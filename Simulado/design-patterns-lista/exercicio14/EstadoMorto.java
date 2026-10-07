/** Estado terminal: nenhuma ação tem efeito e não há transição de saída. */
public class EstadoMorto implements EstadoPersonagem {

    @Override
    public void andar(Personagem p) {
        System.out.println("Personagem morto não anda.");
    }

    @Override
    public void pular(Personagem p) {
        System.out.println("Personagem morto não pula.");
    }

    @Override
    public void atacar(Personagem p) {
        System.out.println("Personagem morto não ataca.");
    }

    @Override
    public void parar(Personagem p) {
        System.out.println("Personagem morto já está imóvel.");
    }

    @Override
    public void morrer(Personagem p) {
        System.out.println("Já está morto.");
    }
}
