import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String password = "iesbelen";
        String SQLsentence;

        Connection con = DriverManager.getConnection(url, user, password);
        Statement statement = con.createStatement();

        SQLsentence = "INSERT INTO public.asignatura(nombre, anyo) VALUES ('LENGUAJE DE MARCAS', 1)";
        statement.executeUpdate(SQLsentence);

        SQLsentence = "ALTER TABLE asignatura ADD COLUMN horas INTEGER";
        statement.executeUpdate(SQLsentence);

        SQLsentence = "SELECT * FROM asignatura ORDER BY codigo";
        ResultSet rs = statement.executeQuery(SQLsentence);
        System.out.println("Codigo\tNombre\tAño");
        System.out.println("----------------------------------------------------");
        while (rs.next()) {
            for (int i = 0; i < 4; i++) {
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.println();
        }
        rs.close();
        con.close();
    }
}