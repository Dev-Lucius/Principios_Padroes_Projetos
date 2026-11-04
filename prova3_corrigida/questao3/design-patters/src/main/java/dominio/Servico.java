package dominio;

public interface Servico {
    void enviar(String dados);
    void baixar(String dados);
    void consultarArquivos(String dados);
}
