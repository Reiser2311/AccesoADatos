import java.sql.*;

public class MiBD {

    private Connection connection = null;
    String url = "jdbc:postgresql://localhost:5432/";
    String user = "postgres";
    String password = "iesbelen";

    public MiBD(String BD) {
        this.url += BD;
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public void createTable(String tableName, String tableDefinition) throws SQLException {
        String createTableSQL;
        Statement statement = connection.createStatement();
        createTableSQL = "CREATE TABLE " + tableName + " (" + tableDefinition + ")";
        statement.execute(createTableSQL);
        statement.close();
    }

    public void updateData(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }


}
