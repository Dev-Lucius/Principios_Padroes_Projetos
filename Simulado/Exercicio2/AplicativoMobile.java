package Exercicio2;

public class AplicativoMobile implements Observer{
    
    @Override
    public void update(String mensagem){
        System.out.println(mensagem);
    }

}
