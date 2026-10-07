import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportadorJSON extends ImportadorDados {

    private static final Pattern OBJETO = Pattern.compile("\\{([^}]*)\\}");
    private static final Pattern PAR = Pattern.compile("\"(\\w+)\"\\s*:\\s*\"?([^\",}]*)\"?");

    public ImportadorJSON(String nomeArquivo, String conteudo) {
        super(nomeArquivo, conteudo);
    }

    @Override
    protected void processarDados() {
        System.out.println("3. Processando JSON (cada { } vira um registro)");
        Matcher objeto = OBJETO.matcher(textoLido);

        while (objeto.find()) {
            Map<String, String> registro = new LinkedHashMap<>();
            Matcher par = PAR.matcher(objeto.group(1));
            while (par.find()) {
                registro.put(par.group(1), par.group(2).trim());
            }
            registros.add(registro);
        }
    }
}
