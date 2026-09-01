# 🎓 GUIA DEFINITIVO: Como Resolver Questões de Design Patterns

## 📋 Índice dos Padrões da Prova
1. **Strategy** — Cálculo de Frete
2. **Observer** — Sistema de Chamados
3. **Template Method** — Cadastro de Alunos
4. **Command** — Gerenciamento de Pedidos de Restaurante
5. **Factory Method** — Novos Tipos de Preparo (extensão do Command)

---

## 🧠 Como IDENTIFICAR o Padrão na Questão

### 1️⃣ STRATEGY — "Diferentes formas de fazer a MESMA coisa"

**Sinais na questão:**
- ✅ "Diferentes regras/algoritmos para a mesma operação"
- ✅ "Trocar a modalidade/estratégia em tempo de execução"
- ✅ "Adicionar novas modalidades sem alterar a classe principal"
- ✅ "A classe está acumulando regras específicas"

**Analogia:** Você tem um carro e quer trocar o motor. Strategy = você consegue trocar o motor sem desmontar o carro inteiro.

**Arquitetura:**
```
Contexto (Pedido) → usa → Strategy (FreteStrategy)
                              ↑
                    ┌─────────┼─────────┐
                    ▼         ▼         ▼
                Normal    Expressa   Retirada
```

**Como responder na prova:**
1. Crie uma **interface** com o método da operação (`calcularFrete()`)
2. Crie **classes concretas** para cada regra (Normal, Expressa, etc.)
3. No **Contexto** (Pedido), use **composição** (`private FreteStrategy strategy`)
4. Permita trocar a strategy via `setStrategy()`
5. Delegue a operação: `return strategy.calcularFrete(peso)`

**Por que funciona:** O Contexto NÃO sabe COMO calcular, só SABE QUE pode calcular. Cada Strategy sabe o seu próprio algoritmo.

---

### 2️⃣ OBSERVER — "Quando algo muda, avise quem está interessado"

**Sinais na questão:**
- ✅ "Quando X muda, vários componentes precisam saber"
- ✅ "Cadastrar/remover componentes interessados"
- ✅ "Nem todos acompanham tudo" (cada um escolhe o que observa)
- ✅ "Adicionar novos componentes sem modificar a classe principal"
- ✅ "Evitar que a classe principal conheça detalhes dos componentes"

**Analogia:** Você se inscreve em um canal do YouTube. Quando sai vídeo novo, TODOS os inscritos são notificados automaticamente. Você pode se inscrever ou cancelar a qualquer momento.

**Arquitetura:**
```
Subject (Chamado) ←───── Observer (interface)
    ↑                        ↑
    │            ┌───────────┼───────────┐
    │            ▼           ▼           ▼
    │        Cliente     Histórico     Painel
    │
    └── mantém lista de observers
    └── notifica todos quando status muda
```

**Como responder na prova:**
1. Crie a **interface Observer** com `atualizar(Subject s)`
2. Crie a **interface Subject** com `adicionar()`, `remover()`, `notificar()`
3. No **ConcreteSubject** (Chamado), mantenha uma `List<Observer>`
4. No `setStatus()`, chame `notificarObservers()` automaticamente
5. Crie **ConcreteObservers** que implementam `atualizar()` com sua lógica

**Por que funciona:** O Subject não sabe QUEM são os observers, só sabe que eles implementam a interface. Novos observers = novas classes, sem tocar no Subject.

---

### 3️⃣ TEMPLATE METHOD — "Mesmo processo, passos diferentes"

**Sinais na questão:**
- ✅ "Sequência geral é a mesma para todos"
- ✅ "Alguns passos são diferentes conforme o tipo"
- ✅ "Evitar repetição de código entre classes"
- ✅ "Validação/Confirmação específica para cada tipo"
- ✅ "Possibilitar inclusão de novos tipos"

