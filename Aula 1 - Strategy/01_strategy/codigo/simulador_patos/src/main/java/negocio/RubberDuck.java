package negocio;

public class RubberDuck extends Duck {

    public RubberDuck(){
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Squeak();
    }
}
