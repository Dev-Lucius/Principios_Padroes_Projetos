package design_patters.negocio;

/** Componente: contrato comum do plano básico E de todos os opcionais. */
public interface Seguro {
    String getDescricao();

    double preco();
}
