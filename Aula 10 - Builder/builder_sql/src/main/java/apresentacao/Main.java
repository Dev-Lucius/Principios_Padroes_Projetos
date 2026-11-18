package apresentacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import negocio.*;
import negocio.Query.QueryBuilder;


public class Main {
    public static void main(String[] args) throws SQLException {
        // loombok
        Aluno aluno = Aluno.builder().nome("NOME").build();
        System.out.println(aluno.getNome()); 
        // na mão
        // String url = "jdbc:postgresql://localhost:5432/sist_requerimento";
        // Connection conexao = DriverManager.getConnection(url, "postgres", "postgres");
        // Query query = new Query.QueryBuilder("aluno").tablesJoin(Arrays.asList("requerimento", "anexo")).orderBy(" nome DESC;").where("aluno.id = 1 AND aluno.nome = 'IGOR'").build();        
        // System.out.println(query.toString());
        // ResultSet rs = conexao.prepareStatement(query.toString()).executeQuery();
        // while (rs.next()) {
        //     System.out.println(rs.getString("nome"));
        // }
    }
}