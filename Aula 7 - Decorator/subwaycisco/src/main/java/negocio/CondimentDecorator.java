package negocio;

public abstract class CondimentDecorator extends Lanche {
    protected Lanche lanche;

    public CondimentDecorator(Lanche lanche){
        this.lanche = lanche;
        this.clienteNome = lanche.clienteNome;
    }

    public double preco(){
        return Double.valueOf(String.format("%.2f",this.preco  + this.lanche.preco()));
    }

    public String descricao(){
        return this.lanche.descricao()+"\n"+this.descricao+":"+this.preco;
    }

    public String getClienteNome(){
        return this.clienteNome;
    }

    // aqui, propriamente, eu removo o ultimo item
    @Override
    public Lanche removeItem(){
        return lanche;
    }   
   

}
