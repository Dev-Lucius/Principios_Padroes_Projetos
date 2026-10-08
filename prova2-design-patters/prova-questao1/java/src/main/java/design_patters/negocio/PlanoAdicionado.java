package design_patters.negocio;

public class PlanoAdicionado extends PlanoDecorator{

    public PlanoAdicionado(SeguroBasico seguroBasico, String name, double preco) {
        super(seguroBasico);
        this.descricao = name;
        this.preco = preco;
    }
}
