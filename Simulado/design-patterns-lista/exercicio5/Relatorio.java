/**
 * TEMPLATE METHOD.
 *
 * gerar() é o "template": fixa a ORDEM das etapas e é final para que nenhuma
 * subclasse consiga alterar a sequência. Cada etapa é abstrata e fica a cargo
 * da subclasse (PDF, HTML, CSV).
 *
 * Algoritmo geral = fixo (superclasse) | Etapas específicas = variáveis (subclasses)
 */
public abstract class Relatorio {

    public final void gerar() {
        abrir();
        cabecalho();
        dados();
        rodape();
        fechar();
    }

    protected abstract void abrir();

    protected abstract void cabecalho();

    protected abstract void dados();

    protected abstract void rodape();

    protected abstract void fechar();
}
