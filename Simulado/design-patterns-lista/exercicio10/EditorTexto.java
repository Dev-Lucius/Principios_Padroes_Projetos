/** RECEIVER: contém o texto e as operações básicas. Não conhece os comandos. */
public class EditorTexto {

    private final StringBuilder texto = new StringBuilder();
    private String ultimoSalvo = "";

    public void inserir(String trecho) {
        texto.append(trecho);
    }

    /** Remove até 'quantidade' caracteres do final e devolve o que foi removido. */
    public String removerFinal(int quantidade) {
        int n = Math.min(quantidade, texto.length());
        String removido = texto.substring(texto.length() - n);
        texto.setLength(texto.length() - n);
        return removido;
    }

    /** Salva o texto atual e devolve a versão salva ANTES (útil para desfazer). */
    public String salvar() {
        String anterior = ultimoSalvo;
        ultimoSalvo = texto.toString();
        return anterior;
    }

    public void restaurarUltimoSalvo(String versao) {
        ultimoSalvo = versao;
    }

    public String getTexto() {
        return texto.toString();
    }

    public String getUltimoSalvo() {
        return ultimoSalvo;
    }
}
