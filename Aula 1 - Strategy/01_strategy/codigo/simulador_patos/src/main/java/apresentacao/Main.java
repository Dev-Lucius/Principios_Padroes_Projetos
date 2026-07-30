package apresentacao;

import negocio.FlyNoWay;
import negocio.MallardDuck;
import negocio.MuteQuack;
import negocio.QuackQuack;
import negocio.RubberDuck;

public class Main {
    public static void main(String[] args) {

        MallardDuck pato1 = new MallardDuck();
        pato1.performFly();
        // System.out.println("Tomou um tiro nas asas!");
        // pato1.setFlyBehavior(new FlyNoWay());
        pato1.performFly();
        pato1.performQuack();
        pato1.display();

        System.out.println("----------------");
        MallardDuck pato2 = new MallardDuck(new FlyNoWay(), new MuteQuack());
        pato2.performFly();
        pato2.performQuack();
        // pato2.display();

        RubberDuck patoRubber = new RubberDuck();
        patoRubber.display();

    }
}