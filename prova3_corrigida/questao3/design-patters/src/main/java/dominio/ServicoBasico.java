package dominio;

public class ServicoBasico implements Servico{

    private final String arquivo;

    public ServicoBasico(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void enviar(String dados) {
        System.out.println("Enviando arquivos: " + dados);
    }

    @Override
    public void baixar(String dados) {
        System.out.println("Baixando arquivos: " + dados);
    }

    @Override
    public void consultarArquivos(String dados) {
        System.out.println("Consultando arquivos: " + dados);
    }
}
