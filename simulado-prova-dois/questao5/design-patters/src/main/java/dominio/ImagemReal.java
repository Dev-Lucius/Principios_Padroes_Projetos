package dominio;

public final class ImagemReal implements Imagem{

    private final String arquivo;

    public ImagemReal(String arquivo) {
        this.arquivo = arquivo;
        carregarImagem();
    }

    public void carregarImagem(){
        System.out.println("Carregando " + arquivo + " do Disco ...");
    }

    @Override
    public void exibir() {
        System.out.println("Exibindo Imagem: " + arquivo);
    }

}
