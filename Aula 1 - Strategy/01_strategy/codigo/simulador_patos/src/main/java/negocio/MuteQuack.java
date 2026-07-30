package negocio;

public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Aqui n sai som nenhum!");
    }

}
