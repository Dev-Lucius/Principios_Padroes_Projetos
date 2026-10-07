import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TEMPLATE METHOD.
 *
 * importar() fixa a sequência: abrir -> ler -> processar -> validar -> salvar -> fechar.
 *
 * Aqui há dois tipos de etapa:
 *  - COMUNS (abrir, ler, validar, salvar, fechar): implementadas UMA vez na superclasse,
 *    evitando duplicar código nas três subclasses;
 *  - VARIÁVEIS (processarDados): abstrata, pois interpretar CSV, JSON ou XML é diferente.
 *
 * O try/finally dentro do template garante que o arquivo é SEMPRE fechado,
 * mesmo que a validação falhe. Como o fluxo está num único lugar, essa garantia
 * vale automaticamente para todos os formatos.
 */
public abstract class ImportadorDados {

    protected final String nomeArquivo;
    private final String conteudoBruto; // simula o conteúdo do arquivo em disco

    protected String textoLido;                                     // saída de lerDados()
    protected final List<Map<String, String>> registros = new ArrayList<>(); // saída de processarDados()
    private final List<Map<String, String>> banco = new ArrayList<>();       // simula o banco de dados

    protected ImportadorDados(String nomeArquivo, String conteudoBruto) {
        this.nomeArquivo = nomeArquivo;
        this.conteudoBruto = conteudoBruto;
    }

    // ===== TEMPLATE METHOD =====
    public final void importar() {
        abrirArquivo();
        try {
            lerDados();
            processarDados();
            validarDados();
            salvarDados();
        } finally {
            fecharArquivo();
        }
    }

    // ===== Etapas comuns =====
    protected void abrirArquivo() {
        System.out.println("1. Abrindo " + nomeArquivo);
    }

    protected void lerDados() {
        textoLido = conteudoBruto;
        System.out.println("2. Lendo dados (" + textoLido.length() + " caracteres)");
    }

    protected void validarDados() {
        for (Map<String, String> registro : registros) {
            String nome = registro.get("nome");
            if (nome == null || nome.isBlank()) {
                throw new IllegalStateException("Registro inválido (nome ausente): " + registro);
            }
        }
        System.out.println("4. Validação OK: " + registros.size() + " registro(s)");
    }

    protected void salvarDados() {
        banco.addAll(registros);
        System.out.println("5. Salvando " + registros.size() + " registro(s)");
    }

    protected void fecharArquivo() {
        System.out.println("6. Fechando " + nomeArquivo);
    }

    // ===== Etapa variável =====
    protected abstract void processarDados();

    public List<Map<String, String>> getBanco() {
        return List.copyOf(banco);
    }
}
