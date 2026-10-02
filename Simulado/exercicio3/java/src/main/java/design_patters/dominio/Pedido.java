package design_patters.dominio;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Subject{
    
    private final int id;
    private final int qtdEstoque;
    private final double valorTotal;
    private Status status = Status.PENDENTE;

    private List<Observer> observers = new ArrayList<>();
    
    public Pedido(int id, int qtdEstoque, double valorTotal) {
        this.id = id;
        this.qtdEstoque = qtdEstoque;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public double getValorTotal() {
        return valorTotal;
    }

    // getter para o status
    public Status getStatus(){
        return status;
    }

    // Alterando o Status
    public void alterarStatus(Status status){
        this.status = status;
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
        for(Observer observers: observers){
            System.out.printf("Pedido N° %d Foi Atualizado com Sucesso", id);
            observers.atualizar(this);
        }
    }


}
