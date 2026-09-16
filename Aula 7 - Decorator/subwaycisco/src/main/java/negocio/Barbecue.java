package negocio;

// exemplo de condimento
public class Barbecue extends CondimentDecorator {

    public Barbecue(Lanche lanche) {
        super(lanche);
        this.descricao = this.getClass().getSimpleName();
        this.preco = 10;
    }

  
   

}
