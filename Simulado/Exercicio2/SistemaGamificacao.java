package Exercicio2;

public class SistemaGamificacao implements Observer{
    
    @Override
    public void update(String mensagem){
        System.out.println(mensagem);
    }
}
