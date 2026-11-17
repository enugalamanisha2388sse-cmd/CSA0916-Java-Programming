import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String INSERT_SQL =
        "INSERT INTO bus_pass(pass_id,register_no,student_name,route_no,"
        + "boarding_stop,valid_until,amount_paid) VALUES(?,?,?,?,?,?,?)";

    private static Connection openConnection() throws SQLException {
        String url = System.getenv().getOrDefault(
            "DB_URL", "jdbc:mysql://localhost:3306/buspass_test"
        );
        String user = System.getenv().getOrDefault("DB_USER", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }

    private static String passId(int number) {
        return "P" + number;
    }

    private static void clearTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM bus_pass");
        }
    }

    private static void insertWithStatement(Connection connection, int count)
            throws SQLException {
        boolean oldAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (Statement statement = connection.createStatement()) {
            for (int i = 1; i <= count; i++) {
                String sql = "INSERT INTO bus_pass VALUES('P" + i + "','REG" + i
                    + "','Student " + i + "'," + (i % 20 + 1)
                    + ",'Stop " + (i % 10 + 1)
                    + "','2027-12-31'," + (100 + i % 500) + ".00)";
                statement.executeUpdate(sql);
            }
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }
    }

    private static void insertWithPreparedStatement(Connection connection, int count)
            throws SQLException {
        boolean oldAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            for (int i = 1; i <= count; i++) {
                bind(statement, i);
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }
    }

    private static void insertWithBatch(Connection connection, int count)
            throws SQLException {
        boolean oldAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            for (int i = 1; i <= count; i++) {
                bind(statement, i);
                statement.addBatch();

                if (i % 1000 == 0) {
                    statement.executeBatch();
                }
            }

            statement.executeBatch();
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }
    }

    private static void bind(PreparedStatement statement, int i) throws SQLException {
        statement.setString(1, passId(i));
        statement.setString(2, "REG" + i);
        statement.setString(3, "Student " + i);
        statement.setInt(4, i % 20 + 1);
        statement.setString(5, "Stop " + (i % 10 + 1));
        statement.setDate(6, java.sql.Date.valueOf("2027-12-31"));
        statement.setBigDecimal(7, BigDecimal.valueOf(100 + i % 500));
    }

    private static long measure(Connection connection, String label, int count,
                                SqlWork work) throws SQLException {
        clearTable(connection);
        long start = System.nanoTime();
        work.run(connection, count);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("%-22s %,d rows: %d ms%n", label, count, elapsedMs);
        return elapsedMs;
    }

    @FunctionalInterface
    interface SqlWork {
        void run(Connection connection, int count) throws SQLException;
    }

    private static void showMetadata(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM bus_pass LIMIT 1")) {
            ResultSetMetaData metadata = result.getMetaData();
            System.out.println("Column metadata:");
            for (int i = 1; i <= metadata.getColumnCount(); i++) {
                System.out.println(metadata.getColumnName(i) + " : "
                    + metadata.getColumnTypeName(i));
            }
        }
    }

    // A deliberately invalid amount halfway through demonstrates rollback.
    private static void demonstrateRollback(Connection connection) throws SQLException {
        clearTable(connection);
        boolean oldAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            for (int i = 1; i <= 10; i++) {
                bind(statement, i);

                if (i == 5) {
                    statement.setBigDecimal(7, new BigDecimal("-999.00"));
                }

                statement.addBatch();
            }

            statement.executeBatch();
            connection.commit();
            System.out.println("Unexpected: invalid test data was accepted.");
        } catch (SQLException exception) {
            connection.rollback();
            System.out.println("Expected failure occurred; transaction rolled back.");
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM bus_pass")) {
            result.next();
            System.out.println("Rows remaining after rollback: " + result.getInt(1));
        }
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};

        try (Connection connection = openConnection()) {
            System.out.println("All timings are measured on this MySQL server; repeat runs for median values.");

            for (int size : sizes) {
                measure(connection, "Statement", size, Main::insertWithStatement);
                measure(connection, "PreparedStatement", size,
                    Main::insertWithPreparedStatement);
                measure(connection, "PreparedStatement batch", size,
                    Main::insertWithBatch);
            }

            showMetadata(connection);
            demonstrateRollback(connection);
        } catch (SQLException exception) {
            System.err.println("JDBC comparison failed: " + exception.getMessage());
            System.err.println("Run Task08/schema.sql and configure DB_URL, DB_USER, DB_PASSWORD.");
            System.exit(1);
        }
    }
}
