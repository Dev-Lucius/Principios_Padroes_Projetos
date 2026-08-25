# 🏗️ MEGA GUIA DE DESIGN PATTERNS
## Prova de Padrões de Projeto — Consulta Rápida

---

# 📋 ÍNDICE

1. [Strategy](#1-strategy)
2. [Observer](#2-observer)
3. [Template Method](#3-template-method)
4. [Command](#4-command)
5. [Tabela Comparativa Rápida](#-tabela-comparativa-rápida)
6. [Checklist para a Prova](#-checklist-para-a-prova)

---

# 1. STRATEGY

## 🎯 Propósito
> **Definir uma família de algoritmos, encapsular cada um deles e torná-los intercambiáveis.**
> Strategy permite que o algoritmo varie independentemente dos clientes que o utilizam.

**Em uma frase:** *"Trocar comportamentos em tempo de execução sem herança."*

---

## 🔑 Quando Usar

| Situação | Exemplo |
|----------|---------|
| Muitos `if/else` ou `switch` para escolher comportamentos | Cálculo de frete: Sedex, PAC, Retirada |
| Algoritmos relacionados mas com implementações diferentes | Ordenação: BubbleSort, QuickSort, MergeSort |
| Precisa trocar comportamento em tempo de execução | Modo de pagamento no checkout |
| Quer evitar herança massiva (herança múltipla de comportamentos) | Personagens de jogo com diferentes ataques |
| Diferentes variantes de um mesmo algoritmo | Compressão: ZIP, RAR, 7Z |

---

## 🧱 Estrutura

```
┌─────────────────┐         ┌─────────────────────┐
│    Context      │◄────────│  Strategy (interface)│
│  (usa strategy) │         │   + execute()        │
│  - strategy     │         └─────────────────────┘
│  + setStrategy()│                   ▲
│  + doSomething()│                   │
└─────────────────┘      ┌────────────┼────────────┐
                         │            │            │
              ┌──────────┴──┐  ┌──────┴────┐ ┌────┴──────┐
              │ConcreteStrA │  │ConcreteStrB│ │ConcreteStrC│
              │+ execute()  │  │+ execute() │ │+ execute() │
              └─────────────┘  └────────────┘ └───────────┘
```

---

## 💻 Exemplo de Código (Java)

```java
// ========== INTERFACE STRATEGY ==========
interface FreteStrategy {
    double calcular(double peso);
}

// ========== CONCRETES ==========
class Sedex implements FreteStrategy {
    public double calcular(double peso) {
        return peso * 1.5 + 10;
    }
}

class PAC implements FreteStrategy {
    public double calcular(double peso) {
        return peso * 0.8 + 5;
    }
}

class Retirada implements FreteStrategy {
    public double calcular(double peso) {
        return 0; // gratuito
    }
}

// ========== CONTEXT ==========
class Carrinho {
    private FreteStrategy frete;

    public void setFrete(FreteStrategy frete) {
        this.frete = frete;
    }

    public double finalizarCompra(double peso) {
        return frete.calcular(peso);
    }
}

// ========== USO ==========
Carrinho c = new Carrinho();
c.setFrete(new Sedex());   // troca em runtime!
c.finalizarCompra(2.5);
c.setFrete(new PAC());     // troca de novo!
c.finalizarCompra(2.5);
```

---

## ✅ Vantagens

- ✅ **Elimina condicionais massivos** (if/else, switch)
- ✅ **Princípio Aberto/Fechado**: novos algoritmos sem modificar código existente
- ✅ **Reuso** de algoritmos em diferentes contextos
- ✅ **Troca em runtime** — comportamento dinâmico
- ✅ **Separação de responsabilidades** — cada strategy faz uma coisa

## ❌ Desvantagens

- ❌ Aumento no número de classes
- ❌ Cliente precisa conhecer as strategies disponíveis
- ❌ Overhead de criação de objetos (pode usar Singleton/Flyweight nas strategies)

---

## 🎓 Dicas para a Prova

> **Palavras-chave na questão:** *"trocar algoritmo"*, *"diferentes formas de calcular"*, *"evitar switch"*, *"comportamento intercambiável"*, *"família de algoritmos"*

> **Diferenciar de:**
> - **State**: Strategy é escolhido pelo cliente; State muda automaticamente conforme o estado interno do objeto
> - **Template Method**: Strategy usa **composição**; Template Method usa **herança**

> **Padrões relacionados:** Flyweight (compartilhar strategies), Factory (criar strategies)

---

# 2. OBSERVER

## 🎯 Propósito
> **Definir uma dependência um-para-muitos entre objetos, de modo que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.**

**Em uma frase:** *"Inscrever-se para receber notificações de mudanças."*

---

## 🔑 Quando Usar

| Situação | Exemplo |
|----------|---------|
| Um objeto muda e outros precisam reagir | Preço de ação muda → gráficos atualizam |
| Desacoplar quem gera evento de quem reage | Botão clicado → múltiplos listeners |
| Modelo MVC: Model notifica Views | Dados atualizam → interface atualiza |
| Sistema de eventos/pub-sub | Notificações push, newsletters |
| Precisa notificar N objetos sem conhecê-los | Sistema de logs, métricas, alertas |

---

## 🧱 Estrutura

```
         ┌─────────────────┐
         │   Subject       │◄──────────────┐
         │  (Observable)   │               │
         │ - observers[]   │               │
         │ + attach(obs)   │               │
         │ + detach(obs)   │               │
         │ + notify()      │               │
         └────────┬────────┘               │
                  │                        │
                  ▲                        │
         ┌────────┴────────┐               │
         │ ConcreteSubject │               │
         │ - state         │               │
         │ + getState()    │               │
         │ + setState()    │               │
         └─────────────────┘               │
                                           │
         ┌─────────────────┐               │
         │    Observer     │───────────────┘
         │  + update()     │
         └────────┬────────┘
                  ▲
      ┌───────────┼───────────┐
      │           │           │
┌─────┴────┐ ┌────┴────┐ ┌────┴────┐
│ Concrete │ │Concrete │ │Concrete │
│Observer1 │ │Observer2│ │Observer3│
│+ update()│ │+ update()│ │+ update()│
└──────────┘ └─────────┘ └─────────┘
```

---

## 💻 Exemplo de Código (Java)

```java
import java.util.*;

// ========== OBSERVER INTERFACE ==========
interface Observer {
    void update(float temperatura);
}

// ========== SUBJECT INTERFACE ==========
interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

// ========== CONCRETE SUBJECT ==========
class EstacaoMeteorologica implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperatura;

    public void attach(Observer o) { observers.add(o); }
    public void detach(Observer o) { observers.remove(o); }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperatura);
        }
    }

    public void setTemperatura(float temp) {
        this.temperatura = temp;
        notifyObservers();  // NOTIFICA TODOS!
    }
}

// ========== CONCRETE OBSERVERS ==========
class DisplayCelular implements Observer {
    public void update(float temperatura) {
        System.out.println("Celular: " + temperatura + "°C");
    }
}

class DisplayComputador implements Observer {
    public void update(float temperatura) {
        System.out.println("PC: " + temperatura + "°C");
    }
}

class AlertaGelo implements Observer {
    public void update(float temperatura) {
        if (temperatura < 0) 
            System.out.println("ALERTA: Gelo detectado!");
    }
}

// ========== USO ==========
EstacaoMeteorologica estacao = new EstacaoMeteorologica();
estacao.attach(new DisplayCelular());
estacao.attach(new DisplayComputador());
estacao.attach(new AlertaGelo());

estacao.setTemperatura(25.0f);  // Todos recebem!
estacao.setTemperatura(-5.0f);  // Alerta dispara!
```

---

## ✅ Vantagens

- ✅ **Desacoplamento** — Subject não conhece detalhes dos Observers
- ✅ **Extensibilidade** — novos observers sem alterar o subject
- ✅ **Broadcast automático** — um update, muitos receptores
- ✅ **Dinâmico** — attach/detach em runtime
- ✅ **Padrão fundamental do MVC**

## ❌ Desvantagens

- ❌ Notificações em cascata podem causar loops infinitos
- ❌ Ordem de notificação não é garantida (depende da implementação)
- ❌ Pode haver vazamento de memória se observers não forem removidos
- ❌ Subject pode notificar observers desnecessariamente (push vs pull)

---

## 🎓 Dicas para a Prova

> **Palavras-chave na questão:** *"notificar"*, *"atualizar automaticamente"*, *"inscrever"*, *"escutar eventos"*, *"publish-subscribe"*, *"MVC"*, *"dependência um-para-muitos"*

> **Push vs Pull:**
> - **Push**: Subject envia dados na notificação (`update(dados)`)
> - **Pull**: Observer busca dados do Subject (`update()` + `subject.getState()`)

> **Diferenciar de:**
> - **Mediator**: Observer é 1:N notificação; Mediator centraliza comunicação N:N
> - **Chain of Responsibility**: Observer notifica TODOS; Chain passa até alguém tratar

> **Padrões relacionados:** Singleton (subject único), Command (encapsular notificações)

---

# 3. TEMPLATE METHOD

## 🎯 Propósito
> **Definir o esqueleto de um algoritmo em uma operação, postergando (deferring) alguns passos para as subclasses.**
> Template Method permite que subclasses redefinam certos passos de um algoritmo sem mudar sua estrutura.

**Em uma frase:** *"Definir o 'molde' do algoritmo, deixando os detalhes para as subclasses."*

---

## 🔑 Quando Usar

| Situação | Exemplo |
|----------|---------|
| Algoritmos com passos fixos mas implementações variáveis | Relatório: gerar cabeçalho (fixo), corpo (varia), rodapé (fixo) |
| Frameworks que definem fluxo, mas deixam hooks | JUnit: `setUp()` → `runTest()` → `tearDown()` |
| Evitar duplicação de código em algoritmos similares | Processamento de arquivo: abrir → processar → fechar |
| Algoritmo com invariantes (sempre iguais) e variantes | Bebida: ferver água (fixo), adicionar ingrediente (varia) |
| Quer controlar extensão de subclasses | Métodos `final` (não sobrescrevíveis) + métodos abstratos |

---

## 🧱 Estrutura

```
┌─────────────────────────────┐
│   AbstractClass             │
│  (define o template)        │
│                             │
│  + templateMethod()  ◄──────┤── NÃO pode ser sobrescrito (final)
│    ├─ step1()        ◄──────┤── implementação padrão (hook)
│    ├─ step2()        ◄──────┤── ABSTRATO (deve ser implementado)
│    ├─ step3()        ◄──────┤── implementação padrão
│    └─ step4()        ◄──────┤── ABSTRATO
│                             │
│  # hook()            ◄──────┤── opcional, pode ser sobrescrito
└─────────────┬───────────────┘
              ▲
    ┌─────────┴─────────┐
    │                   │
┌───┴──────┐     ┌──────┴──────┐
│ConcreteA │     │  ConcreteB  │
│+ step2() │     │  + step2()  │
│+ step4() │     │  + step4()  │
│# hook()  │     │             │
└──────────┘     └─────────────┘
```

---

## 💻 Exemplo de Código (Java)

```java
// ========== CLASSE ABSTRATA (TEMPLATE) ==========
abstract class BebidaQuente {

    // TEMPLATE METHOD — final = não pode ser sobrescrito!
    public final void prepararReceita() {
        ferverAgua();      // passo fixo
        adicionarIngredientePrincipal();  // abstrato → subclasses implementam
        despejarNaXicara(); // passo fixo
        adicionarCondimentos(); // abstrato → subclasses implementam
        // hook opcional
        if (clienteQuerExtras()) {
            adicionarExtras();
        }
    }

    // Passos com implementação padrão (fixos)
    private void ferverAgua() {
        System.out.println("Fervendo água...");
    }

    private void despejarNaXicara() {
        System.out.println("Despejando na xícara...");
    }

    // Passos ABSTRATOS — subclasses DEVEM implementar
    protected abstract void adicionarIngredientePrincipal();
    protected abstract void adicionarCondimentos();

    // HOOK — método opcional, subclasses PODEM sobrescrever
    protected boolean clienteQuerExtras() {
        return true;  // padrão: sim
    }

    protected void adicionarExtras() {
        System.out.println("Adicionando extras...");
    }
}

// ========== CONCRETES ==========
class Cha extends BebidaQuente {
    protected void adicionarIngredientePrincipal() {
        System.out.println("Adicionando saquinho de chá...");
    }
    protected void adicionarCondimentos() {
        System.out.println("Adicionando limão...");
    }
}

class Cafe extends BebidaQuente {
    protected void adicionarIngredientePrincipal() {
        System.out.println("Passando café moído...");
    }
    protected void adicionarCondimentos() {
        System.out.println("Adicionando açúcar e leite...");
    }

    // Sobrescrevendo o HOOK
    @Override
    protected boolean clienteQuerExtras() {
        return false;  // café puro, sem extras
    }
}

// ========== USO ==========
BebidaQuente cha = new Cha();
cha.prepararReceita();  // segue o template!

BebidaQuente cafe = new Cafe();
cafe.prepararReceita();  // segue o mesmo template, mas com passos diferentes!
```

---

## ✅ Vantagens

- ✅ **Reuso de código** — código comum fica na superclasse
- ✅ **Controle de extensão** — `final` no template, abstratos nos passos
- ✅ **Inversão de controle** — "Hollywood Principle": *"Don't call us, we'll call you"*
- ✅ **Fácil de manter** — mudanças no fluxo afetam todas as subclasses
- ✅ **Frameworks** baseiam-se fortemente neste padrão

## ❌ Desvantagens

- ❌ Herança rígida — limitado a uma hierarquia
- ❌ Difícil entender o fluxo completo (está espalhado)
- ❌ Subclasses são acopladas à superclasse
- ❌ Pouca flexibilidade — o esqueleto é fixo

---

## 🎓 Dicas para a Prova

> **Palavras-chave na questão:** *"esqueleto do algoritmo"*, *"passos fixos"*, *"molde"*, *"framework"*, *"hook"*, *"Hollywood Principle"*, *"algoritmo com variações"*

> **Componentes essenciais:**
> - `templateMethod()` — **final**, define o fluxo
> - Métodos **abstratos** — obrigatórios nas subclasses
> - Métodos **concretos** — reutilizáveis (passos fixos)
> - **Hooks** — opcionais, permitem extensão pontual

> **Diferenciar de:**
> - **Strategy**: Template Method usa **herança**; Strategy usa **composição**
> - **Factory Method**: Template Method define fluxo de algoritmo; Factory Method cria objetos

> **Padrões relacionados:** Factory Method (usado dentro do template), Strategy (pode substituir herança por composição)

---

# 4. COMMAND

## 🎯 Propósito
> **Encapsular uma solicitação como um objeto, permitindo parametrizar clientes com diferentes solicitações, enfileirar ou registrar solicitações, e suportar operações reversíveis (undo).**

**Em uma frase:** *"Transformar uma ação em um objeto que pode ser armazenado, passado e executado depois."*

---

## 🔑 Quando Usar

| Situação | Exemplo |
|----------|---------|
| Desacoplar quem invoca de quem executa | Botão da UI → ação do backend |
| Suportar Undo/Redo | Ctrl+Z / Ctrl+Y em editores |
| Enfileirar ou agendar operações | Fila de jobs, tarefas assíncronas |
| Registrar operações para log/audit | Sistema bancário: registrar todas as transações |
| Operações compostas (macro commands) | Atalho de teclado executa várias ações |
| API com callbacks parametrizáveis | Menu dinâmico com ações configuráveis |

---

## 🧱 Estrutura

```
┌─────────────┐      ┌─────────────────┐      ┌──────────────┐
│   Client    │─────►│    Invoker      │      │   Receiver   │
│ (cria cmd)  │      │  (dispara cmd)  │      │ (executa a   │
└─────────────┘      │  - command      │      │  ação real)  │
                     │  + setCommand() │      │  + action()  │
                     │  + execute()    │      └──────────────┘
                     └────────┬────────┘              ▲
                              │                       │
                              │    ┌──────────────────┘
                              │    │
                              ▼    │
                     ┌─────────────────┐
                     │ Command (interface)│
                     │   + execute()      │
                     │   + undo()         │  ← opcional
                     └────────┬──────────┘
                              ▲
                    ┌─────────┴──────────┐
                    │                    │
             ┌──────┴──────┐      ┌─────┴──────┐
             │ ConcreteCmdA│      │ConcreteCmdB│
             │ - receiver  │      │ - receiver │
             │ + execute() │      │ + execute()│
             │ + undo()    │      │ + undo()   │
             └─────────────┘      └────────────┘
```

---

## 💻 Exemplo de Código (Java)

```java
// ========== RECEIVER (quem executa de verdade) ==========
class Luz {
    private String local;
    public Luz(String local) { this.local = local; }

    public void ligar() { 
        System.out.println(local + ": Luz LIGADA 💡"); 
    }
    public void desligar() { 
        System.out.println(local + ": Luz DESLIGADA 🌑"); 
    }
}

// ========== COMMAND INTERFACE ==========
interface Command {
    void execute();
    void undo();
}

// ========== CONCRETE COMMANDS ==========
class LigarLuzCommand implements Command {
    private Luz luz;

    public LigarLuzCommand(Luz luz) { this.luz = luz; }

    public void execute() { luz.ligar(); }
    public void undo() { luz.desligar(); }  // UNDO!
}

class DesligarLuzCommand implements Command {
    private Luz luz;

    public DesligarLuzCommand(Luz luz) { this.luz = luz; }

    public void execute() { luz.desligar(); }
    public void undo() { luz.ligar(); }  // UNDO!
}

// ========== INVOKER (quem dispara) ==========
class ControleRemoto {
    private Command slot;
    private Command ultimoCommand;  // para UNDO

    public void setCommand(Command cmd) { 
        this.slot = cmd; 
    }

    public void pressionarBotao() {
        slot.execute();
        ultimoCommand = slot;
    }

    public void pressionarUndo() {
        if (ultimoCommand != null) {
            ultimoCommand.undo();
        }
    }
}

// ========== CLIENT ==========
Luz luzSala = new Luz("Sala");
Command ligar = new LigarLuzCommand(luzSala);
Command desligar = new DesligarLuzCommand(luzSala);

ControleRemoto controle = new ControleRemoto();

controle.setCommand(ligar);
controle.pressionarBotao();     // Sala: Luz LIGADA 💡
controle.pressionarUndo();      // Sala: Luz DESLIGADA 🌑

controle.setCommand(desligar);
controle.pressionarBotao();     // Sala: Luz DESLIGADA 🌑
controle.pressionarUndo();      // Sala: Luz LIGADA 💡
```

---

## 🔄 Macro Command (Command Composto)

```java
class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();

    public void add(Command cmd) { commands.add(cmd); }

    public void execute() {
        for (Command cmd : commands) cmd.execute();
    }

    public void undo() {
        // Desfaz na ordem inversa!
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}

// Uso: "Modo Cinema" → desliga luz, fecha cortina, liga TV
MacroCommand modoCinema = new MacroCommand();
modoCinema.add(new DesligarLuzCommand(luzSala));
modoCinema.add(new FecharCortinaCommand(cortina));
modoCinema.add(new LigarTVCommand(tv));
modoCinema.execute();  // Tudo de uma vez!
```

---

## ✅ Vantagens

- ✅ **Desacoplamento total** — Cliente não conhece Receiver
- ✅ **Undo/Redo** — fácil de implementar
- ✅ **Enfileiramento** — commands podem ir para fila
- ✅ **Logging/Auditoria** — histórico de todas as operações
- ✅ **Composição** — Macro Commands para operações complexas
- ✅ **Parametrização** — passar comportamentos como objetos

## ❌ Desvantagens

- ❌ Muitas classes pequenas (um Command para cada ação)
- ❌ Complexidade aumentada para operações simples
- ❌ Gerenciamento de estado do undo pode ser complicado

---

## 🎓 Dicas para a Prova

> **Palavras-chave na questão:** *"desacoplar invocação da execução"*, *"undo/redo"*, *"fila de comandos"*, *"log de operações"*, *"macro"*, *"parametrizar ações"*, *"transações"*

> **Componentes essenciais:**
> - **Command** — interface com `execute()` e opcionalmente `undo()`
> - **ConcreteCommand** — encapsula o Receiver e a ação
> - **Receiver** — objeto que executa a ação real
> - **Invoker** — dispara o command (botão, menu, scheduler)
> - **Client** — cria e configura o command

> **Diferenciar de:**
> - **Strategy**: Command encapsula uma **ação/solicitação**; Strategy encapsula um **algoritmo**
> - **Observer**: Command é disparado uma vez; Observer notifica múltiplos

> **Padrões relacionados:** Composite (para Macro Commands), Memento (para estado do undo), Prototype (clonar commands)

---

# 📊 TABELA COMPARATIVA RÁPIDA

| Aspecto | Strategy | Observer | Template Method | Command |
|---------|----------|----------|-----------------|---------|
| **Tipo** | Comportamental | Comportamental | Comportamental | Comportamental |
| **Intenção** | Trocar algoritmos | Notificar mudanças | Definir esqueleto de algoritmo | Encapsular solicitação |
| **Mecanismo** | Composição | Assinatura/Notificação | Herança | Encapsulamento em objeto |
| **Relação** | Cliente → Strategy | 1:N Subject→Observers | Superclasse → Subclasse | Invoker → Command → Receiver |
| **Troca em Runtime** | ✅ Sim | ✅ Sim | ❌ Não (herança) | ✅ Sim |
| **Undo/Redo** | ❌ Não | ❌ Não | ❌ Não | ✅ Sim |
| **Palavra-chave** | "Algoritmo intercambiável" | "Notificar/atualizar" | "Esqueleto/molde" | "Encapsular ação" |
| **Analogia** | Trocar de ferramenta | Assinar newsletter | Receita de bolo | Controle remoto |
| **Princípio** | Aberto/Fechado | Baixo acoplamento | Hollywood Principle | Desacoplamento total |

---

# ✅ CHECKLIST PARA A PROVA

## 🔍 Como identificar o padrão na questão:

### Strategy
- [ ] Aparecem múltiplos algoritmos para o mesmo problema
- [ ] Menção a "trocar comportamento em runtime"
- [ ] Quer evitar `if/else` ou `switch` massivos
- [ ] "Família de algoritmos" encapsulados

### Observer
- [ ] "Um muda, vários precisam saber"
- [ ] "Notificar", "atualizar automaticamente", "inscrever"
- [ ] MVC / Model notificando Views
- [ ] Publish-Subscribe

### Template Method
- [ ] "Esqueleto", "molde", "passos fixos"
- [ ] Algoritmo com partes invariantes + variantes
- [ ] Frameworks, JUnit, processamento em etapas
- [ ] "Hook", "Hollywood Principle"

### Command
- [ ] "Desacoplar quem chama de quem executa"
- [ ] Undo/Redo, fila de operações, log
- [ ] Macro comandos, atalhos
- [ ] "Encapsular solicitação como objeto"

---

## 🧠 Frases-memorização (mnemônicos)

| Padrão | Mnemônico |
|--------|-----------|
| **Strategy** | *"Estratégia de guerra: troco de tática conforme a batalha"* |
| **Observer** | *"Observador de reality show: todos reagem quando algo acontece"* |
| **Template Method** | *"Template de currículo: estrutura fixa, conteúdo varia"* |
| **Command** | *"Comando militar: ordem encapsulada, pode ser desfeita"* |

---

## ⚡ Diferenças Críticas (cai muito em prova!)

### Strategy vs Template Method
```
Strategy: "COMPOSIÇÃO" → troca o objeto inteiro
Template Method: "HERANÇA" → sobrescreve partes do método

Use Strategy quando: precisa de MÁXIMA flexibilidade em runtime
Use Template Method quando: o FLUXO é fixo, só os passos variam
```

### Observer vs Command
```
Observer: "1:N notificação automática" → Subject notifica TODOS
Command: "1:1 solicitação encapsulada" → Invoker dispara UM command

Observer: reativo (quando o estado muda, notifica)
Command: ativo (quando o usuário clica, executa)
```

---

## 📝 Estrutura de Resposta para Questões Dissertativas

Se a questão pedir para **explicar/aplicar** um padrão, siga esta estrutura:

```
1. DEFINIÇÃO (1 linha — cite o GoF)
2. INTENÇÃO (para que serve — 1 frase)
3. ESTRUTURA (quem são os participantes)
4. APLICAÇÃO NO CENÁRIO (como usaria no problema dado)
5. VANTAGENS (2-3 benefícios específicos do cenário)
```

---

> 🍀 **Boa prova!** Lembre-se: o padrão certo é aquele que resolve o problema de desacoplamento/comportamento da forma mais elegante possível.

---

*Guia gerado para consulta rápida em provas de Design Patterns — Padrões Comportamentais*
