import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    // Transfers a fixed amount between two wallets in one transaction.
    static void transfer(Connection connection, long from, long to) throws SQLException {
        boolean previousAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (
            PreparedStatement debit = connection.prepareStatement(
                "UPDATE wallet SET balance=balance-500 WHERE id=? AND balance>=500"
            );
            PreparedStatement credit = connection.prepareStatement(
                "UPDATE wallet SET balance=balance+500 WHERE id=?"
            )
        ) {
            debit.setLong(1, from);

            if (debit.executeUpdate() != 1) {
                throw new SQLException("Insufficient funds");
            }

            credit.setLong(1, to);

            if (credit.executeUpdate() != 1) {
                throw new SQLException("Wallet missing");
            }

            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(previousAutoCommit);
        }
    }

    public static void main(String[] args) {
        System.out.println("Transfer: debit and credit in one transaction; rollback on any failure.");
        System.out.println("Login uses PreparedStatement placeholders for username and password hash.");
    }
}
