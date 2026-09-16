package apresentacao;

import negocio.H1;
import negocio.H2;
import negocio.MarkdownFile;
import negocio.Link;
import negocio.Markdown;

public class Main {
    public static void main(String[] args) {
        Markdown arquivoMD = new MarkdownFile();
        arquivoMD = new H1(arquivoMD, "Titulo");
        arquivoMD = new H2(arquivoMD, "SubTitulo");
        arquivoMD = new Link(arquivoMD, "Google", "http://www.google.com");
        System.out.println(arquivoMD.codigo());

    }
}