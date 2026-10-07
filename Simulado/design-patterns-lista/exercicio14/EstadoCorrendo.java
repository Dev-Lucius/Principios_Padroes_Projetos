public class EstadoCorrendo implements EstadoPersonagem {

    @Override
    public void andar(Personagem p) {
        System.out.println("Já está correndo.");
    }

    @Override
    public void pular(Personagem p) {
        System.out.println("Pulou durante a corrida!");
        p.setEstado(new EstadoPulando());
    }

    @Override
    public void atacar(Personagem p) {
        System.out.println("Atacou correndo!");
        p.setEstado(new EstadoAtacando());
    }

    @Override
    public void parar(Personagem p) {
        System.out.println("Parou de correr.");
        p.setEstado(new EstadoParado());
    }

    @Override
    public void morrer(Personagem p) {
        System.out.println("O personagem morreu.");
        p.setEstado(new EstadoMorto());
    }
}
