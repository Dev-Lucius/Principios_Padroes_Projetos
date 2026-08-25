package Exercicio5;

public abstract class Exportador {
    
    public final void exportar() {

        validarDados();
        buscarDados();
        registrarLog();
        transformarDados();
        gerarArquivo();
        finalizarExportacao();
    }

    protected abstract void validarDados();

    protected abstract void buscarDados();

    protected void registrarLog() {
        System.out.println("Registrando log...");
    }

    protected abstract void transformarDados();

    protected abstract void gerarArquivo();

    protected void finalizarExportacao() {
        System.out.println("Exportação finalizada.");
    }
}
