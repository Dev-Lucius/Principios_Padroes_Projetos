package design_patters.negocio;

public abstract class PlanoDecorator extends SeguroBasico{

    protected SeguroBasico seguroBasico;

    public PlanoDecorator(SeguroBasico seguroBasico){
        this.seguroBasico = seguroBasico;
        this.seguroBasico.descricao = seguroBasico.getClass().getSimpleName();
    }

    public String getDescricao(){
        return this.seguroBasico.getDescricao() + "\n" + this.descricao;
    }

    public double preco(){
        return this.seguroBasico.preco()+ this.preco;
    }

    @Override 
    public SeguroBasico removerAdicional(){
        return this.seguroBasico;
    }
}
