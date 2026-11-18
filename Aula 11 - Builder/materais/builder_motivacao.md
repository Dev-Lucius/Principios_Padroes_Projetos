# Padrão de Projeto Builder

## 1. O que é o Builder?

O padrão **Builder** é um padrão de projeto criacional que permite construir objetos complexos passo a passo, separando o processo de construção da representação final do objeto.

Ele é especialmente útil quando um objeto possui muitos atributos, configurações opcionais ou diferentes combinações de características.

Em vez de criar um objeto por meio de um construtor gigantesco, com muitos parâmetros, ou instanciá-lo e configurar dezenas de atributos manualmente, o Builder permite realizar a construção de forma gradual, organizada e mais legível.

---

## 2. Problemas que motivam o uso do Builder

### Problema 1 — Construtores com muitos parâmetros

Imagine um sistema que representa um computador:

```java
Computador pc = new Computador(
    "Intel Core i7",
    32,
    1000,
    "RTX 4060",
    true,
    false,
    "Windows"
);
```

Esse tipo de construção apresenta alguns problemas:

- É difícil lembrar a ordem dos parâmetros.
- Muitos parâmetros podem ter valores semelhantes, como booleanos, números ou strings.
- Nem todos os parâmetros são obrigatórios.
- A leitura do código fica pouco intuitiva.
- Alterações no construtor podem afetar diversas partes do sistema.

O Builder permite substituir essa construção por algo mais expressivo:

```java
Computador pc = new Computador.Builder()
    .processador("Intel Core i7")
    .memoriaRAM(32)
    .armazenamento(1000)
    .placaVideo("RTX 4060")
    .sistemaOperacional("Windows")
    .build();
```

---

## 3. Aplicações práticas do Builder

### Montagem de computadores

Configurar processador, RAM, SSD, GPU, fonte e periféricos, permitindo diferentes combinações.

### Pedidos de alimentos

Montar pizzas, hambúrgueres ou combos com tamanhos, ingredientes, adicionais e opções de personalização.

### Requisições HTTP

Construir requisições com URL, método, cabeçalhos, autenticação, parâmetros e corpo, sem exigir tudo de uma vez.

### Personagens de jogos

Criar personagens configurando nome, classe, habilidades, equipamentos, atributos e aparência.

### Relatórios e documentos

Criar relatórios com títulos, autores, filtros, gráficos, seções, formatos e opções de exportação.

---

## 4. Motivação: muitas combinações de um mesmo objeto

Imagine um sistema de pedidos de uma pizzaria.

Uma pizza pode possuir:

- Tamanho;
- Tipo de massa;
- Tipo de borda;
- Sabores;
- Ingredientes adicionais;
- Observações;
- Desconto.

Nem todos os pedidos possuem todos esses atributos.

Sem Builder, poderíamos acabar criando diversos construtores:

```java
new Pizza("Grande");
new Pizza("Grande", "Integral");
new Pizza("Grande", "Integral", "Catupiry");
new Pizza("Grande", "Integral", "Catupiry", "Calabresa");
```

Além disso, poderíamos ter diversos construtores com combinações diferentes de parâmetros, gerando sobrecarga excessiva.

Com Builder:

```java
Pizza pizza = new Pizza.Builder()
    .tamanho("Grande")
    .massa("Integral")
    .borda("Catupiry")
    .adicionarSabor("Calabresa")
    .adicionarSabor("Frango")
    .adicionarIngrediente("Cebola")
    .build();
```

A construção fica mais flexível, pois o cliente escolhe quais características deseja definir.

---

## 5. Exemplo de implementação em Java

```java
public class Pizza {

    private String tamanho;
    private String massa;
    private String borda;
    private String sabor;

    private Pizza(Builder builder) {
        this.tamanho = builder.tamanho;
        this.massa = builder.massa;
        this.borda = builder.borda;
        this.sabor = builder.sabor;
    }

    public static class Builder {

        private String tamanho;
        private String massa;
        private String borda;
        private String sabor;

        public Builder tamanho(String tamanho) {
            this.tamanho = tamanho;
            return this;
        }

        public Builder massa(String massa) {
            this.massa = massa;
            return this;
        }

        public Builder borda(String borda) {
            this.borda = borda;
            return this;
        }

        public Builder sabor(String sabor) {
            this.sabor = sabor;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}
```

### Utilização

```java
Pizza pizza = new Pizza.Builder()
    .tamanho("Grande")
    .massa("Tradicional")
    .borda("Recheada")
    .sabor("Calabresa")
    .build();
```

O `Builder` é responsável por receber as configurações e, ao chamar `build()`, criar efetivamente o objeto `Pizza`.

---

## 6. Principais motivações resumidas

| Motivação                                             | Benefício                                         |
| ----------------------------------------------------- | ------------------------------------------------- |
| Muitos parâmetros no construtor                       | Evita construtores extensos e confusos            |
| Muitos atributos opcionais                            | Permite configurar somente o necessário           |
| Diferentes combinações de atributos                   | Facilita criar objetos com configurações variadas |
| Código de instanciação pouco legível                  | Torna a criação mais expressiva                   |
| Objetos que precisam de várias etapas de configuração | Organiza a construção progressiva                 |
| Necessidade de validar o objeto antes de criá-lo      | Centraliza validações no `build()`                |

---

## 7. Ideia central

O Builder não é motivado simplesmente pelo fato de uma classe possuir muitos atributos.

Ele é especialmente útil quando a criação de um objeto é complexa, possui muitas opções ou combinações e precisa ser organizada, legível e controlada.

Seu principal objetivo é **simplificar e organizar o processo de construção de objetos complexos**.

Corrigir o exemplo completo da PizzaAdicionar validação obrigatória no build
