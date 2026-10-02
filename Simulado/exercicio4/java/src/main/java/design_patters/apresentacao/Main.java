package design_patters.apresentacao;

import design_patters.dominio.AppMobile;
import design_patters.dominio.EstacaoMeteorologica;
import design_patters.dominio.Grafico;
import design_patters.dominio.PainelTemperatura;
import design_patters.dominio.SistemaAlerta;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        EstacaoMeteorologica estMet = new EstacaoMeteorologica(1, "Estação IF");

        estMet.addObserver(new PainelTemperatura("Painel 1", 1));
        estMet.addObserver(new AppMobile("Estação IF Mobile", 2));
        estMet.addObserver(new SistemaAlerta("Sistema Alerta Estação IF", 3));
        estMet.addObserver(new Grafico("Gráfico de Controle", 4));

        estMet.setTemperatura(25);

        estMet.notifyObserver();
    }
}