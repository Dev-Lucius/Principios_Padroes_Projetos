package negocio;

public class MarioMorto implements Estado {

    @Override
    public Estado pegarCogumelo(Mario mario) {
        return this;
    }

    @Override
    public Estado pegarPena(Mario mario) {
        return this;
    }

    @Override
    public Estado levarDano(Mario mario) {
        return this;
    }

    @Override
    public Estado pegarFlor(Mario mario) {
        return this;
    }

}
