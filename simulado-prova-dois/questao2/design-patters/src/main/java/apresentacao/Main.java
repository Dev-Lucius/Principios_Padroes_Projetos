package apresentacao;

import dominio.FluxoArquivo;
import dominio.FluxoBuffer;
import dominio.FluxoComLog;
import dominio.FluxoCompactado;
import dominio.FluxoCriptografado;
import dominio.FluxoDados;

public class Main {

    public static void main(String[] args) {

        // 1. Criamos o fluxo original.
        FluxoDados fluxo = new FluxoArquivo("dados.txt");

        // 2. Adicionamos a criptografia.
        fluxo = new FluxoCriptografado(fluxo);

        // 3. Adicionamos a compactação.
        fluxo = new FluxoCompactado(fluxo);

        // 4. Adicionamos o registro de logs.
        fluxo = new FluxoComLog(fluxo);

        // 5. Adicionamos o buffer.
        FluxoBuffer buffer = new FluxoBuffer(fluxo);

        // 6. Escrevemos os dados.
        buffer.escrever("Informação confidencial");

        // 7. Descarregamos o buffer para o arquivo.
        buffer.descarregar();
    }
}