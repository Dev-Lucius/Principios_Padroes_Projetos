package apresentacao;

import dominio.Compactar;
import dominio.Criptografia;
import dominio.Servico;
import dominio.ServicoBasico;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        // Serviço Inicial
        Servico s1 = new ServicoBasico("dados.txt");

        // Adicionando Criptografia
        s1 = new Criptografia(s1);

        // Adicionando Compactação
        s1 = new Compactar(s1);
    }
}