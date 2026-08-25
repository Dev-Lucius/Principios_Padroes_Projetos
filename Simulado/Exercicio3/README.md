# Exercício 3 - Processamento de Relatórios

## Como Identificar o Padrão

De início, vamos analisar o processo desse exercício

```text
1. Buscar os dados
2. Processar os dados
3. Gerar o relatório
4. Salvar o arquivo
```

Agora vamos comparar os Tipos:

```text
Financeiro
Vendas
Estoque
```

Perceba que todos possuem o **mesmo Fluxo**

Mas a questão é:

> "Todos seguem o mesmo algoritmo, mas determinadas etapas possuem implementações diferentes?"

Se a resposta for sim, então temos um **Template Method**

A ideia é:

```
              Algoritmo
                 │
      ┌──────────┴──────────┐
      ↓                     ↓
   etapas fixas        etapas variáveis
```

# Implementação

## O Erro a ser evitado !

A implementação ingênua seria algo como:

```java
class RelatorioFinanceiro {
    void gerar() {
        buscarDados();
        processarDados();
        gerarRelatorio();
        salvar();
    }
}
```

Em seguida:

```java
class RelatorioVendas {
    void gerar() {
        buscarDados();
        processarDados();
        gerarRelatorio();
        salvar();
    }
}
```

E novamente:

```java
class RelatorioEstoque {
    void gerar() {
        buscarDados();
        processarDados();
        gerarRelatorio();
        salvar();
    }
}
```

> Nesses casos, temos o mesmo algoritmo sendo implementado 3 vezes, **e nossa ideia é evitar isso**!

## Pense no Template Method como um "molde"

A classe base deve definir a seguinte ideia:

```
gerarRelatorio()
       │
       ├── buscarDados()
       ├── processarDados()
       ├── gerarArquivo()
       └── salvarArquivo()
```

O método ```gerarRelatorio()``` controla o fluxo.

As subclasses definem como cada etapa variável funciona.

## Aplicando o Template Method

### Classe Abstrata

```java
public abstract class Relatorio {

    // Este é o nosso Template Method
    // Ele define o Esqueleto do Algoritmo
    // e as Subclasses implementam exatamente essa estrutura
    public final void gerarRelatorio() {
        buscarDados();
        processarDados();
        gerarArquivo();
        salvarArquivo();
    }

    protected abstract void buscarDados();

    protected abstract void processarDados();

    protected abstract void gerarArquivo();

    protected void salvarArquivo() {
        System.out.println("Arquivo salvo.");
    }
}
```

### Subclasses

#### Relatório Financeiro

```java
public class RelatorioFinanceiro extends Relatorio {

    @Override
    protected void buscarDados() {
        System.out.println("Buscando dados financeiros...");
    }

    @Override
    protected void processarDados() {
        System.out.println("Processando dados financeiros...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando relatório financeiro em PDF...");
    }
}
```

#### Relatório de Vendas

```java
public class RelatorioVendas extends Relatorio {

    @Override
    protected void buscarDados() {
        System.out.println("Buscando dados de vendas...");
    }

    @Override
    protected void processarDados() {
        System.out.println("Processando dados de vendas...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando relatório de vendas em PDF...");
    }
}
```

#### Relatório De Estoque

```java
public class RelatorioEstoque extends Relatorio {

    @Override
    protected void buscarDados() {
        System.out.println("Buscando dados de estoque...");
    }

    @Override
    protected void processarDados() {
        System.out.println("Processando dados de estoque...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando relatório de estoque em PDF...");
    }
}
```

### Classe Main

```java
public class Main {

    public static void main(String[] args) {

        Relatorio financeiro = new RelatorioFinanceiro();
        Relatorio vendas = new RelatorioVendas();
        Relatorio estoque = new RelatorioEstoque();

        financeiro.gerarRelatorio();

        System.out.println();

        vendas.gerarRelatorio();

        System.out.println();

        estoque.gerarRelatorio();
    }
}
```

O mais importante é:

```java
financeiro.gerarRelatorio();
```

Aqui nunca teremos algo assim:

```java
financeiro.buscarDados();
financeiro.processarDados();
financeiro.gerarArquivo();
financeiro.salvarArquivo();
```

# Identificando Template Method

Procure palavras ou ideias como:

- "Todos seguem as mesmas etapas."
- "O fluxo deve permanecer igual."
- "Algumas etapas possuem implementações diferentes."
- "Evitar duplicação do algoritmo."

Então pense:

```text
MESMO FLUXO
+
ALGUMAS ETAPAS VARIAM
=
TEMPLATE METHOD
```