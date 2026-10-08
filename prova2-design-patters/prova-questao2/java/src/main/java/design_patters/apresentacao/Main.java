package design_patters.apresentacao;

import design_patters.negocio.API;
import design_patters.negocio.AlgoritmoMineracao;
import design_patters.negocio.CapturaPostsBrutos;
import design_patters.negocio.FiltrarPalavras;
import design_patters.negocio.SalvarBancoDados;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");


        AlgoritmoMineracao processoAPI = new API();
        processoAPI.processoMineracao();

        AlgoritmoMineracao processoPosts = new CapturaPostsBrutos();
        processoPosts.processoMineracao();

        AlgoritmoMineracao processoFil = new FiltrarPalavras();
        processoFil.processoMineracao();

        AlgoritmoMineracao processoSave = new SalvarBancoDados();
        processoSave.processoMineracao();

    }
}