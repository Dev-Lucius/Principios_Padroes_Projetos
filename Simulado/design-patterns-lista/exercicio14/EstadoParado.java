public class EstadoParado implements EstadoPersonagem {

    @Override
    public void andar(Personagem p) {
        System.out.println("Começou a correr.");
        p.setEstado(new EstadoCorrendo());
    }

    @Override
    public void pular(Personagem p) {
        System.out.println("Pulou!");
        p.setEstado(new EstadoPulando());
    }

    @Override
    public void atacar(Personagem p) {
        System.out.println("Atacou!");
        p.setEstado(new EstadoAtacando());
    }

    @Override
    public void parar(Personagem p) {
        System.out.println("Já está parado.");
    }

    @Override
    public void morrer(Personagem p) {
        System.out.println("O personagem morreu.");
        p.setEstado(new EstadoMorto());
    }
}
