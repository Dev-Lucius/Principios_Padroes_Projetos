package negocio;

// exemplo de condimento
public class Tomate extends CondimentDecorator {

    public Tomate(Lanche lanche) {
        super(lanche);
        this.descricao = "Tomate";
        this.preco = 10.00;
    }


  

}
