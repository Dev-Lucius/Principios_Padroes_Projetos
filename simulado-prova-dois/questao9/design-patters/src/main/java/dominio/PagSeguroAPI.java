package dominio;

public class PagSeguroAPI {

    public void efetuarCobranca(String cartao, int valorEmCentavos){
        System.out.println("Pag Seguro: cobrando " + valorEmCentavos + " centavos");
    }
}
