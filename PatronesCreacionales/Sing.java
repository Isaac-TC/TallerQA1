package PatronesCreacionales;
import java.sql.*;

class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection(String dbPath) throws SQLException {
        String url = "jdbc:sqlite:" + dbPath;
        this.connection = DriverManager.getConnection(url);
    }

    public static synchronized DatabaseConnection getInstance(String dbPath) throws SQLException {
        if (instance == null || instance.connection == null || instance.connection.isClosed()) {
            instance = new DatabaseConnection(dbPath);
        }
        return instance;
    }

    public void query(String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        }
    }

    public ResultSet select(String sql, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeQuery(); // recuerda cerrar el ResultSet afuera
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        instance = null;
    }
}

public class Sing {
    public static void main(String[] args) {
        try {
            DatabaseConnection db = DatabaseConnection.getInstance("database.db");

            db.query("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
            db.query("INSERT INTO users (name) VALUES (?)", "Isaac Tonato");
            db.query("INSERT INTO users (name) VALUES (?)", "Eduardo Tonato");

            // Reusar la misma instancia
            DatabaseConnection db2 = DatabaseConnection.getInstance("database.db");
            try (ResultSet rs = db2.select("SELECT * FROM users")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println(id + " - " + name);
                }
            }

            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
