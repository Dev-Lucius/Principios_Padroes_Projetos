# 1. Strategy — Ordenação de produtos

Uma aplicação de comércio eletrônico precisa permitir que os produtos sejam exibidos utilizando diferentes critérios de ordenação:

* menor preço;
* maior preço;
* nome em ordem alfabética;
* maior avaliação.

A classe `Catalogo` não deve conhecer as regras específicas de cada tipo de ordenação.

### Tarefas

Implemente uma solução utilizando **Strategy**.

Crie uma interface:

```java
public interface EstrategiaOrdenacao {
    void ordenar(List<Produto> produtos);
}
```

Crie pelo menos quatro estratégias:

```text
OrdenarPorMenorPreco
OrdenarPorMaiorPreco
OrdenarPorNome
OrdenarPorAvaliacao
```

A classe `Catalogo` deverá receber a estratégia e utilizá-la para ordenar seus produtos.

### Exemplo de utilização

```java
Catalogo catalogo = new Catalogo();

catalogo.setEstrategia(new OrdenarPorMenorPreco());
catalogo.ordenar();

catalogo.setEstrategia(new OrdenarPorNome());
catalogo.ordenar();
```

### Gabarito

**Padrão:** Strategy.

A ideia central é encapsular diferentes **algoritmos** em classes separadas.

```text
              EstrategiaOrdenacao
                      ▲
          ┌───────────┼───────────┐
          │           │           │
    MenorPreco      PorNome    Avaliacao
          │
          ▼
       Catalogo
```

A classe `Catalogo` depende da abstração:

```java
private EstrategiaOrdenacao estrategia;
```

e não diretamente de uma implementação específica.