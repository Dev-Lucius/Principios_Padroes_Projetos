/**
 * DECORATOR.
 *
 * Por padrão, apenas repassa a mensagem para a notificação embrulhada.
 * Cada decorador concreto chama super.enviar(...) (executa o que já existia)
 * e DEPOIS acrescenta o seu próprio canal.
 *
 * Por isso a ordem de empilhamento define a ordem de execução:
 * new Log(new Push(new SMS(new Email()))) -> Email, SMS, Push, Log.
 */
public abstract class DecoradorNotificacao implements Notificacao {

    protected final Notificacao notificacao;

    protected DecoradorNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    @Override
    public void enviar(String mensagem) {
        notificacao.enviar(mensagem);
    }
}
