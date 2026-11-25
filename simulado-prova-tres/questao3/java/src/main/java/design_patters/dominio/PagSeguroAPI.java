package design_patters.dominio;

public class PagSeguroAPI {

    public void efetuarCobranca(String cartao, int valorEmCentavos) {
        System.out.println("PagSeguro: cobrando " + valorEmCentavos + " centavos");
    }

}
