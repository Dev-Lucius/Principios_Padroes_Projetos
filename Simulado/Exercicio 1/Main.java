public class Main{
    public static void main(String[] args){

        // Via Pix
        FormaPagamento pagamento1 = new PagamentoPix();
        Compras compras = new Compras(pagamento1);
        compras.pagamento(150.00);

        // Via Débito no Cartão
        FormaPagamento pagamento2 = new PagamentoCartaoDebito();
        Compras compras2 = new Compras(pagamento2);
        compras2.pagamento(1000.00);
    }
}