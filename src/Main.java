import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Main {

    public static void main (String[] args) {
        DataSource dataSource = createDataSource();
        try (Connection connection = dataSource.getConnection()) {

            printAllDrzave(connection);

            CallableStatement cs = connection.prepareCall("{CALL DeleteDrzaveVeceOdIDa(?)}");
            cs.setInt(1, 18);
            cs.executeUpdate();

            printAllDrzave(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource createDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName("AdventureWorksOBP");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(58355);
        dataSource.setIntegratedSecurity(true);
        dataSource.setEncrypt(false);

        return dataSource;
    }

    public static void printAllDrzave(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Drzava");
        while (rs.next()) {
            System.out.printf("%s",rs.getString("Naziv"));
        }
        System.out.println("\n");
    }
}
