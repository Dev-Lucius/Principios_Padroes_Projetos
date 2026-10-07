/** Notificação básica: é o objeto no centro da "cebola" de decoradores. */
public class Email implements Notificacao {
    @Override
    public void enviar(String mensagem) {
        System.out.println("[Email] " + mensagem);
    }
}
