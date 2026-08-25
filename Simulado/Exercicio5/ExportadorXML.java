package Exercicio5;

public class ExportadorXML extends Exportador {

    @Override
    protected void validarDados() {
        System.out.println("Validando dados para XML...");
    }

    @Override
    protected void buscarDados() {
        System.out.println("Buscando clientes...");
    }

    @Override
    protected void transformarDados() {
        System.out.println("Transformando dados para XML...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando arquivo XML...");
    }
}