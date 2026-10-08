package design_patters.negocio;

public abstract class SeguroBasico {
    protected  String descricao;
    protected  double preco;

    public SeguroBasico removerAdicional(){
        return this;
    }

    public String getDescricao(){
        return this.getClass().getSimpleName();
    }

    public abstract double preco();
}
