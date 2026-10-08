package design_patters.negocio;

public class BarraDeVida implements Observer<Personagem> {
    @Override
    public void update(Personagem p) {
        System.out.println("[Barra de vida] atualizada para " + p.getVida() + " (piscando!)");
    }
}
