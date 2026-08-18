public class PagamentoCartaoDebito implements FormaPagamento{

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento Realizado via Cartão - Débito");
    }
    
    @Override
    public void parcelas(int n) {
        System.out.println("Sem Parcelas");
    }

    @Override
    public void processamento(int periodo) {
        System.out.println("Aprovação Instantênea");
    }
}
