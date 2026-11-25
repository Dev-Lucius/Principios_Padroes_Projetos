package design_patters.dominio;

import java.util.List;

public class Orc extends Inimigo {

    // Configuração inicial do Orc
    public Orc() {
        super(
            "Orc",
            120,
            List.of("Golpe pesado", "Grito de guerra")
        );

    }

    // Construtor de cópia
    private Orc(Orc outro) {

        super(outro);

    }

    @Override
    public Orc clonar() {

        return new Orc(this);

    }

}
