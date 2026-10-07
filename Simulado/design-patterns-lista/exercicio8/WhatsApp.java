public class WhatsApp extends DecoradorNotificacao {

    public WhatsApp(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem); // executa o que já estava empilhado
        System.out.println("[WhatsApp] " + mensagem);
    }
}
