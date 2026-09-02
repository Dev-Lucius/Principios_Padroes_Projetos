package negocio;

public interface Estado {

    Estado pegarCogumelo(Mario mario);

    Estado pegarPena(Mario mario);

    Estado levarDano(Mario mario);

    Estado pegarFlor(Mario mario);

     // Default method (has a body, implementing classes can use or override)
    default void performExtraDuties() {
        System.out.println("Performing default extra duties.");
    }



}
