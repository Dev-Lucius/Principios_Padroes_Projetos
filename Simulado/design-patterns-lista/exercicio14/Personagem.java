/**
 * STATE - CONTEXTO.
 * Mantém o estado atual e delega todas as ações a ele.
 * As regras ("morto não ataca", "no ar não pula de novo") NÃO estão aqui:
 * estão distribuídas nas classes de estado.
 */
public class Personagem {

    private EstadoPersonagem estado = new EstadoParado();

    public void andar()  { estado.andar(this); }
    public void pular()  { estado.pular(this); }
    public void atacar() { estado.atacar(this); }
    public void parar()  { estado.parar(this); }
    public void morrer() { estado.morrer(this); }

    void setEstado(EstadoPersonagem novo) {
        System.out.println("   [transição] " + estado.getClass().getSimpleName()
                + " -> " + novo.getClass().getSimpleName());
        this.estado = novo;
    }

    public String getEstadoAtual() {
        return estado.getClass().getSimpleName();
    }
}
