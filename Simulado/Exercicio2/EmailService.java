package Exercicio2;

public class EmailService implements Observer{

    @Override
    public void update(String mensagem){
        System.out.println(mensagem);
    }
}
