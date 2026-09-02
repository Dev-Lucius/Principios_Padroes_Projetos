package negocio;

import persistencia.RepositoryEstado;

public class MarioPequeno implements Estado {

    @Override
    public Estado pegarCogumelo(Mario mario) {
        // return MarioGrande.getInstance();
        // performExtraDuties();
        System.out.println(RepositoryEstado.getInstance().getEstado("MarioGrande").hashCode());
        return RepositoryEstado.getInstance().getEstado("MarioGrande");
    }

    @Override
    public Estado pegarPena(Mario mario) {
        return new MarioPena();
    }

    @Override
    public Estado levarDano(Mario mario) {
        System.out.println("Mario perdeu uma vida:"+mario.getNroVidas());
        return new MarioMorto();
    }

    @Override
    public Estado pegarFlor(Mario mario) {
        return new MarioFlor();
    }

}
