package design_patters.apresentacao;

import design_patters.dominio.AcessoNegadoException;
import design_patters.dominio.Documento;
import design_patters.dominio.DocumentoProxy;
import design_patters.dominio.DocumentoReal;
import design_patters.dominio.Perfil;
import design_patters.dominio.Usuario;

public class Main {

    public static void main(String[] args) {

        // 1. Criando o documento real
        Documento documentoReal =
            new DocumentoReal("Contrato");

        // 2. Criando os usuários
        Usuario ana = new Usuario("Ana", Perfil.LEITOR);

        Usuario carlos = new Usuario("Carlos", Perfil.EDITOR);

        Usuario lucas = new Usuario("Lucas", Perfil.ADMIN);

        // 3. Criando os proxies
        Documento acessoAna =
            new DocumentoProxy(documentoReal, ana);

        Documento acessoCarlos =
            new DocumentoProxy(documentoReal, carlos);

        Documento acessoLucas =
            new DocumentoProxy(documentoReal, lucas);

        // 4. Testando o LEITOR
        System.out.println("\n=== TESTE: LEITOR ===");

        try {
            System.out.println(acessoAna.ler());

            acessoAna.editar("Novo conteúdo");

        } catch (AcessoNegadoException e) {
            System.out.println(e.getMessage());
        }

        // 5. Testando o EDITOR
        System.out.println("\n=== TESTE: EDITOR ===");

        try {
            acessoCarlos.editar("Contrato atualizado");

            acessoCarlos.excluir();

        } catch (AcessoNegadoException e) {
            System.out.println(e.getMessage());
        }

        // 6. Testando o ADMIN
        System.out.println("\n=== TESTE: ADMIN ===");

        try {
            System.out.println(acessoLucas.ler());

            acessoLucas.editar("Contrato final");

            acessoLucas.excluir();

        } catch (AcessoNegadoException e) {
            System.out.println(e.getMessage());
        }
    }
}
