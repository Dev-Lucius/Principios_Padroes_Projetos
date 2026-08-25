package Exercicio5;

public class Main {

    public static void main(String[] args) {

        Exportador csv = new ExportadorCSV();
        Exportador json = new ExportadorJSON();
        Exportador xml = new ExportadorXML();

        csv.exportar();

        System.out.println();

        json.exportar();

        System.out.println();

        xml.exportar();
    }
}