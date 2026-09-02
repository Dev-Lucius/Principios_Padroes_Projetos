package negocio;

public class MarioGrande implements Estado {
    private static final MarioGrande INSTANCE = new MarioGrande();

    private MarioGrande(){

    }

    public static synchronized MarioGrande getInstance(){
        return INSTANCE;
    }

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
        return new MarioFlor();
    }

}