**Analogia:** Receita de bolo. TODOS os bolos têm: preparar massa → assar → decorar. Mas a massa do chocolate é diferente da de baunilha, e a decoração também. O "template" é a receita, os "hooks" são os passos que mudam.

**Arquitetura:**
```
AbstractClass (CadastroTemplate)
    │
    ├── cadastrar() [FINAL]  ← Template Method (sequência fixa)
    │   ├── validarDados()
    │   │   └── validarEspecificos()  ← HOOK (abstract)
    │   ├── realizarCadastro()        ← comum a todos
    │   └── confirmarMatricula()
    │       └── confirmarEspecifica() ← HOOK (abstract)
    │
    └── getTipoAluno()                ← HOOK (abstract)
         ↑
    ┌────┴────┬────────┐
    ▼         ▼        ▼
 Regular   PosGrad   Visitante
```

**Como responder na prova:**
1. Crie uma **classe abstrata** com o método `cadastrar()` marcado como `final`
2. Dentro de `cadastrar()`, chame os passos na ordem fixa
3. Os passos que são IGUAIS para todos → implemente direto na classe abstrata
4. Os passos que MUDAM → declare como `protected abstract` (hooks)
5. Crie **subclasses** que implementam SÓ os hooks

**Por que funciona:** A sequência fica em UM lugar só. Se mudar a ordem dos passos, muda só na classe abstrata. Subclasses não podem bagunçar a ordem (método é final).

---

### 4️⃣ COMMAND — "Transformar uma ação em um objeto"

**Sinais na questão:**
- ✅ "Encapsular cada solicitação como objeto independente"
- ✅ "Desacoplar quem faz o pedido de quem executa"
- ✅ "Armazenar pedidos em filas de espera"
- ✅ "Permitir cancelamento de forma padronizada"
- ✅ "Manter histórico de operações"
- ✅ "Interface invoca diretamente métodos dos setores" (problema!)

**Analogia:** Você vai a um restaurante e anota seu pedido num papel. O garçom (Invoker) leva o papel (Command) para a cozinha (Receiver). O papel pode ficar na fila, ser cancelado, ou ser arquivado no histórico.

**Arquitetura:**
```
Invoker (FilaDePedidos)
    │
    ├── Queue<Command> fila
    ├── List<Command> historico
    ├── adicionarPedido(cmd)
    ├── processarFila()
    └── cancelarPedido(id)
         │
         ▼
    <<interface>> Command
        ├── execute()
        ├── cancelar()
        └── getDescricao()
             ↑
    ┌────────┼────────┐
    ▼        ▼        ▼
 PratoCmd SobremCmd BebidaCmd
    │        │        │
    ▼        ▼        ▼
 Cozinha  Cozinha    Bar
 (Receiver)
```

**Como responder na prova:**
1. Crie a **interface Command** com `executar()`, `cancelar()`, `getDescricao()`
2. Crie **Receiver classes** (Cozinha, Bar) — quem realmente faz o trabalho
3. Crie **ConcreteCommands** que recebem um Receiver no construtor
4. No `executar()`, delegue para o Receiver: `cozinha.prepararPrato(item)`
5. Crie o **Invoker** (FilaDePedidos) com fila e histórico
6. O Invoker NÃO sabe o que cada comando faz, só sabe que pode `executar()`

**Por que funciona:** O garçom (Invoker) não precisa saber cozinhar. Ele só precisa saber entregar o comando. A cozinha (Receiver) não precisa saber de filas. Cada um faz sua parte.

---

### 5️⃣ FACTORY METHOD — "Criar objetos sem especificar a classe exata"

**Sinais na questão:**
- ✅ "Adicionar novos tipos sem alterar a interface existente"
- ✅ "Adicionar novos tipos sem alterar o gerenciador/fila"
- ✅ "Diferentes menus/contextos criam diferentes objetos"
- ✅ Extensão natural do Command ou Strategy

