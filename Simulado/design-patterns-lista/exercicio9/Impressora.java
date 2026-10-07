import java.util.ArrayList;
import java.util.List;

/**
 * RECEIVER: quem realmente faz o trabalho.
 * Não sabe nada sobre Comando nem sobre a fila.
 */
public class Impressora {

    private boolean pausada = false;
    private final List<String> aguardando = new ArrayList<>();

    public void imprimir(String documento) {
        if (pausada) {
            aguardando.add(documento);
            System.out.println("Impressora pausada: '" + documento + "' ficou aguardando");
        } else {
            System.out.println("Imprimindo '" + documento + "'");
        }
    }

    public void cancelar(String documento) {
        if (aguardando.remove(documento)) {
            System.out.println("Cancelado: '" + documento + "' removido da espera");
        } else {
            System.out.println("Cancelamento solicitado para '" + documento + "'");
        }
    }

    public void pausar() {
        pausada = true;
        System.out.println("Impressora pausada");
    }

    public void retomar() {
        pausada = false;
        System.out.println("Impressora retomada");

        List<String> pendentes = new ArrayList<>(aguardando);
        aguardando.clear();
        pendentes.forEach(this::imprimir);
    }
}
