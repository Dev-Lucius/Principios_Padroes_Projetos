public interface FormaPagamento{
    
    void pagar(double valor);
    void parcelas(int n);
    void processamento(int periodo);
}