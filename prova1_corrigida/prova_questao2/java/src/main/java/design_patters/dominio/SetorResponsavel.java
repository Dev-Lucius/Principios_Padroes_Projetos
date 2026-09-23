package design_patters.dominio;

public class SetorResponsavel implements ObservadorChamado{
    private final String nomeSetor;

    public SetorResponsavel(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    @Override
    public void atualizar(Chamado chamado, StatusChamado statusAnterior) {
        if (chamado.getStatus() == StatusChamado.ENCERRADO) {
            System.out.printf("  [Setor %s] Notificacao: chamado #%d foi encerrado.%n",
                    nomeSetor, chamado.getId());
        }
    }
}
