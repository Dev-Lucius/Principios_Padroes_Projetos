package design_patters.dominio;

public class DocumentoReal implements Documento {

    private final String titulo;
    private String conteudo;
    private boolean excluido;

    public DocumentoReal(String titulo) {
        this.titulo = titulo;
        this.conteudo = "Conteúdo inicial do documento.";
        this.excluido = false;
    }

    @Override
    public String ler() {

        if (excluido) {
            throw new IllegalStateException(
                "O documento foi excluído."
            );
        }

        return conteudo;
    }

    @Override
    public void editar(String novoConteudo) {

        if (excluido) {
            throw new IllegalStateException(
                "Não é possível editar um documento excluído."
            );
        }

        this.conteudo = novoConteudo;

        System.out.println(
            "Documento '" + titulo + "' editado com sucesso."
        );
    }

    @Override
    public void excluir() {

        if (excluido) {
            throw new IllegalStateException(
                "O documento já foi excluído."
            );
        }

        this.excluido = true;

        System.out.println(
            "Documento '" + titulo + "' excluído com sucesso."
        );
    }
}
