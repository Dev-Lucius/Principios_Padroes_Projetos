package apresentacao;

import negocio.Influenciador;
import negocio.Patrocinador;
import negocio.Post;
import negocio.Seguidor;

public class Main {
    public static void main(String[] args) {
        Influenciador chicoBarney = new Influenciador();
        Seguidor igor = new Seguidor("Igor");
        chicoBarney.addSubscribe(igor);
        chicoBarney.addSubscribe(new Seguidor("Jaaziel"));
        chicoBarney.changedState(new Post("babu brigou com a ana paula"));
        chicoBarney.removeSubscribe(igor);
        chicoBarney.addSubscribe(new Patrocinador());
        chicoBarney.changedState(new Post("jonas ganhou o bbb"));
    }
}