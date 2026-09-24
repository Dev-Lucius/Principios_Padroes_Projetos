package design_patters.dominio;

public class PrepararBebida extends SolicitacaoBase{
    
    private final Bar bar;

    public PrepararBebida(int id, int mesa, String bebida, Bar bar) {
        super(id, mesa, bebida);
        this.bar = bar;
    }

    @Override protected void prepararNoSetor(String item) { bar.prepararBebida(item); }
    @Override protected String getSetor() { return "Bar"; }
}
