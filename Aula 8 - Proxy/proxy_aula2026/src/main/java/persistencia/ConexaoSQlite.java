package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQlite {
    
    public Connection getConexao() throws SQLException {
        String url = "jdbc:sqlite:my_database.db"; // Local database file
        return DriverManager.getConnection(url);
    }

}
