package design_patters.negocio;

/** Componente concreto: plano básico, cobre apenas colisões. É o "miolo da cebola". */
public class SeguroBasico implements Seguro {

    @Override
    public String getDescricao() {
        return "Seguro básico (colisão)";
    }

    @Override
    public double preco() {
        return 600.00;
    }
}
