package design_patters.dominio;

// RECEIVER --> sabe COMO preparar bebidas e coqueteis
public class Bar {
    public void prepararBebida(String bebida) {
        System.out.println("  [Bar] Preparando bebida: " + bebida);
    }
}
