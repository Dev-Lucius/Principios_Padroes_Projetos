package apresentacao;

import negocio.Mario;

public class Main {
    public static void main(String[] args) {
        Mario mario = new Mario();
        mario.pegarCogumelo();
        mario.levarDano();
        mario.pegarCogumelo();
        mario.levarDano();
        mario.pegarCogumelo();
        mario.levarDano();
        mario.pegarCogumelo();
        mario.levarDano();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();
        mario.pegarCogumelo();

        System.out.println(mario.getEstado().getClass().getSimpleName());
        // mario.levarDano();
        // System.out.println(mario.getEstado().getClass().getSimpleName());
        // mario.levarDano();
        // System.out.println(mario.getEstado().getClass().getSimpleName());


    }
}