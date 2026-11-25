package design_patters.dominio;

public class DocumentoProxy implements Documento {

    private final Documento documento;
    private final Usuario usuario;

    public DocumentoProxy(Documento documento, Usuario usuario) {
        this.documento = documento;
        this.usuario = usuario;
    }

    // Qualquer perfil pode ler
    @Override
    public String ler() {
        return documento.ler();
    }

    // EDITOR e ADMIN podem editar
    @Override
    public void editar(String novoConteudo) {

        if (usuario.getPerfil() == Perfil.LEITOR) {

            registrarNegado("editar");

            throw new AcessoNegadoException(
                "Sem permissão para editar."
            );
        }

        documento.editar(novoConteudo);
    }

    // Apenas ADMIN pode excluir
    @Override
    public void excluir() {

        if (usuario.getPerfil() != Perfil.ADMIN) {

            registrarNegado("excluir");

            throw new AcessoNegadoException(
                "Sem permissão para excluir."
            );
        }

        documento.excluir();
    }

    // Registra tentativas não autorizadas
    private void registrarNegado(String operacao) {

        System.out.println(
            "[LOG] Acesso negado: "
            + usuario.getNome()
            + " tentou "
            + operacao
        );
    }
}
