public class PagamentoPix implements FormaPagamento{

    
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento Realizado Via PIX");
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
