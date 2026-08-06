package negocio;

public class SiteDoIFRS implements Observer {

    @Override
    public void update(String message) {
        System.out.println("reescrevendo o site. colocando uma nova noticia já que o clima está assim:"+message);
    }

}