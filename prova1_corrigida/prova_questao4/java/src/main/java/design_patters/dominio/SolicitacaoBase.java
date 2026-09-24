package design_patters.dominio;

/**
 * Classe base dos comandos concretos: concentra id, mesa, status e a regra
 * padronizada de cancelamento (so e possivel antes do inicio do preparo).
*/
public abstract class SolicitacaoBase implements SolicitacaoPreparo{
    private final int id;
    private final int mesa;
    private final String item;
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;

    protected SolicitacaoBase(int id, int mesa, String item) {
        this.id = id;
        this.mesa = mesa;
        this.item = item;
    }

    /** Cada comando concreto delega ao seu Receiver. */
    protected abstract void prepararNoSetor(String item);

    protected abstract String getSetor();

    @Override
    public void executar() {
        if (status != StatusSolicitacao.PENDENTE) {
            return; // ja cancelada ou concluida
        }
        prepararNoSetor(item);
        status = StatusSolicitacao.CONCLUIDA;
    }

    @Override
    public boolean cancelar() {
        if (status != StatusSolicitacao.PENDENTE) {
            return false; // preparo ja iniciado/concluido ou ja cancelada
        }
        status = StatusSolicitacao.CANCELADA;
        return true;
    }

    @Override public int getId() { return id; }
    @Override public StatusSolicitacao getStatus() { return status; }

    @Override
    public String getDescricao() {
        return String.format("#%d | Mesa %d | %s | %s", id, mesa, getSetor(), item);
    }
}