**Analogia:** Você vai a uma lanchonete e pede "um hambúrguer". Você não precisa saber se é o chef João ou a chef Maria que vai fazer. A fábrica (cozinha) decide quem cria e como cria.

**Arquitetura:**
```
InterfaceAplicativo
    │
    ├── CommandFactory factory  ← composição com a fábrica
    │
    └── fazerPedido(tipo, item)
            └── factory.criarCommand(tipo, item)  ← delega criação
                 │
                 ▼
        <<interface>> CommandFactory
            ├── criarCommand(tipo, item)
                 ↑
        ┌────────┴────────┐
        ▼                 ▼
    MenuJantarFactory   MenuCafeFactory
        │                   │
        └── cria: Prato,    └── cria: Cafe,
            Sobremesa,          Bebida
            Bebida, Aperitivo
```

**Como responder na prova:**
1. Crie a **interface Factory** com `criarCommand(tipo, item)`
2. Crie **ConcreteFactories** para cada contexto (Jantar, Café, etc.)
3. A interface do aplicativo recebe uma Factory no construtor
4. Para adicionar novo tipo: crie NOVA Factory (ou modifique uma existente)
5. A interface e a fila NÃO precisam ser alteradas!

**Por que funciona:** A criação de objetos é isolada do uso de objetos. Você pode ter 50 tipos de comandos diferentes, mas a interface só vê `Command`.

---

## 🎯 Checklist para a Prova

| Padrão | Palavras-chave na questão | Estrutura mínima |
|--------|---------------------------|------------------|
| **Strategy** | "diferentes regras", "trocar modalidade", "sem alterar Pedido" | Interface + Contexto com composição |
| **Observer** | "notificar", "interessados", "cadastrar/remover", "status alterado" | Subject + Observer + lista |
| **Template Method** | "sequência geral", "passos iguais", "evitar repetição", "validação específica" | Classe abstrata + método final + hooks |
| **Command** | "encapsular solicitação", "fila", "cancelar", "histórico", "desacoplar" | Command + Receiver + Invoker + fila |
| **Factory Method** | "novos tipos sem alterar interface", "criar objetos" | Factory interface + ConcreteFactories |

---

## 💡 Dicas de Ouro

1. **Sempre justifique:** "Escolhi Strategy porque o problema envolve múltiplos algoritmos intercambiáveis para a mesma operação, respeitando o Princípio Aberto/Fechado (OCP)."

2. **Use composição, não herança:** Strategy e Observer usam composição ("tem um"), não herança ("é um").

3. **Método final no Template Method:** Sempre marque o template method como `final` para impedir que subclasses baguncem a sequência.

4. **Receiver no Command:** Não esqueça de criar as classes Receiver (Cozinha, Bar). O Command encapsula a chamada, mas alguém precisa executar!

5. **Histórico no Command:** Mantenha uma `List<Command>` no Invoker para auditoria. Isso é um diferencial na prova.

6. **Desacoplamento:** Em TODOS os padrões, o objetivo principal é reduzir o acoplamento. Sempre mencione isso na justificativa.

---

## 📝 Resposta Modelo para a Prova

**Estrutura ideal da resposta:**

```
1. IDENTIFICAÇÃO DO PADRÃO
   "O padrão utilizado é [NOME], pois a questão apresenta [SINAIS]."

2. JUSTIFICATIVA
   "Justifico pela necessidade de [OBJETIVO], respeitando os princípios
    SOLID de [SRP/OCP/etc.]. O padrão permite [BENEFÍCIO]."

3. ARQUITETURA (diagrama ou descrição)
   "A arquitetura consiste em [COMPONENTES] com os papéis [PAPEIS]."

4. IMPLEMENTAÇÃO EM JAVA
   [Código completo e compilável]

5. EXPLICAÇÃO DO CÓDIGO
   "A classe X representa o papel Y porque..."
```

---

Boa prova! 🚀
