import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    // Displays database, driver and table information using JDBC metadata.
    static void metadata(Connection connection) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();

        System.out.println(
            metadata.getDatabaseProductName() + " "
                + metadata.getDatabaseProductVersion()
        );
        System.out.println(
            metadata.getDriverName() + " "
                + metadata.getDriverVersion()
        );

        try (
            ResultSet tables = metadata.getTables(
                connection.getCatalog(),
                null,
                "%",
                new String[] {"TABLE"}
            )
        ) {
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Migration: validate file -> batch 1000 -> transaction -> report rejects.");
        System.out.println("Use DatabaseMetaData for product, driver and table inventory.");
    }
}
