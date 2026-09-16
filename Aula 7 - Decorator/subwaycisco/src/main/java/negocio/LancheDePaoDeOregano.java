package negocio;

// uma das n opcoes de lanche (Concreto)
public class LancheDePaoDeOregano extends Lanche {

    @Override
    public double preco() {
        return 1.99;
    }

    @Override
    public String descricao() {
        return "Lanche de Pão de Oregano:"+preco();
    }

   



}
