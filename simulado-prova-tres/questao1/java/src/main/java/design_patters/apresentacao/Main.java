
package design_patters.apresentacao;

import design_patters.dominio.FluxoArquivo;
import design_patters.dominio.FluxoBuffer;
import design_patters.dominio.FluxoComLog;
import design_patters.dominio.FluxoCompactado;
import design_patters.dominio.FluxoCriptografado;
import design_patters.dominio.FluxoDados;

public class Main {

    public static void main(String[] args) {

        // 1. Criando o fluxo base
        FluxoDados fluxo = new FluxoArquivo("dados.txt");

        // 2. Adicionando criptografia
        fluxo = new FluxoCriptografado(fluxo);

        // 3. Adicionando compactação
        fluxo = new FluxoCompactado(fluxo);

        // 4. Adicionando buffer
        fluxo = new FluxoBuffer(fluxo);

        // 5. Adicionando log
        fluxo = new FluxoComLog(fluxo);

        // 6. Escrevendo os dados
        fluxo.escrever("Informação confidencial");
    }
}
