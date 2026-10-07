import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportadorXML extends ImportadorDados {

    private static final Pattern ALUNO = Pattern.compile("<aluno>(.*?)</aluno>", Pattern.DOTALL);
    private static final Pattern TAG = Pattern.compile("<(\\w+)>([^<]*)</\\1>");

    public ImportadorXML(String nomeArquivo, String conteudo) {
        super(nomeArquivo, conteudo);
    }

    @Override
    protected void processarDados() {
        System.out.println("3. Processando XML (cada <aluno> vira um registro)");
        Matcher aluno = ALUNO.matcher(textoLido);

        while (aluno.find()) {
            Map<String, String> registro = new LinkedHashMap<>();
            Matcher tag = TAG.matcher(aluno.group(1));
            while (tag.find()) {
                registro.put(tag.group(1), tag.group(2).trim());
            }
            registros.add(registro);
        }
    }
}
