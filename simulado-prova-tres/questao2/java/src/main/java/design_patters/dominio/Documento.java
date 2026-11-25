package design_patters.dominio;

public interface Documento {
    String ler();
    void editar(String novoConteudo);
    void excluir();
}
