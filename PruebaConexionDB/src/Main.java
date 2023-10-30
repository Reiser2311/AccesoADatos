import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
            String user = "postgres";
            String password = "2311";
            conexion = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion establecida");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (conexion != null) conexion.close();
        }
    }
}