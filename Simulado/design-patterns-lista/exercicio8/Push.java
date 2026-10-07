public class Push extends DecoradorNotificacao {

    public Push(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem); // executa o que já estava empilhado
        System.out.println("[Push] " + mensagem);
    }
}
