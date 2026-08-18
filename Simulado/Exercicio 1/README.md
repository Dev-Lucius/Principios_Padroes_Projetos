# Coomo pensar na Solução

## Padrão Usado -> **Strategy**

## Justificativa  

O ponto principal do exercício é perceber que existe um mesmo objetivo, mas várias maneiras diferentes de realizá-lo.

o objetivo é:

> Realizar um Pagamento

As estratégias possíveis são:

```
Cartão de crédito
PIX
Boleto
PayPal
Carteira digital
```

**Cada uma possui uma implementação diferente.**

---

# Solução

## 1. Encontro o Comportamento que Varia e o Que se Mantêm Constante

Pergunte:

> "O que muda dependendo da situação?"

Podemos representar:

```
             REALIZAR PAGAMENTO
                    │
       ┌────────────┼────────────┐
       ↓            ↓            ↓
    Cartão         PIX        Boleto
```

**A ideia do padrão é justamente encapsular comportamentos diferentes atrás de uma mesma interface.**

---

## 2. Cria uma Abstração para Esse comportamento que Varia

Nesse caso, imaginamos essa abstração como uma Interface:

```java
public interface FormaPagamento {
    void pagar(double valor);
}
```

Observe algo importante:

A interface não sabe como o pagamento será realizado.

Ela apenas estabelece:

> **"Toda forma de pagamento precisa saber realizar um pagamento."**

## 3. Agora Implemente as Estratégias

Cada forma de pagamento implementa a interface.

Por exemplo:

```java
public class PagamentoCartao implements FormaPagamento {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento realizado com cartão.");
    }
}
```

E 

```java
public class PagamentoPix implements FormaPagamento {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento realizado via PIX.");
    }
}
```

Conceitualmente:

```
              FormaPagamento
                    ▲
          ┌─────────┼─────────┐
          │         │         │
       Cartão      PIX      Boleto
```

## 4. Implemente uma Classe Que use Essas estratégias

Veja este exemplo:

```java
public class Pedido {

    private FormaPagamento formaPagamento;

    public Pedido(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void pagar(double valor) {
        formaPagamento.pagar(valor);
    }
}
```

Perceba a diferença.

A classe ``Pedido`` não sabe se está usando:

```
Cartão
PIX
Boleto
PayPal
```

Ela só sabe de uma Coisa:

```
"Eu tenho uma FormaPagamento."
```

Isso reduz bastante o acoplamento.

### O uso ficaria assim:

```java
FormaPagamento pagamento = new PagamentoPix();

Pedido pedido = new Pedido(pagamento);

pedido.pagar(150.00);
```

Ou então...

```java
FormaPagamento pagamento = new PagamentoCartao();

Pedido pedido = new Pedido(pagamento);

pedido.pagar(150.00);
```

O ```Pedido``` continua exatamente igual.

Esse é um dos grandes ganhos do Strategy.