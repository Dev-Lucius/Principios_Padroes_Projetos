package design_patters.dominio;

public class PrepararPratoPrincipal extends SolicitacaoBase{
    private final Cozinha cozinha;

    public PrepararPratoPrincipal(int id, int mesa, String prato, Cozinha cozinha) {
        super(id, mesa, prato);
        this.cozinha = cozinha;
    }

    @Override protected void prepararNoSetor(String item) { cozinha.prepararPrato(item); }
    @Override protected String getSetor() { return "Cozinha"; }
}
