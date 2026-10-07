import java.util.ArrayDeque;
import java.util.Queue;

/**
 * INVOKER: só conhece a interface Comando.
 * Não sabe se é imprimir, pausar ou cancelar, nem que existe uma Impressora.
 * Por isso qualquer novo comando pode entrar na fila sem alterar esta classe.
 */
public class FilaDeImpressao {

    private final Queue<Comando> comandos = new ArrayDeque<>();

    public void adicionar(Comando comando) {
        comandos.add(comando);
        System.out.println("  + enfileirado (" + comandos.size() + " na fila)");
    }

    public void executarTodos() {
        Comando comando;
        while ((comando = comandos.poll()) != null) { // FIFO: o primeiro a entrar é o primeiro a executar
            comando.executar();
        }
    }
}
