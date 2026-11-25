package design_patters.dominio;

import java.util.List;

public class Goblin extends Inimigo {

    public Goblin() {

        super(
            "Goblin",
            70,
            List.of("Ataque rápido", "Esquiva")
        );

    }

    private Goblin(Goblin outro) {

        super(outro);

    }

    @Override
    public Goblin clonar() {

        return new Goblin(this);

    }

}