package design_patters.dominio;

public class FluxoArquivo implements  FluxoDados{

    private final String nomeArquivo;

    public FluxoArquivo(String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void escrever(String dados) {
        System.out.println("Gravando no Arquivo: " + nomeArquivo);
        System.out.println("Conteúdo: " + dados);
    }

}
