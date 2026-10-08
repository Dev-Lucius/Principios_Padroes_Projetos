package design_patters.apresentacao;

import design_patters.negocio.PlanoAdicionado;
import design_patters.negocio.SeguroBasico;
import design_patters.negocio.SeguroComRoubo;
import design_patters.negocio.SeguroComRouboEAssistencia;
import design_patters.negocio.SeguroComRouboECarroReserva;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        SeguroBasico segBas = new SeguroComRoubo();
        System.out.println(segBas.getDescricao());
        System.out.println(segBas.preco());

        SeguroBasico segCarroRes = new SeguroComRouboECarroReserva();

        segBas = new PlanoAdicionado(segCarroRes, segCarroRes.getDescricao(), segCarroRes.preco());
        System.out.println(segBas.getDescricao());
        System.out.println(segBas.preco());
    }
}