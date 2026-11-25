package design_patters.dominio;

public class AcessoNegadoException extends RuntimeException{
    public AcessoNegadoException(String mensagem) {
        super(mensagem);
    }
}
