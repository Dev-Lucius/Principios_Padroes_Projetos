public class SMS extends DecoradorNotificacao {

    public SMS(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem); // executa o que já estava empilhado
        System.out.println("[SMS] " + mensagem);
    }
}
