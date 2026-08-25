# Sistema de Frete

## Identificando o Padrão

Temos:

```text
Frete normal
Frete expresso
Frete econômico
Retirada na loja
```

Cada um possui uma maneira diferente de calcular.

Logo, precisar fazer o seguinte questionamento

> "Tenho Várias formas de realizar a mesma operação?"

Sim, esta operação em questão é a ``CALCULAR_FRETE``

As estratégias possíveis são:

```
Normal
Expresso
Econômico
Retirada
```

Isso nos leva novamente para: **Strategy**

A diferença para o exercício 1 é apenas o domínio

- No Exercício 1

```text
Pagamento
 ├── PIX
 ├── Cartão
 └── Boleto
```

- Agora:

```text
Frete
 ├── Normal
 ├── Expresso
 ├── Econômico
 └── Retirada
```

Quando estiver na prova:

"O comportamento pode ser substituído por outro comportamento equivalente?"

Aqui:

```java
calcularFrete();
```

Logo, pode ser feito de diversas maneiras. O que nos leva ao **Strategy**

# Implementação

## Interface Strategy

```java
public interface EstrategiaFrete {

    double calcular(double distancia, double peso);
}
```

## Estratégia Normal

```java
public class FreteNormal implements EstrategiaFrete {

    @Override
    public double calcular(double distancia, double peso) {

        return distancia * 2.0 + peso * 0.5;
    }
}
```

## Estratégia Expresso

```java
public class FreteExpresso implements EstrategiaFrete {

    @Override
    public double calcular(double distancia, double peso) {

        return distancia * 4.0 + peso * 1.0;
    }
}
```

## Estratégia Econômica

```java
public class FreteEconomico implements EstrategiaFrete {

    @Override
    public double calcular(double distancia, double peso) {

        return distancia * 1.5 + peso * 0.3;
    }
}
```

## Context

Agora precisamos da classe que usa a **estratégia**.

Essa é uma parte fundamental do **Strategy**.

```java
public class Pedido {

    private EstrategiaFrete estrategiaFrete;

    public Pedido(EstrategiaFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public void definirEstrategiaFrete(EstrategiaFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public double calcularFrete(double distancia, double peso) {
        return estrategiaFrete.calcular(distancia, peso);
    }
}
```

Este ``Pedido`` não sabe:

```
como calcular frete normal
como calcular frete expresso
como calcular frete econômico
```

Ela só sabe:

```text
"Tenho uma Estratégia de Frete."
```

## Main

```java
public class Main {

    public static void main(String[] args) {

        Pedido pedido = new Pedido(new FreteNormal());

        System.out.println(
                "Frete normal: " +
                pedido.calcularFrete(100, 5)
        );

        pedido.definirEstrategiaFrete(new FreteExpresso());

        System.out.println(
                "Frete expresso: " +
                pedido.calcularFrete(100, 5)
        );

        pedido.definirEstrategiaFrete(new FreteEconomico());

        System.out.println(
                "Frete econômico: " +
                pedido.calcularFrete(100, 5)
        );
    }
}
```

Observer esta Característica muito Importante:

```java
pedido.definirEstrategiaFrete(new FreteExpresso());
```

A estratégia pode mudar durante a execução.

Isso é uma das características clássicas do Strategy.

# Estrutura Mental

```
                    Pedido
                   Context
                      │
                      │ possui
                      ↓
              EstrategiaFrete
                      ▲
          ┌───────────┼───────────┐
          ↓           ↓           ↓
       Normal      Expresso    Econômico
```

Frase para decorar

> **Strategy** = "Tenho várias formas de fazer uma coisa e posso escolher qual usar."