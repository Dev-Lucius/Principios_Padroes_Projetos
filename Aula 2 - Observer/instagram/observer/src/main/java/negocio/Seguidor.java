package negocio;

import java.util.ArrayList;
import java.util.List;

public class Seguidor implements Observer {
    private String nome;

    public Seguidor(String nome) {
        this.nome = nome;
    }

    @Override
    public void update(Post lastOne) {
        System.out.println(this.nome+":"+lastOne);
    }

}