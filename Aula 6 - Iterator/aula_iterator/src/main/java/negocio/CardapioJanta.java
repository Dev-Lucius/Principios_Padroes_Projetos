package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class CardapioJanta implements Iterator<MenuItem> {
    private ResultSet resultSet;
    private Connection connection;

    public CardapioJanta() {
        String url = "jdbc:postgresql://localhost:5432/restaurante_jefferson";
        try {
            this.connection = DriverManager.getConnection(url, "postgres", "postgres");
            String sql = "SELECT * FROM menu_item";
            PreparedStatement instrucaoSQL = this.connection.prepareStatement(sql);
            this.resultSet = instrucaoSQL.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        try {
            return this.resultSet.next();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = null;
        try {
            String descricao = this.resultSet.getString("descricao");
            double preco = this.resultSet.getDouble("preco");
            boolean vegetariano = this.resultSet.getBoolean("vegetariano");
            Segmento segmento = null;
            switch(this.resultSet.getString("segmento")) {
                case "CAFE_DA_MANHA": segmento = Segmento.CAFE_DA_MANHA;  
                case "ALMOCO": segmento = Segmento.ALMOCO;
                case "JANTA": segmento = Segmento.JANTA;  
            }
            menuItem = new MenuItem(descricao, preco, vegetariano, segmento);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return menuItem;
    }

}
