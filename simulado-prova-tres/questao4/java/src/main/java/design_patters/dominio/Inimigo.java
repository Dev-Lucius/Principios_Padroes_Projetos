package design_patters.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Inimigo {

    private String nome;
    private int vida;
    private final List<String> habilidades;

    // Construtor principal
    protected Inimigo(String nome, int vida, List<String> habilidades) {
        this.nome = nome;
        this.vida = vida;
        this.habilidades = new ArrayList<>(habilidades);
    }

    // Construtor de cópia
    protected Inimigo(Inimigo outro) {

        this.nome = outro.nome;
        this.vida = outro.vida;
        this.habilidades = new ArrayList<>(outro.habilidades);

    }

    // Método que será implementado pelas subclasses
    public abstract Inimigo clonar();

    // Getters
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

}