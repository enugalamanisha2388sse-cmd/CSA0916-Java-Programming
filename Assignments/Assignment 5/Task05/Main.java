import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static Connection openConnection() throws SQLException {
        String url = System.getenv().getOrDefault(
            "DB_URL", "jdbc:mysql://localhost:3306/buspass_test"
        );
        String user = System.getenv().getOrDefault("DB_USER", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }

    // Debit and credit are atomic: both updates commit or both roll back.
    static void transfer(Connection connection, long from, long to, double amount)
            throws SQLException {
        boolean previousAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try {
            String debitSql =
                "UPDATE wallet SET balance=balance-? WHERE wallet_id=? AND balance>=?";
            String creditSql =
                "UPDATE wallet SET balance=balance+? WHERE wallet_id=?";

            try (PreparedStatement debit = connection.prepareStatement(debitSql);
                 PreparedStatement credit = connection.prepareStatement(creditSql)) {
                debit.setDouble(1, amount);
                debit.setLong(2, from);
                debit.setDouble(3, amount);

                if (debit.executeUpdate() != 1) {
                    throw new SQLException("Source wallet missing or insufficient balance.");
                }

                credit.setDouble(1, amount);
                credit.setLong(2, to);

                if (credit.executeUpdate() != 1) {
                    throw new SQLException("Destination wallet not found.");
                }
            }

            connection.commit();
            System.out.println("Transfer committed: Rs. " + amount);
        } catch (SQLException exception) {
            connection.rollback();
            System.out.println("Transfer rolled back: " + exception.getMessage());
            throw exception;
        } finally {
            connection.setAutoCommit(previousAutoCommit);
        }
    }

    // Deliberately unsafe example for a disposable local test database only.
    static boolean unsafeLogin(Connection connection, String username, String password)
            throws SQLException {
        String sql = "SELECT user_id FROM demo_user WHERE username='"
            + username + "' AND password_text='" + password + "'";

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            return result.next();
        }
    }

    // Safe version: user input is data, not executable SQL syntax.
    static boolean safeLogin(Connection connection, String username, String password)
            throws SQLException {
        String sql = "SELECT user_id FROM demo_user WHERE username=? AND password_text=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    public static void main(String[] args) {
        try (Connection connection = openConnection()) {
            if (args.length > 0 && args[0].equalsIgnoreCase("login-demo")) {
                String testInput = "' OR '1'='1";
                System.out.println("Unsafe login accepted test input: "
                    + unsafeLogin(connection, testInput, "not-a-password"));
                System.out.println("PreparedStatement accepted test input: "
                    + safeLogin(connection, testInput, "not-a-password"));
                return;
            }

            transfer(connection, 1, 2, 500.00);
        } catch (SQLException exception) {
            System.err.println("Database test failed: " + exception.getMessage());
            System.err.println("Run Task05/schema.sql and configure DB_URL, DB_USER, DB_PASSWORD.");
            System.exit(1);
        }
    }
}
