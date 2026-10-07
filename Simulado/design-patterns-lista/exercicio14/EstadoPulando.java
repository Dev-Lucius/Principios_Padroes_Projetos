public class EstadoPulando implements EstadoPersonagem {

    @Override
    public void andar(Personagem p) {
        System.out.println("Não dá para correr no ar.");
    }

    @Override
    public void pular(Personagem p) {
        System.out.println("Já está no ar: não pode iniciar outro pulo.");
    }

    @Override
    public void atacar(Personagem p) {
        System.out.println("Não pode atacar enquanto pula.");
    }

    @Override
    public void parar(Personagem p) {
        System.out.println("Aterrissou.");
        p.setEstado(new EstadoParado());
    }

    @Override
    public void morrer(Personagem p) {
        System.out.println("O personagem morreu no ar.");
        p.setEstado(new EstadoMorto());
    }
}
