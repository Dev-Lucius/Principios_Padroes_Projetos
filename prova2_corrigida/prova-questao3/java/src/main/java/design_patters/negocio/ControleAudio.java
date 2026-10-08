package design_patters.negocio;

public class ControleAudio implements Observer<Personagem> {
    @Override
    public void update(Personagem p) {
        System.out.println("[Áudio] som de impacto");
    }
}
