package dominio;

public final class DocumentoReal implements Documento{

    private String documento;
    private Usuario user;

    public DocumentoReal(String documento){
        this.documento = documento;
        carregarDocumento();
    }

    public void carregarDocumento(){
        System.out.println("Carregando " + documento + " do Disco");
    }

    @Override
    public String ler() {
        return "Lendo Documento: " + documento;
    }

    @Override
    public void editar(String novoConteudo) {
        if(user.getPerfil() == Perfil.ADMIN) {
            System.out.println("Editando Documento");
            this.documento = novoConteudo;
        }
        System.out.println("Acesso Negado");
    }

    @Override
    public void excluir() {
        if(user.getPerfil() == Perfil.ADMIN) {
            this.documento =  null;
        }
        System.out.println("Acesso Negado");
    }


}
