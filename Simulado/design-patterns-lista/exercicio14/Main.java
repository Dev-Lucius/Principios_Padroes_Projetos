public class Main {
    public static void main(String[] args) {

        Personagem heroi = new Personagem();

        System.out.println("== Movimentos ==");
        heroi.andar();      // Parado -> Correndo
        heroi.pular();      // Correndo -> Pulando
        heroi.pular();      // bloqueado: já está no ar
        heroi.andar();      // bloqueado: não corre no ar
        heroi.parar();      // Pulando -> Parado (aterrissa)

        System.out.println();
        System.out.println("== Ataque ==");
        heroi.atacar();     // Parado -> Atacando
        heroi.pular();      // bloqueado: ocupado atacando
        heroi.parar();      // Atacando -> Parado

        System.out.println();
        System.out.println("== Morte (estado terminal) ==");
        heroi.morrer();     // Parado -> Morto
        heroi.atacar();     // bloqueado: morto não ataca
        heroi.andar();      // bloqueado
        System.out.println("Estado final: " + heroi.getEstadoAtual());
    }
}
