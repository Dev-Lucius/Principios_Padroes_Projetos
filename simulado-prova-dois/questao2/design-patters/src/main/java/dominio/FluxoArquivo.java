package dominio;

public class FluxoArquivo implements FluxoDados{

    private final String nomeArquivo;

    public FluxoArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void escrever(String dados) {
        System.out.println("Gravando no arquivo: " + nomeArquivo);
        System.out.println("Dados armazenados: " + dados);
    }    
}
