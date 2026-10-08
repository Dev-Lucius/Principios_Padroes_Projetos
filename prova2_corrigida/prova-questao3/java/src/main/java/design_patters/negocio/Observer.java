package design_patters.negocio;

/** Contrato de quem quer ser avisado. O tipo genérico T é o tipo do assunto observado. */
public interface Observer<T> {
    void update(T assunto);
}
