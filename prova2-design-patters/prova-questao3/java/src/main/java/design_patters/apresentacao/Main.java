package design_patters.apresentacao;

import design_patters.negocio.Equipe;
import design_patters.negocio.Personagem;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        Personagem p1 = new Personagem(10, 50, 5);
        System.out.println(p1.toString());

        Equipe e1 = new Equipe("Equipe Teste", 1);

        p1.addObserver(e1);
        p1.setNivel(6);

        p1.notifyObservers();
    }
}