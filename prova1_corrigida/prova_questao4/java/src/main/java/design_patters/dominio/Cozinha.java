package design_patters.dominio;

// RECEIVER --> Sabe COMO preparar pratos principais
public class Cozinha {
    public void prepararPrato(String prato){
        System.out.println("  [Cozinha] Preparando prato principal: " + prato);
    }
}
