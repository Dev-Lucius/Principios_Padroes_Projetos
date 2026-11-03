package dominio;

public class ImagemProxy implements Imagem{

    private final String arquivo;
    private ImagemReal imagemReal;

    public ImagemProxy(String arquivo){
        this.arquivo = arquivo;
    }

    @Override
    public void exibir() {
        if(imagemReal == null){
            imagemReal = new ImagemReal(arquivo);
        }
        imagemReal.exibir();
    }

}
