package apresentacao;

import dominio.Documento;
import dominio.DocumentoProxy;
import dominio.DocumentoReal;
import dominio.Perfil;
import dominio.Usuario;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        Documento doc = new DocumentoReal("Contrato");

        Documento acessoLeitor = new DocumentoProxy(doc, new Usuario("Ana", Perfil.LEITOR));

        acessoLeitor.ler(); // permitido
        acessoLeitor.editar("novo texto");  // AcessoNegadoException
    }
}