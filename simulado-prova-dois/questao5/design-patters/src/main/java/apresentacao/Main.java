package apresentacao;

import dominio.Imagem;
import dominio.ImagemProxy;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        // Criando os proxies.
        Imagem foto1 = new ImagemProxy("foto1.png");
        Imagem foto2 = new ImagemProxy("foto2.png");
        Imagem foto3 = new ImagemProxy("foto3.png");

        // Exibindo as imagens.
        foto1.exibir();
        foto2.exibir();
        foto3.exibir();
    }
}