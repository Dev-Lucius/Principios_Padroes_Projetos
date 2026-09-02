package persistencia;

import java.util.HashMap;
import java.util.Map;

import negocio.Estado;
import negocio.Mario;
import negocio.MarioFlor;
import negocio.MarioGrande;
import negocio.MarioMorto;
import negocio.MarioPena;
import negocio.MarioPequeno;

public class RepositoryEstado {
    private Map<String, Estado> vetEstado;

    private static final RepositoryEstado INSTANCE = new RepositoryEstado();

    private RepositoryEstado() {
        this.vetEstado = new HashMap<String, Estado>();

        MarioPequeno marioPequeno = new MarioPequeno();
        MarioFlor marioFlor = new MarioFlor();
        MarioGrande marioGrande = MarioGrande.getInstance();
        MarioMorto marioMorto = new MarioMorto();
        MarioPena marioPena = new MarioPena();

        vetEstado.put(marioPequeno.getClass().getSimpleName(), marioPequeno);
        vetEstado.put(marioFlor.getClass().getSimpleName(), marioFlor);
        vetEstado.put(marioGrande.getClass().getSimpleName(), marioGrande);
        vetEstado.put(marioMorto.getClass().getSimpleName(), marioMorto);
        vetEstado.put(marioPena.getClass().getSimpleName(), marioPena);

    }

    public Estado getEstado(String nome){
        return this.vetEstado.get(nome);
    }

    public static synchronized RepositoryEstado getInstance(){
        return INSTANCE;
    }

}
