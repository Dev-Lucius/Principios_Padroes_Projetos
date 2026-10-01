package design_patters.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido implements Subject{
    private final UUID nroNota;
    private final String descricao;
    private final double valoTotal;
    private Status status;

    private Cliente cliente;
    private CalcularFrete frete;
    private List<Observer> vetObservadores = new ArrayList<>();

    public Pedido(UUID nroNota, String descricao, double valoTotal) {
        this.nroNota = nroNota;
        this.descricao = descricao;
        this.valoTotal = valoTotal;
        this.status = Status.PAGAMENTO_PENDENTE;
    }

    public void setFrete(CalcularFrete frete){
        this.frete = frete;
    }

    public double valorFrete(double peso, double distancia){
        return this.frete.calcular(peso, distancia);
    }

    @Override
    public void addObserver(Observer obs) {
        this.vetObservadores.add(obs);
    }

    @Override
    public void removeObsever(Observer obs) {
        this.vetObservadores.remove(obs);
    }

    @Override
    public void removeObserverPos(int pos) {
        this.vetObservadores.remove(pos);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < this.vetObservadores.size(); i++){
            this.vetObservadores.get(i).update(this);
        }
    }
    
    @Override
    public void changeStatus(Status status) {
        this.status = status;
        this.notifyObservers();
    }

    public UUID getNroNota() {
        return nroNota;
    }


    public String getDescricao() {
        return descricao;
    }


    public double getValoTotal() {
        return valoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public CalcularFrete getFrete() {
        return frete;
    }

    public List<Observer> getVetObservadores() {
        return vetObservadores;
    }
    
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pedido [nroNota=" + nroNota + ", descricao=" + descricao + ", valoTotal=" + valoTotal + "]";
    }
}
