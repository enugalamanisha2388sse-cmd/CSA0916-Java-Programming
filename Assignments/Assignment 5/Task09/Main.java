import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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

    private record Pass(String id, String registerNo, String name, int route,
                        String stop, LocalDate validUntil, BigDecimal amount) {
    }

    private record Rejected(int lineNumber, String source, String reason) {
    }

    private static Pass parse(String line) {
        String[] fields = line.split("\\|", -1);

        if (fields.length != 7) {
            throw new IllegalArgumentException("Expected 7 pipe-separated fields");
        }

        if (fields[0].isBlank() || fields[1].isBlank() || fields[2].isBlank()) {
            throw new IllegalArgumentException("Pass ID, register number and name are required");
        }

        int route = Integer.parseInt(fields[3]);
        LocalDate validUntil = LocalDate.parse(fields[5]);
        BigDecimal amount = new BigDecimal(fields[6]);

        if (route <= 0 || amount.signum() < 0) {
            throw new IllegalArgumentException("Route must be positive and amount non-negative");
        }

        return new Pass(fields[0], fields[1], fields[2], route,
            fields[4], validUntil, amount);
    }

    private static void bind(PreparedStatement statement, Pass pass) throws SQLException {
        statement.setString(1, pass.id());
        statement.setString(2, pass.registerNo());
        statement.setString(3, pass.name());
        statement.setInt(4, pass.route());
        statement.setString(5, pass.stop());
        statement.setDate(6, java.sql.Date.valueOf(pass.validUntil()));
        statement.setBigDecimal(7, pass.amount());
    }

    private static List<Rejected> migrate(Path input, Connection connection)
            throws IOException, SQLException {
        List<Rejected> rejected = new ArrayList<>();
        boolean oldAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (BufferedReader reader = Files.newBufferedReader(input);
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            String line;
            int lineNumber = 0;
            int accepted = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                try {
                    Pass pass = parse(line);
                    bind(statement, pass);
                    statement.addBatch();
                    accepted++;

                    if (accepted % 1000 == 0) {
                        statement.executeBatch();
                    }
                } catch (RuntimeException exception) {
                    rejected.add(new Rejected(lineNumber, line, exception.getMessage()));
                }
            }

            statement.executeBatch();
            connection.commit();
            System.out.println("Valid records submitted: " + accepted);
        } catch (IOException | SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }

        return rejected;
    }

    private static void showMetadata(Connection connection) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();

        System.out.println("Database: " + metadata.getDatabaseProductName()
            + " " + metadata.getDatabaseProductVersion());
        System.out.println("Driver: " + metadata.getDriverName()
            + " " + metadata.getDriverVersion());

        try (ResultSet tables = metadata.getTables(
                connection.getCatalog(), null, "%", new String[] {"TABLE"})) {
            System.out.println("Available tables:");
            while (tables.next()) {
                System.out.println(" - " + tables.getString("TABLE_NAME"));
            }
        }
    }

    public static void main(String[] args) {
        Path input = args.length > 0 ? Path.of(args[0]) : Path.of("../Task06/passes.txt");
        Path rejectsFile = Path.of("rejected-records.txt");

        try (Connection connection = openConnection()) {
            List<Rejected> rejected = migrate(input, connection);

            List<String> report = new ArrayList<>();
            for (Rejected item : rejected) {
                report.add("Line " + item.lineNumber() + " | " + item.reason()
                    + " | " + item.source());
            }
            Files.write(rejectsFile, report);

            System.out.println("Rejected records: " + rejected.size());
            System.out.println("Reject report: " + rejectsFile.toAbsolutePath());
            showMetadata(connection);
        } catch (IOException | SQLException exception) {
            System.err.println("Migration failed: " + exception.getMessage());
            System.err.println("No partial migration is committed. Check input path and MySQL settings.");
            System.exit(1);
        }
    }
}
