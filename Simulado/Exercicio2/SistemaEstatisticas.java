package Exercicio2;

public class SistemaEstatisticas implements Observer{
    
    @Override
    public void update(String mensagem){
        System.out.println(mensagem);
    }

}
