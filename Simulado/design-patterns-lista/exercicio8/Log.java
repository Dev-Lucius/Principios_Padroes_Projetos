public class Log extends DecoradorNotificacao {

    public Log(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem); // executa o que já estava empilhado
        System.out.println("[LOG] " + mensagem);
    }
}
