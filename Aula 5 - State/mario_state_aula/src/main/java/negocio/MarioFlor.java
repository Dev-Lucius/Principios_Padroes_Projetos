package negocio;

public class MarioFlor implements Estado {

    @Override
    public Estado pegarCogumelo(Mario mario) {
        return this;
    }

    @Override
    public Estado pegarPena(Mario mario) {
        return new MarioPena();
    }

    @Override
    public Estado levarDano(Mario mario) {
        return new MarioPequeno();
    }

    @Override
    public Estado pegarFlor(Mario mario) {
        return this;
    }

}
