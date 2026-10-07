public class EstadoAtacando implements EstadoPersonagem {

    @Override
    public void andar(Personagem p) {
        System.out.println("Está atacando: aguarde o fim do ataque.");
    }

    @Override
    public void pular(Personagem p) {
        System.out.println("Está atacando: aguarde o fim do ataque.");
    }

    @Override
    public void atacar(Personagem p) {
        System.out.println("Já está atacando.");
    }

    @Override
    public void parar(Personagem p) {
        System.out.println("Terminou o ataque.");
        p.setEstado(new EstadoParado());
    }

    @Override
    public void morrer(Personagem p) {
        System.out.println("O personagem morreu durante o ataque.");
        p.setEstado(new EstadoMorto());
    }
}
