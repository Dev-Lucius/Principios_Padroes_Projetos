package design_patters.negocio;

public class SistemaConquistas implements Observer<Personagem> {
    @Override
    public void update(Personagem p) {
        if (p.getVida() <= 0) {
            System.out.println("[Conquistas] troféu liberado: 'Primeira morte'");
        }
    }
}
