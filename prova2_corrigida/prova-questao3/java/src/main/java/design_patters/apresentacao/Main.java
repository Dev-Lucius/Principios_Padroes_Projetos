package design_patters.apresentacao;

import design_patters.negocio.BarraDeVida;
import design_patters.negocio.ControleAudio;
import design_patters.negocio.Observer;
import design_patters.negocio.Personagem;
import design_patters.negocio.SistemaConquistas;

public class Main {
    public static void main(String[] args) {

        Personagem heroi = new Personagem(100, 50, 1);

        Observer<Personagem> barra = new BarraDeVida();
        heroi.addObserver(barra);
        heroi.addObserver(new ControleAudio());
        heroi.addObserver(new SistemaConquistas());

        System.out.println("-- dano: vida 100 -> 40 --");
        heroi.setVida(40);          // ninguém chama notifyObservers() manualmente

        System.out.println("-- painel de vida some da tela --");
        heroi.removeObserver(barra);

        System.out.println("-- dano fatal: vida 40 -> 0 --");
        heroi.setVida(0);
    }
}
