package negocio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExportacaoSQL extends Exportacao {


    @Override
    protected String transformacao() {        
        String resultado = "DROP DATABASE IF EXISTS template_method; \n";
        resultado += "CREATE DATABASE template_method; \n";
        resultado += "\\c template_method; \n";
        if (this.vetPessoa.size() > 0){
            resultado += "CREATE TABLE pessoa (id serial primary key, nome text); \n";
            for (Pessoa pessoa : vetPessoa) {
                resultado += "INSERT INTO pessoa (id, nome) VALUES ("+pessoa.getId()+",'"+pessoa.getNome().trim()+"'); \n";
            }
        }
        return resultado;
    }

    protected void gravacao(String resultado) {
        super.gravacao(resultado);
        // executaSQL(resultado);
    }      

    // private void executaSQL(String resultado) { 
    //     try {
    //         Runtime.getRuntime().exec("PGPASSWORD=<SENHA> pg_dump --host <HOST> --port <PORT> --username <USERNAME> --format plain --create --clean --inserts --verbose --file <NOME_DO_ARQUIVO>.sql template_methd /home/iapereira/git/ppp2026_1sem/03_template_method/exemplo/exportado.sql");
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }


    
}
