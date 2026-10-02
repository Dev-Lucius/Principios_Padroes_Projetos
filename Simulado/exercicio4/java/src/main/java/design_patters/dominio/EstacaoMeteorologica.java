package design_patters.dominio;

import java.util.ArrayList;
import java.util.List;

public class EstacaoMeteorologica implements Subject{

    private final int codigo;
    private final String nome;
    private int temperatura;
    private Status status;

    List<Observer> observers = new ArrayList<>();

    public EstacaoMeteorologica(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
        this.temperatura = 0;
        this.status = Status.ESTAVEL;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getNome(){
        return nome;
    }

    public Status getStatus(){
        return status;
    }

    public int getTemperatura(){
        return temperatura;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public void setTemperatura(int temp){
        System.out.println("\tTemperatura Atualizada: " + temp);
        this.temperatura = temp;
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for(Observer observers : observers){
            System.out.println("");
            observers.atualizar(this);
        }
    }
}
