package dominio;

public class DocumentoProxy implements Documento{
    
    private String arquivo;
    private final Usuario usuario;
    private DocumentoReal docReal;
    private final Documento doc;


    public DocumentoProxy(Documento doc, Usuario usuario) {
        this.doc = doc;
        this.usuario = usuario;
    }

    @Override
    public String ler() {
        if(docReal == null){
            docReal = new DocumentoReal(arquivo);
        }
        return "Lendo Documento: " + docReal;
    }

    @Override
    public void editar(String novoConteudo) {
        if(usuario.getPerfil() == Perfil.ADMIN) {
            System.out.println("Editando Documento");
            this.arquivo = novoConteudo;
        }
        System.out.println("Acesso Negado");
    }

    @Override
    public void excluir() {
        if(usuario.getPerfil() == Perfil.ADMIN) {
            this.arquivo =  null;
        }
        System.out.println("Acesso Negado");
    }
}
