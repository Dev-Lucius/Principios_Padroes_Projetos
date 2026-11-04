package dominio;

public abstract class DecoradorServico implements Servico{
    protected Servico servico;

    public DecoradorServico(Servico servico){
        this.servico = servico;
    }

    @Override
    public void enviar(String dados){
        servico.enviar(dados);
    } 

    @Override
    public void baixar(String dados){
        servico.baixar(dados);
    } 

    @Override
    public void consultarArquivos(String dados){
        servico.consultarArquivos(dados);
    } 
}
