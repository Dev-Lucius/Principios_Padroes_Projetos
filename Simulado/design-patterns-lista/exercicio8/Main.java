public class Main {
    public static void main(String[] args) {

        // Exemplo do enunciado: Email + SMS + Push + Log
        Notificacao notificacao = new Email();
        notificacao = new SMS(notificacao);
        notificacao = new Push(notificacao);
        notificacao = new Log(notificacao);

        notificacao.enviar("Pedido enviado");

        System.out.println();

        // Outra combinação montada em tempo de execução, sem classe nova
        Notificacao zap = new WhatsApp(new Email());
        zap.enviar("Pagamento aprovado");
    }
}
