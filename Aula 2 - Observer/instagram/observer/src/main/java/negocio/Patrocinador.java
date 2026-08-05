package negocio;

public class Patrocinador implements Observer {

    @Override
    public void update(Post lastOne) {
        // TODO Auto-generated method stub
        System.out.println("Patrocinador"+this.hashCode()+" recebeu um novo post:"+lastOne);
    }

}
