package Exercicio2;

import java.util.ArrayList;
import java.util.List;

public class ProgressoAluno implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int progresso;

    public void atualizarProgresso(int progresso) {
        this.progresso = progresso;

        notificarObservadores();
    }

    @Override
    public void anexar(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void separar(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservadores() {

        String mensagem = "Progresso atualizado para " + progresso + "%";

        for (Observer observer : observers) {
            observer.update(mensagem);
        }
    }
}