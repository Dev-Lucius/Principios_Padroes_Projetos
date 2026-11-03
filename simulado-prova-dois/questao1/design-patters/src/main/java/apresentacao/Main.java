package apresentacao;

import dominio.Italico;
import dominio.Negrito;
import dominio.Texto;
import dominio.TextoSimples;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        Texto txt = new TextoSimples("Design Patters");
    
        txt = new Negrito(txt);
        System.out.println(txt.renderizar());

        txt = new Italico(txt);
        System.out.println(txt.renderizar());
    }
}