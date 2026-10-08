package design_patters.negocio;

import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVER - SUBJECT.
 *
 * O Personagem só conhece a interface Observer. Ele NÃO sabe quantos nem quais
 * painéis existem (barra de vida, áudio, conquistas...). Um painel novo é
 * apenas mais um addObserver(); esta classe nunca mais precisa ser alterada.
 *
 * O disparo é AUTOMÁTICO: cada setter que muda um atributo chama notifyObservers().
 */
public class Personagem implements Subject<Personagem> {

    private int vida;
    private int mana;
    private int nivel;

    private final List<Observer<Personagem>> observadores = new ArrayList<>();

    public Personagem(int vida, int mana, int nivel) {
        this.vida = vida;
        this.mana = mana;
        this.nivel = nivel;
    }

    // ----- Gerência da lista de assinantes -----
    @Override
    public void addObserver(Observer<Personagem> observer) {
        observadores.add(observer);
    }

    @Override
    public void removeObserver(Observer<Personagem> observer) {
        observadores.remove(observer);
    }

    @Override
    public void notifyObservers() {
        // Itera sobre uma cópia: um observador pode se remover durante o aviso sem quebrar o laço
        for (Observer<Personagem> observer : new ArrayList<>(observadores)) {
            observer.update(this);
        }
    }

    // ----- Atributos: ao mudar, avisam sozinhos -----
    public void setVida(int vida) {
        if (this.vida != vida) {
            this.vida = vida;
            notifyObservers();
        }
    }

    public void setMana(int mana) {
        if (this.mana != mana) {
            this.mana = mana;
            notifyObservers();
        }
    }

    public void setNivel(int nivel) {
        if (this.nivel != nivel) {
            this.nivel = nivel;
            notifyObservers();
        }
    }

    public int getVida()  { return vida; }
    public int getMana()  { return mana; }
    public int getNivel() { return nivel; }
}
