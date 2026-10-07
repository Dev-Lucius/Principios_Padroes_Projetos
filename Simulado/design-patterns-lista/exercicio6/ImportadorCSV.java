import java.util.LinkedHashMap;
import java.util.Map;

public class ImportadorCSV extends ImportadorDados {

    public ImportadorCSV(String nomeArquivo, String conteudo) {
        super(nomeArquivo, conteudo);
    }

    @Override
    protected void processarDados() {
        System.out.println("3. Processando CSV (1ª linha = cabeçalho, vírgula = separador)");
        String[] linhas = textoLido.split("\\R");
        String[] colunas = linhas[0].split(",");

        for (int i = 1; i < linhas.length; i++) {
            if (linhas[i].isBlank()) continue;
            String[] valores = linhas[i].split(",", -1);

            Map<String, String> registro = new LinkedHashMap<>();
            for (int c = 0; c < colunas.length; c++) {
                registro.put(colunas[c].trim(), c < valores.length ? valores[c].trim() : "");
            }
            registros.add(registro);
        }
    }
}
