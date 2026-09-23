package design_patters.dominio;

/**
 * Contexto do Strategy
 * Pedido
 *  - pedido não deve reconhecer as regras de nenhuma modalidade -> ELE APENAS DELEGA O calculo para a estrategia que estiver setada
 */

public class Pedido {
    
    private final int numero;
    private final double valorProdutos;
    private final double pesoKg;
    private final double distanciaKm;
    private CalculoFrete modalidade;

    public Pedido(int numero, double valorProdutos, double pesoKg, double distanciaKm){
        this.numero = numero;
        this.valorProdutos = valorProdutos;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }

    // Permite Escolher e TROCAR a modalidade sem Alterar a classe
    public void setModalidade(CalculoFrete modalidade){
        this.modalidade = modalidade;
    }

    public double calcularFrete(){
        if(modalidade == null){
            throw new IllegalStateException("Nenhuma Modalidade de Entrega foi Escolhida");
        }
        return modalidade.calcular(this);
    }

    public double calcularTotal(){
        return valorProdutos + calcularFrete();
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public double getValorProdutos() {
        return valorProdutos;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public CalculoFrete getModalidade() {
        return modalidade;
    }

    
}
