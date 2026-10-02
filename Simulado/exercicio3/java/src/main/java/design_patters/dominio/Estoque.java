package design_patters.dominio;

public class Estoque implements Observer{

    private final String nome;
    private final int nroRamal; 

    public Estoque(String nome, int nroRamal) {
        this.nome = nome;
        this.nroRamal = nroRamal;
    }

    public String getNome() {
        return nome;
    }

    public int getNroRamal() {
        return nroRamal;
    }

    @Override
    public void atualizar(Pedido pedido) {
        System.out.printf("\tEstoque %s foi Avisado Sobre o Pedido \n", nome);
    }

}
