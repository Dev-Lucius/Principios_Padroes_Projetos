package Exercicio5;

public class ExportadorJSON extends Exportador {

    @Override
    protected void validarDados() {
        System.out.println("Validando dados para JSON...");
    }

    @Override
    protected void buscarDados() {
        System.out.println("Buscando clientes...");
    }

    @Override
    protected void transformarDados() {
        System.out.println("Transformando dados para JSON...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando arquivo JSON...");
    }
}