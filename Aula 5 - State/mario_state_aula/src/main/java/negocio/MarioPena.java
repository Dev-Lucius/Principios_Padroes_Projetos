package negocio;

public class MarioPena implements Estado {

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
        return new MarioPequeno();
    }

    @Override
    public Estado pegarFlor(Mario mario) {
        
        return new MarioFlor();
    }

}
