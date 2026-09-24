package design_patters.dominio;

/**
 * COMMAND (interface).
 * Encapsula uma solicitacao de preparo como um objeto independente: quem cria o
 * pedido (garcom/interface) so conhece esta interface, nunca cozinha, bar etc.
*/
public interface SolicitacaoPreparo {
    void executar();

    /** Cancela a solicitacao. Retorna false se ela ja nao pode mais ser cancelada. */
    boolean cancelar();

    int getId();

    String getDescricao();

    StatusSolicitacao getStatus();
}

