package design_patters.negocio;

import java.util.List;

/**
 * TEMPLATE METHOD.
 *
 * minerar() é o esqueleto: fixa a ORDEM dos 4 passos e é final, então nenhuma
 * plataforma consegue alterá-la. Mudou o fluxo (ex.: passo de auditoria)?
 * Altera-se UM método, em UMA classe.
 *
 *  1. autenticar        -> VARIA por plataforma  (abstrato: toda subclasse é obrigada a implementar)
 *  2. capturarPosts     -> pode variar           (hook com implementação padrão)
 *  3. filtrarOfensivos  -> igual para todos      (concreto na base)
 *  4. salvar            -> igual para todos      (concreto na base)
 */
public abstract class AlgoritmoMineracao {

    private static final List<String> PALAVRAS_OFENSIVAS = List.of("idiota", "lixo");

    // ===== TEMPLATE METHOD =====
    public final void minerar() {
        System.out.println("== " + getClass().getSimpleName() + " ==");
        autenticar();
        List<String> brutos = capturarPostsBrutos();
        List<String> limpos = filtrarOfensivos(brutos);
        salvar(limpos);
        System.out.println();
    }

    // ----- Passo variável obrigatório -----
    protected abstract void autenticar();

    // ----- Passo variável opcional (hook) -----
    protected List<String> capturarPostsBrutos() {
        System.out.println("2. Capturando posts via endpoint REST padrão");
        return List.of("Adorei o produto", "Isso é um lixo", "Chegou rápido");
    }

    // ----- Passos fixos -----
    protected List<String> filtrarOfensivos(List<String> posts) {
        List<String> limpos = posts.stream()
                .filter(post -> PALAVRAS_OFENSIVAS.stream()
                        .noneMatch(palavra -> post.toLowerCase().contains(palavra)))
                .toList();
        System.out.println("3. Filtrando ofensivos: " + posts.size() + " -> " + limpos.size() + " post(s)");
        return limpos;
    }

    protected void salvar(List<String> posts) {
        System.out.println("4. Salvando no banco: " + posts);
    }
}
