package design_patters.dominio;

// RECEIVER --> sabe COMO preparar sobremessas
public class Confeitaria {
    public void prepararSobremesa(String sobremesa){
        System.out.println(" [Confeitaria] Preparando Sobremesa: " + sobremesa);
    }
}
