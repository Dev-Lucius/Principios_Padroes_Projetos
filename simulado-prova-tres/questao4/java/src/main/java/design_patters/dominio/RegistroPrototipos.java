package design_patters.dominio;

import java.util.HashMap;
import java.util.Map;

public class RegistroPrototipos {

    private final Map<String, Inimigo> prototipos = new HashMap<>();

    // Registra um protótipo
    public void registrar(String chave, Inimigo prototipo) {

        prototipos.put(chave, prototipo);

    }

    // Cria um novo inimigo a partir do protótipo
    public Inimigo criar(String chave) {

        Inimigo prototipo = prototipos.get(chave);

        if (prototipo == null) {

            throw new IllegalArgumentException(
                "Protótipo inexistente: " + chave
            );

        }

        return prototipo.clonar();

    }

}