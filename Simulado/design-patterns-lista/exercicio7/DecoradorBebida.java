/**
 * DECORATOR.
 *
 * O decorador IMPLEMENTA Bebida (para poder ser usado no lugar de uma bebida)
 * e TAMBÉM GUARDA uma Bebida (a que ele embrulha). É essa dupla relação
 * ("é uma" + "tem uma") que permite empilhar quantos adicionais quiser.
 *
 * Cada adicional faz sempre o mesmo: pega o resultado da bebida embrulhada
 * e acrescenta a sua parte (texto na descrição, valor no preço).
 */
public abstract class DecoradorBebida implements Bebida {

    protected final Bebida bebida;

    protected DecoradorBebida(Bebida bebida) {
        this.bebida = bebida;
    }
}
