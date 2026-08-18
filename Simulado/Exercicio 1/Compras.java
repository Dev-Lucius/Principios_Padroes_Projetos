public class Compras {

    private final FormaPagamento formaPagamento;

    public Compras(FormaPagamento formaPagamento){
        this.formaPagamento = formaPagamento;
    }

    public void pagamento(double preco){
        formaPagamento.pagar(preco);
    }
}