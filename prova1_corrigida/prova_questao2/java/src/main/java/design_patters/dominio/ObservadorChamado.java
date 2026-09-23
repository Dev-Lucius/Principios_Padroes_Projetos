package design_patters.dominio;

/**
 * OBSERVER --> (Interface do Observador)
 * Todo Componente que queira acompanhar um chamado Implementa esta Interface
*/
public interface ObservadorChamado {
    void atualizar(Chamado chamado, StatusChamado statusAnterior);
}
