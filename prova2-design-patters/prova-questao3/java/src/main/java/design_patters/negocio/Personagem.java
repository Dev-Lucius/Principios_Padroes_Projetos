package design_patters.negocio;

import java.util.ArrayList;
import java.util.List;

public class Personagem implements Subject{

    private int nroVidas;
    private int mana;
    private int nivel;

    public Personagem(int nroVidas, int mana, int nivel) {
        this.nroVidas = nroVidas;
        this.mana = mana;
        this.nivel = nivel;
    }

    private Equipe equipe;
    private List<Observer> vetObservadores = new ArrayList<>();

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
        for (int i = 0; i < this.vetObservadores.size(); i++) {
            this.vetObservadores.get(i).update(this);
        }
    }

    public List<Observer> getVetObservadores() {
        return vetObservadores;
    }

    public void setNroVidas(int nroVidas) {
        this.nroVidas = nroVidas;
    }
    
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Personagem [nroVidas=" + nroVidas + ", mana=" + mana + ", nivel=" + nivel + ", equipe=" + equipe
                + ", vetObservadores=" + vetObservadores + "]";
    }
}
