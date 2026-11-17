import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    // Inserts bus-pass rows in batches of 1,000 within one transaction.
    static void batch(Connection connection, String[][] rows) throws SQLException {
        boolean previousAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO bus_pass"
            + "(pass_id,register_no,student_name,route_no,boarding_stop,valid_until,amount_paid)"
            + " VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int count = 0;

            for (String[] row : rows) {
                statement.setString(1, row[0]);
                statement.setString(2, row[1]);
                statement.setString(3, row[2]);
                statement.setInt(4, Integer.parseInt(row[3]));
                statement.setString(5, row[4]);
                statement.setDate(6, Date.valueOf(row[5]));
                statement.setBigDecimal(7, new java.math.BigDecimal(row[6]));
                statement.addBatch();

                count++;

                if (count % 1000 == 0) {
                    statement.executeBatch();
                }
            }

            statement.executeBatch();
            connection.commit();
        } catch (Exception exception) {
            connection.rollback();

            if (exception instanceof SQLException) {
                throw (SQLException) exception;
            }

            throw new SQLException(exception);
        } finally {
            connection.setAutoCommit(previousAutoCommit);
        }
    }

    public static void main(String[] args) {
        System.out.println("PassLoader batch size 1000; run with MySQL Connector/J and schema.sql.");
    }
}
