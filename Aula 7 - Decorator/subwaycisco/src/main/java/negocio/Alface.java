package negocio;

// exemplo de condimento
public class Alface extends CondimentDecorator {

    public Alface(Lanche lanche) {
        super(lanche);
        this.descricao = "Alface";
        this.preco = 10.00;
    }
 

}
