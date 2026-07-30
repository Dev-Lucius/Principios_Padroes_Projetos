# 🦆 Duck Simulator — Design Pattern Strategy

[![Language: Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![Pattern: Strategy](https://img.shields.io/badge/Pattern-Strategy-blue.svg)](https://refactoring.guru/design-patterns/strategy)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Este repositório contém a implementação do clássico **Simulador de Patos (SimUDuck)**, utilizado para demonstrar na prática a aplicação do padrão de projeto comportamental **Strategy** (baseado nos conceitos do livro *Head First Design Patterns* / *Padrões de Projeto*).

---

## 📌 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [O Problema](#-o-problema)
- [A Solução (Padrão Strategy)](#-a-solução-padrão-strategy)
- [Princípios de Design Aplicados](#-princípios-de-design-aplicados)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Diagrama de Classes](#-diagrama-de-classes)
- [Como Executar](#-como-executar)
- [Resultados Esperados](#-resultados-esperados)
- [Autor](#-autor)

---

## 📖 Sobre o Projeto

O **SimUDuck** é um simulador de patos capaz de exibir uma grande variedade de espécies de patos nadando, grasnando e voando. 

O objetivo principal deste exercício é demonstrar como a **herança tradicional** pode falhar quando comportamentos precisam mudar dinamicamente ou variar entre subclasses, e como a **composição e o padrão Strategy** resolvem esse problema de forma elegante e flexível.

---

## 🚨 O Problema

Inicialmente, a classe abstrata `Duck` definia comportamentos padrão como `quack()` e `fly()`. Porém, essa abordagem gerou problemas graves de manutenção:

1. **Efeitos colaterais indesejados:** Patos de borracha (`RubberDuck`) ou patos de madeira (`DecoyDuck`) herdavam o comportamento de voar (`fly()`), o que não fazia sentido.
2. **Duplicação de código:** Sobrescrever métodos em dezenas de subclasses gerava código duplicado e difícil de manter.
3. **Rigidez:** Não era possível alterar o comportamento de um pato em tempo de execução (dinamicamente).

---

## 💡 A Solução (Padrão Strategy)

O padrão **Strategy** sugere que você identifique os aspectos da sua aplicação que variam e os separe daqueles que permanecem iguais. 

Neste projeto:
1. **Encapsulamos o que varia:** Os comportamentos de **Voar** (`FlyBehavior`) e **Grasnar** (`QuackBehavior`) foram retirados da classe `Duck` e convertidos em **interfaces**.
2. **Composição sobre Herança:** A classe `Duck` agora *possui* referências para essas interfaces em vez de herdar a implementação.
3. **Comportamento Dinâmico:** É possível alterar a estratégia de voo ou grasno de qualquer pato em tempo de execução através de métodos *setter* (`setFlyBehavior`, `setQuackBehavior`).

---

## 📐 Princípios de Design Aplicados

- 🔹 **Encapsule o que varia:** Isolar comportamentos mutáveis (`FlyBehavior`, `QuackBehavior`).
- 🔹 **Favoreça a composição em relação à herança:** Um pato *tem um* comportamento de voo/grasno, em vez de *ser* um tipo de voador.
- 🔹 **Programe para interfaces, não para implementações:** A classe `Duck` se relaciona diretamente com as interfaces de comportamento, ignorando os detalhes das implementações concretas.

---

## 🛠️ Estrutura do Projeto

```text
src/
 ├── behaviors/
 │    ├── FlyBehavior.java            # Interface para comportamento de voo
 │    ├── FlyWithWings.java           # Implementação: Voa com asas
 │    ├── FlyNoWay.java               # Implementação: Não voa
 │    ├── FlyRocketPowered.java       # Implementação: Voo a jato
 │    ├── QuackBehavior.java          # Interface para comportamento de grasno
 │    ├── Quack.java                  # Implementação: Grasno normal
 │    ├── Squeak.java                 # Implementação: Apito de borracha
 │    └── MuteQuack.java              # Implementação: Silencioso
 ├── ducks/
 │    ├── Duck.java                   # Classe Abstrata Base
 │    ├── MallardDuck.java            # Pato Selvagem
 │    ├── RubberDuck.java             # Pato de Borracha
 │    └── ModelDuck.java              # Pato Modelo (Ajustável)
 └── MiniDuckSimulator.java           # Classe Principal (Main)

```

--- 

```java
classDiagram
    class Duck {
        <<abstract>>
        +FlyBehavior flyBehavior
        +QuackBehavior quackBehavior
        +performFly()
        +performQuack()
        +swim()
        +display()*
        +setFlyBehavior(FlyBehavior fb)
        +setQuackBehavior(QuackBehavior qb)
    }

    class FlyBehavior {
        <<interface>>
        +fly()
    }

    class QuackBehavior {
        <<interface>>
        +quack()
    }

    class FlyWithWings {
        +fly()
    }

    class FlyNoWay {
        +fly()
    }

    class FlyRocketPowered {
        +fly()
    }

    class Quack {
        +quack()
    }

    class Squeak {
        +quack()
    }

    class MuteQuack {
        +quack()
    }

    class MallardDuck {
        +display()
    }

    class ModelDuck {
        +display()
    }

    Duck --> FlyBehavior
    Duck --> QuackBehavior
    FlyWithWings ..|> FlyBehavior
    FlyNoWay ..|> FlyBehavior
    FlyRocketPowered ..|> FlyBehavior
    Quack ..|> QuackBehavior
    Squeak ..|> QuackBehavior
    MuteQuack ..|> QuackBehavior
    MallardDuck --|> Duck
    ModelDuck --|> Duck
```

--- 

## 🚀 Como Executar 

Pré-requisitos

    - Java JDK 8 ou superior instalado.
    - Git instalado na máquina

Passo a Passo

- 1. Clone o Repositório

```bash
git clone [https://github.com/seu-usuario/simulador-patos-strategy.git](https://github.com/seu-usuario/simulador-patos-strategy.git)
cd simulador-patos-strategy
```

- 2. Compile o Projeto

```bash
javac -d bin src/**/*.java
```

- 3. Execute a Aplicação

```bash
java -cp bin MiniDuckSimulator
```

--- 

## ✒️ Autor

Desenvolvido por [Seu Nome]

Desenvolvedor / Estudante de Ciência da Computação / Análise e Desenvolvimento de Sistemas

(Link Notion)[https://app.notion.com/p/Strategy-3ad7c6764d48804b8137fcf16f509d99?source=copy_link]