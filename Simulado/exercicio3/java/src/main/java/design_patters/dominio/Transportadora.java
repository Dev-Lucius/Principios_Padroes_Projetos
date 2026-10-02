package design_patters.dominio;

public class Transportadora implements Observer{

    private final String nomeTransportadora;
    
    public Transportadora(String nomeTransportadora){
        this.nomeTransportadora = nomeTransportadora;
    }

    public String getNomeTransportadora(){
        return nomeTransportadora;
    }

    @Override
    public void atualizar(Pedido pedido) {
        System.out.printf("\tTransportadora  %sFoi Atualizada sobre o Pedido \n", nomeTransportadora);
    }

}
