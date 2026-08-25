package Exercicio3;

public abstract class Relatorio{

    public final void gerarRelatorio(){
        buscarDados();
        processarDados();
        gerarArquivo();
        salvarArquivo();
    }

    protected abstract void buscarDados();
    protected abstract void processarDados();
    protected abstract void gerarArquivo();

    protected void salvarArquivo(){
        System.out.println("Arquivo Salvo com Sucesso!");
    }
}