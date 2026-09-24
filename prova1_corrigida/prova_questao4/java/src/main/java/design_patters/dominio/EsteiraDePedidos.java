package design_patters.dominio;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * INVOKER.
 * Recebe qualquer SolicitacaoPreparo, enfileira (FIFO), executa na ordem de
 * chegada, permite cancelar e guarda o historico. Nao sabe nada sobre
 * cozinha, bar ou confeitaria.
 */
public class EsteiraDePedidos {

    private final Queue<SolicitacaoPreparo> fila = new ArrayDeque<>();
    private final List<SolicitacaoPreparo> historico = new ArrayList<>();

    /** Enfileira a solicitacao e a registra no historico. */
    public void enviar(SolicitacaoPreparo solicitacao) {
        fila.add(solicitacao);
        historico.add(solicitacao);
        System.out.println("Enfileirado: " + solicitacao.getDescricao());
    }

    /** Cancelamento imediato de um pedido enviado por engano, se ainda nao preparado. */
    public boolean cancelar(int id) {
        for (SolicitacaoPreparo s : fila) {
            if (s.getId() == id) {
                boolean cancelou = s.cancelar();
                if (cancelou) {
                    fila.remove(s);
                    System.out.println("Cancelado: " + s.getDescricao());
                }
                return cancelou;
            }
        }
        System.out.println("Pedido #" + id + " nao pode ser cancelado (ja preparado ou inexistente).");
        return false;
    }

    /** Processa o proximo da fila (ordem de chegada). Retorna false se nao havia nenhum. */
    public boolean processarProximo() {
        SolicitacaoPreparo proxima = fila.poll();
        if (proxima == null) {
            return false;
        }
        proxima.executar();
        return true;
    }

    public void processarTodos() {
        while (processarProximo()) {
            // continua ate esvaziar a fila
        }
    }

    public List<SolicitacaoPreparo> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public int tamanhoDaFila() {
        return fila.size();
    }
}
