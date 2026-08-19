package apresentacao;

import negocio.Controle;
import negocio.HomeTheater;
import negocio.ModoCinemaCommand;
import negocio.Pipoqueira;
import negocio.PipoqueiraCommand;
import negocio.TelevisaoSony;
import negocio.TvSonyCommand;

public class Main {
    public static void main(String[] args) {
        TelevisaoSony televisaoSony = new TelevisaoSony();
        Pipoqueira pipoqueira = new Pipoqueira();
        HomeTheater homeTheater = new HomeTheater();
        Controle controle = new Controle();
        controle.alocaSlot(new ModoCinemaCommand(homeTheater, televisaoSony, pipoqueira), 0);
        // jaazielControle.alocaSlot(new TvSonyCommand(televisaoSony), 0);
        // jaazielControle.alocaSlot(new PipoqueiraCommand(pipoqueira), 1);
        controle.aperteiBotaoOn(0);
        // jaazielControle.aperteiBotaoOn(1);
        // jaazielControle.aperteiBotaoOn(2);
        // // jaazielControle.aperteiBotaoOff();
    }
}