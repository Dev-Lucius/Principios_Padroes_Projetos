public class PagamentoCartaoCredito implements FormaPagamento{

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento Realizado Via Cartão - Crédito");
    }

    @Override
    public void parcelas(int n) {
        System.out.println("Parcelado em: " + n);
    }

    @Override
    public void processamento(int periodo) {
        System.out.println("Aprovação Instantênea");
    }
    
}