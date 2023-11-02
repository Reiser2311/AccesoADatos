import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String password = "2311";
        Connection con = DriverManager.getConnection(url, user, password);
        Statement statement = con.createStatement();
        String SQLsentence = "SELECT * FROM asignaturas ORDER BY codigo";
        ResultSet rs = statement.executeQuery(SQLsentence);
        System.out.println("Codigo" + "\t" + "Nombre:");
        System.out.println("----------------------------------------------------");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
        rs.close();
        con.close();
    }
}