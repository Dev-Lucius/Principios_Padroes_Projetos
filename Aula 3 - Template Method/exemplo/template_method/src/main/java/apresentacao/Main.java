package apresentacao;

import negocio.ExportacaoJSON;
import negocio.ExportacaoMD;
import negocio.ExportacaoPDF;
import negocio.ExportacaoSQL;

public class Main {
    public static void main(String[] args) {
        ExportacaoSQL exportarSQL = new ExportacaoSQL();
        exportarSQL.exportar();

        ExportacaoJSON exportarJSON = new ExportacaoJSON();
        exportarJSON.exportar();

        ExportacaoPDF exportarPDF = new ExportacaoPDF();
        exportarPDF.exportar();

        ExportacaoMD exportacaoMD = new ExportacaoMD();
        exportacaoMD.exportar();
    }
}