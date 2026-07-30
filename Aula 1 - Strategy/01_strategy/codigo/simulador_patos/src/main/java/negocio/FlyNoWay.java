package negocio;

public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("Deu ruim! Não voo;");
    }

}
