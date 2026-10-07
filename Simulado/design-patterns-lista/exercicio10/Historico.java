import java.util.ArrayDeque;
import java.util.Deque;

/**
 * INVOKER com histórico.
 * Executar = rodar o comando E empilhá-lo.
 * Desfazer = desempilhar o último (LIFO) e chamar desfazer() nele.
 */
public class Historico {

    private final Deque<Comando> pilha = new ArrayDeque<>();

    public void executar(Comando comando) {
        comando.executar();
        pilha.push(comando);
    }

    public void desfazer() {
        if (pilha.isEmpty()) {
            System.out.println("Nada para desfazer");
            return;
        }
        pilha.pop().desfazer();
    }
}
