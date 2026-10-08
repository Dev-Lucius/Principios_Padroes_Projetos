package design_patters.apresentacao;

import design_patters.negocio.AlgoritmoMineracao;
import design_patters.negocio.ProcessadorInstagram;
import design_patters.negocio.ProcessadorTikTok;
import design_patters.negocio.ProcessadorTwitter;

public class Main {
    public static void main(String[] args) {

        AlgoritmoMineracao[] processadores = {
            new ProcessadorInstagram(),
            new ProcessadorTikTok(),
            new ProcessadorTwitter()
        };

        // Sempre a MESMA chamada: o fluxo vem da classe base, os detalhes da subclasse
        for (AlgoritmoMineracao processador : processadores) {
            processador.minerar();
        }
    }
}
