package design_patters.dominio;

public class PrepararSobremesa extends SolicitacaoBase{
    private final Confeitaria confeitaria;

    public PrepararSobremesa(int id, int mesa, String sobremesa, Confeitaria confeitaria) {
        super(id, mesa, sobremesa);
        this.confeitaria = confeitaria;
    }

    @Override protected void prepararNoSetor(String item) { confeitaria.prepararSobremesa(item); }
    @Override protected String getSetor() { return "Confeitaria"; }
}
