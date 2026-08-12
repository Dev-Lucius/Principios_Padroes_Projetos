# 📐 Princípios e Padrões de Projeto de Software

> Repositório de estudo e referência sobre fundamentos, princípios, padrões e boas práticas de projeto de software.

---

## 📋 Sumário

- [Conceitos Fundamentais de Projeto](#-conceitos-fundamentais-de-projeto)
- [Decisões de Projeto](#-decisões-de-projeto)
- [Princípios de Projeto de Software](#-princípios-de-projeto-de-software)
- [Refatoração](#-refatoração)
- [Padrões de Projeto (Design Patterns)](#-padrões-de-projeto-design-patterns)
- [Anti-Padrões](#-anti-padrões)
- [Princípios e Padrões de Arquitetura](#-princípios-e-padrões-de-arquitetura)
- [Referências](#-referências)

---

## 🏗️ Conceitos Fundamentais de Projeto

Os pilares que sustentam todo projeto de software de qualidade.

| Conceito | Descrição |
|----------|-----------|
| **Hierarquia** | Organização de classes e componentes em níveis de responsabilidade, promovendo reutilização e clareza estrutural. |
| **Abstração** | Foco nos aspectos essenciais de uma entidade, ocultando detalhes de implementação. |
| **Modularização** | Divisão do sistema em módulos coesos e de baixo acoplamento, facilitando manutenção e evolução. |
| **Encapsulamento** | Proteção do estado interno de objetos, expondo apenas interfaces controladas de interação. |

---

## ⚖️ Decisões de Projeto

Escolhas críticas na modelagem de sistemas orientados a objetos.

### Herança (`is-a`)
Relacionamento de especialização/generalização. Útil quando há verdadeira hierarquia semântica.

> ⚠️ **Cuidado:** herança cria acoplamento forte. Prefira composição quando a relação não for estritamente de especialização.

### Composição (`has-a`)
Montagem de comportamentos através da agregação de objetos. Promove flexibilidade e reutilização.

### Delegação
Transferência de responsabilidade de uma classe para outra, evitando duplicação de código e mantendo a coesão.

```java
// Composição + Delegação em vez de herança
class Pato {
    private ComportamentoVoo comportamentoVoo;

    public void voar() {
        comportamentoVoo.executar(); // delegação
    }
}
```

---

## 🧱 Princípios de Projeto de Software

### SOLID

| Princípio | Definição |
|-----------|-----------|
| **S** — Single Responsibility Principle (SRP) | Uma classe deve ter um, e apenas um, motivo para mudar. |
| **O** — Open/Closed Principle (OCP) | Entidades devem estar abertas para extensão, mas fechadas para modificação. |
| **L** — Liskov Substitution Principle (LSP) | Subtipos devem ser substituíveis por seus tipos base sem alterar a corretude do programa. |
| **I** — Interface Segregation Principle (ISP) | Clientes não devem ser forçados a depender de interfaces que não utilizam. |
| **D** — Dependency Inversion Principle (DIP) | Dependa de abstrações, não de implementações concretas. |

### Outros Princípios Importantes

- **DRY** — Don't Repeat Yourself
- **KISS** — Keep It Simple, Stupid
- **YAGNI** — You Ain't Gonna Need It
- **Law of Demeter** — Fale apenas com seus amigos imediatos
- **Composition over Inheritance** — Prefira composição à herança
- **Separation of Concerns** — Separação de responsabilidades

---

## 🔧 Refatoração

Técnica de reestruturar código existente **sem alterar seu comportamento externo**, melhorando a legibilidade, manutenibilidade e extensibilidade.

### Quando Refatorar
- Código duplicado
- Métodos/classes muito longos
- Forte acoplamento
- Baixa coesão
- Nomes pouco expressivos

### Técnicas Comuns
- Extrair método / classe
- Renomear variáveis e métodos
- Substituir condicionais por polimorfismo
- Introduzir padrões de projeto

---

## 🧩 Padrões de Projeto (Design Patterns)

Soluções reutilizáveis para problemas recorrentes no projeto de software.

### GoF (Gang of Four)

#### Criacionais
| Padrão | Propósito |
|--------|-----------|
| **Factory Method** | Delegar a criação de objetos para subclasses |
| **Abstract Factory** | Criar famílias de objetos relacionados |
| **Builder** | Construir objetos complexos passo a passo |
| **Prototype** | Criar objetos clonando uma instância existente |
| **Singleton** | Garantir uma única instância de uma classe |

#### Estruturais
| Padrão | Propósito |
|--------|-----------|
| **Adapter** | Permitir que interfaces incompatíveis trabalhem juntas |
| **Bridge** | Separar abstração de implementação |
| **Composite** | Compor objetos em estruturas de árvore |
| **Decorator** | Adicionar responsabilidades a objetos dinamicamente |
| **Facade** | Fornecer uma interface simplificada para um subsistema |
| **Flyweight** | Compartilhar objetos para suportar grandes quantidades |
| **Proxy** | Controlar o acesso a um objeto |

#### Comportamentais
| Padrão | Propósito |
|--------|-----------|
| **Chain of Responsibility** | Passar requisições ao longo de uma cadeia de handlers |
| **Command** | Encapsular uma requisição como um objeto |
| **Iterator** | Acessar elementos de uma coleção sequencialmente |
| **Mediator** | Definir como objetos interagem sem referências diretas |
| **Memento** | Capturar e restaurar o estado interno de um objeto |
| **Observer** | Notificar múltiplos objetos sobre mudanças de estado |
| **State** | Alterar o comportamento conforme o estado interno |
| **Strategy** | Encapsular algoritmos e torná-los intercambiáveis |
| **Template Method** | Definir o esqueleto de um algoritmo em uma superclasse |
| **Visitor** | Separar algoritmos dos objetos sobre os quais operam |

### Padrões Emergentes
Padrões que ganharam relevância com evolução da indústria:
- **Dependency Injection (DI)**
- **Inversion of Control (IoC)**
- **Repository Pattern**
- **Unit of Work**
- **CQRS** (Command Query Responsibility Segregation)
- **Event Sourcing**

---

## 🚫 Anti-Padrões

Práticas comuns que parecem soluções, mas causam mais problemas do que resolvem.

| Anti-Padrão | Descrição |
|-------------|-----------|
| **God Object / God Class** | Classe que concentra excessivas responsabilidades |
| **Spaghetti Code** | Código com fluxo de controle confuso e desestruturado |
| **Golden Hammer** | Usar a mesma solução para todos os problemas |
| **Copy-Paste Programming** | Duplicação massiva de código |
| **Lava Flow** | Código morto ou legado que ninguém ousa remover |
| **Magic Numbers / Strings** | Valores literais sem significado semântico explícito |
| **Anemic Domain Model** | Modelo de domínio sem comportamento, apenas dados |

---

## 🏛️ Princípios e Padrões de Arquitetura

Conceitos de nível arquitetural para organização de sistemas complexos.

### Princípios Arquiteturais
- **Separação de Camadas** (Presentation, Business, Data)
- **Inversão de Dependências entre camadas**
- **Alta Coesão e Baixo Acoplamento** em nível de componentes

### Padrões Arquiteturais
| Padrão | Característica |
|--------|----------------|
| **MVC** (Model-View-Controller) | Separação de dados, interface e controle |
| **MVP** (Model-View-Presenter) | View passiva com presenter intermediário |
| **MVVM** (Model-View-ViewModel) | ViewModel vinculado à View via data binding |
| **Layered (N-Tier)** | Organização em camadas horizontais |
| **Microservices** | Sistema como conjunto de serviços independentes |
| **Event-Driven** | Comunicação baseada em eventos assíncronos |
| **Hexagonal / Ports and Adapters** | Isolamento do domínio da infraestrutura |
| **Clean Architecture** | Independência de frameworks, UI e banco de dados |

---

## 📚 Referências

- *Design Patterns: Elements of Reusable Object-Oriented Software* — Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides (GoF)
- *Clean Code* — Robert C. Martin
- *Refactoring: Improving the Design of Existing Code* — Martin Fowler
- *Clean Architecture* — Robert C. Martin
- *Head First Design Patterns* — Eric Freeman & Elisabeth Robson

---

> 💡 **Nota:** Este repositório é um material de estudo em constante evolução. Contribuições, correções e sugestões são bem-vindas!

---

<p align="center">Desenvolvido para fins acadêmicos 📖</p>
