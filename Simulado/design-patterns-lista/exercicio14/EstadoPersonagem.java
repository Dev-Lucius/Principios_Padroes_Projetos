/**
 * Cada método é um "evento" que o jogador pode disparar.
 * parar() significa "encerrar a ação atual e ficar parado":
 *  - correndo -> para de correr;
 *  - pulando  -> aterrissa;
 *  - atacando -> termina o ataque.
 */
public interface EstadoPersonagem {

    void andar(Personagem personagem);

    void pular(Personagem personagem);

    void atacar(Personagem personagem);

    void parar(Personagem personagem);

    void morrer(Personagem personagem);
}
