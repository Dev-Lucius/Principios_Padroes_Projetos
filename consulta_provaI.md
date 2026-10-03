# Guia Rápido de Design Patterns

Resumo de seis padrões do catálogo GoF (*Gang of Four*): **Strategy**, **Observer**, **Template Method**, **Command**, **Iterator** e **Decorator**. Os exemplos usam Python, mas as ideias valem para qualquer linguagem orientada a objetos.

---

## Sumário

1. [Visão geral](#visão-geral)
2. [Strategy](#1-strategy)
3. [Observer](#2-observer)
4. [Template Method](#3-template-method)
5. [Command](#4-command)
6. [Iterator](#5-iterator)
7. [Decorator](#6-decorator)
8. [Comparações importantes](#comparações-importantes)
9. [Como escolher](#como-escolher)

---

## Visão geral

| Padrão | Categoria | Ideia central | Mecanismo principal |
|---|---|---|---|
| **Strategy** | Comportamental | Algoritmos intercambiáveis em tempo de execução | Composição |
| **Observer** | Comportamental | Notificar vários objetos quando algo muda | Composição (lista de inscritos) |
| **Template Method** | Comportamental | Esqueleto fixo de algoritmo, passos variáveis | Herança |
| **Command** | Comportamental | Transformar uma ação em um objeto | Composição |
| **Iterator** | Comportamental | Percorrer uma coleção sem expor sua estrutura | Composição |
| **Decorator** | Estrutural | Adicionar comportamento envolvendo um objeto | Composição (*wrapping*) |

---

## 1. Strategy

**Intenção:** definir uma família de algoritmos, encapsular cada um e torná-los intercambiáveis. O cliente escolhe qual usar sem alterar seu próprio código.

**Problema que resolve:** cadeias de `if/elif/else` ou `switch` que escolhem entre várias variantes de um mesmo comportamento (ex.: cálculo de frete, formas de pagamento, ordenação).

**Estrutura**

- `Strategy`: interface comum dos algoritmos.
- `ConcreteStrategy`: cada algoritmo concreto.
- `Context`: guarda uma referência à estratégia e delega o trabalho a ela.

**Exemplo**

```python
from abc import ABC, abstractmethod

class EstrategiaFrete(ABC):
    @abstractmethod
    def calcular(self, peso_kg: float) -> float: ...

class FreteEconomico(EstrategiaFrete):
    def calcular(self, peso_kg):
        return 10 + peso_kg * 1.5

class FreteExpresso(EstrategiaFrete):
    def calcular(self, peso_kg):
        return 25 + peso_kg * 3.0

class Pedido:
    def __init__(self, peso_kg, estrategia: EstrategiaFrete):
        self.peso_kg = peso_kg
        self.estrategia = estrategia  # pode ser trocada em runtime

    def total_frete(self):
        return self.estrategia.calcular(self.peso_kg)

pedido = Pedido(2, FreteExpresso())
print(pedido.total_frete())  # 31.0
```

**Quando usar**

- Existem várias variantes de um algoritmo e você quer trocá-las dinamicamente.
- Quer isolar regras de negócio que mudam com frequência.
- Quer eliminar condicionais extensas.

**Prós e contras**

- ✅ Respeita o princípio Aberto/Fechado (novas estratégias sem alterar o contexto).
- ✅ Facilita testes isolados de cada algoritmo.
- ⚠️ Aumenta o número de classes.
- ⚠️ O cliente precisa conhecer as diferenças entre estratégias para escolher bem.

> Em linguagens com funções de primeira classe, uma estratégia pode ser apenas uma função passada como argumento.

---

## 2. Observer

**Intenção:** definir uma dependência um-para-muitos, de modo que, quando um objeto (*subject*) muda de estado, todos os seus dependentes (*observers*) sejam notificados automaticamente.

**Problema que resolve:** vários componentes precisam reagir a uma mudança sem que o emissor conheça os detalhes de cada um (eventos de UI, alertas, atualização de dashboards, mensageria).

**Estrutura**

- `Subject`: mantém a lista de observadores e oferece `inscrever`, `desinscrever` e `notificar`.
- `Observer`: interface com o método `atualizar`.
- `ConcreteObserver`: reage à notificação.

**Exemplo**

```python
class Assunto:
    def __init__(self):
        self._observadores = []

    def inscrever(self, obs):
        self._observadores.append(obs)

    def desinscrever(self, obs):
        self._observadores.remove(obs)

    def notificar(self, evento):
        for obs in self._observadores:
            obs.atualizar(evento)

class Estoque(Assunto):
    def __init__(self):
        super().__init__()
        self.quantidade = 0

    def definir(self, quantidade):
        self.quantidade = quantidade
        self.notificar({"quantidade": quantidade})

class AlertaEstoqueBaixo:
    def atualizar(self, evento):
        if evento["quantidade"] < 5:
            print("⚠️ Estoque baixo!")

class LogEstoque:
    def atualizar(self, evento):
        print(f"Log: estoque agora é {evento['quantidade']}")

estoque = Estoque()
estoque.inscrever(AlertaEstoqueBaixo())
estoque.inscrever(LogEstoque())
estoque.definir(3)
```

**Quando usar**

- Uma mudança em um objeto exige atualizar outros, e você não sabe quantos nem quais.
- Quer baixo acoplamento entre quem emite e quem reage.

**Prós e contras**

- ✅ Acoplamento baixo entre emissor e receptores.
- ✅ Observadores podem ser adicionados e removidos em tempo de execução.
- ⚠️ A ordem de notificação geralmente não é garantida.
- ⚠️ Risco de *memory leaks* se observadores não forem removidos.
- ⚠️ Cadeias de notificações podem dificultar o rastreamento do fluxo.

---

## 3. Template Method

**Intenção:** definir o esqueleto de um algoritmo em uma classe base, deixando que as subclasses redefinam certos passos sem alterar a estrutura geral.

**Problema que resolve:** várias classes seguem o mesmo fluxo, mudando só alguns detalhes, e o código do fluxo acaba duplicado.

**Estrutura**

- `AbstractClass`: contém o *template method* (o fluxo) e os passos, que podem ser:
  - **abstratos**: a subclasse é obrigada a implementar;
  - **hooks**: têm implementação padrão (geralmente vazia) e podem ser sobrescritos.
- `ConcreteClass`: implementa os passos variáveis.

**Exemplo**

```python
from abc import ABC, abstractmethod

class ExportadorRelatorio(ABC):
    def exportar(self, dados):  # template method
        self.abrir()
        for linha in dados:
            self.escrever_linha(linha)
        self.fechar()

    def abrir(self):  # hook
        pass

    @abstractmethod
    def escrever_linha(self, linha): ...  # passo obrigatório

    def fechar(self):  # hook
        pass

class ExportadorCSV(ExportadorRelatorio):
    def abrir(self):
        print("nome,valor")

    def escrever_linha(self, linha):
        print(f"{linha[0]},{linha[1]}")

class ExportadorHTML(ExportadorRelatorio):
    def abrir(self):
        print("<ul>")

    def escrever_linha(self, linha):
        print(f"  <li>{linha[0]}: {linha[1]}</li>")

    def fechar(self):
        print("</ul>")

ExportadorHTML().exportar([("Ana", 10), ("Beto", 20)])
```

**Quando usar**

- Vários algoritmos compartilham a mesma estrutura e diferem em detalhes.
- Quer controlar quais partes do fluxo as subclasses podem alterar.
- Frameworks: "não nos chame, nós chamamos você" (*Hollywood Principle*).

**Prós e contras**

- ✅ Elimina duplicação de código do fluxo comum.
- ✅ A classe base controla a ordem dos passos.
- ⚠️ Baseado em herança: acoplamento forte entre base e subclasses.
- ⚠️ Pode violar o princípio de Liskov se subclasses alterarem o comportamento esperado.
- ⚠️ Fluxos com muitos passos ficam difíceis de manter.

---

## 4. Command

**Intenção:** encapsular uma requisição como um objeto, permitindo parametrizar clientes com diferentes ações, enfileirar, registrar em log e suportar operações de desfazer.

> Não confundir com "comandante": o nome do padrão é **Command** (Comando).

**Problema que resolve:** quem dispara a ação (botão, menu, atalho) não deveria conhecer quem a executa nem como ela é feita. Além disso, você pode querer desfazer, refazer, agendar ou registrar ações.

**Estrutura**

- `Command`: interface com `executar()` (e opcionalmente `desfazer()`).
- `ConcreteCommand`: liga um receptor a uma ação e guarda os parâmetros necessários.
- `Receiver`: quem realmente faz o trabalho.
- `Invoker`: quem dispara o comando (e pode guardar o histórico).
- `Client`: monta os comandos e os associa aos invokers.

**Exemplo (editor com desfazer)**

```python
from abc import ABC, abstractmethod

class Comando(ABC):
    @abstractmethod
    def executar(self): ...

    @abstractmethod
    def desfazer(self): ...

class Editor:  # receiver
    def __init__(self):
        self.texto = ""

class InserirTexto(Comando):
    def __init__(self, editor, trecho):
        self.editor = editor
        self.trecho = trecho

    def executar(self):
        self.editor.texto += self.trecho

    def desfazer(self):
        fim = len(self.editor.texto) - len(self.trecho)
        self.editor.texto = self.editor.texto[:fim]

class Historico:  # invoker
    def __init__(self):
        self._pilha = []

    def executar(self, comando):
        comando.executar()
        self._pilha.append(comando)

    def desfazer(self):
        if self._pilha:
            self._pilha.pop().desfazer()

editor = Editor()
historico = Historico()
historico.executar(InserirTexto(editor, "Olá, "))
historico.executar(InserirTexto(editor, "mundo!"))
print(editor.texto)  # Olá, mundo!
historico.desfazer()
print(editor.texto)  # Olá,
```

**Quando usar**

- Precisa de desfazer/refazer.
- Quer enfileirar, agendar ou registrar operações (filas de tarefas, *macros*, *logs* transacionais).
- Quer desacoplar quem solicita de quem executa.

**Prós e contras**

- ✅ Desacopla invoker e receiver.
- ✅ Facilita undo/redo, filas e composição de comandos (*macro commands*).
- ⚠️ Cria muitas classes pequenas.
- ⚠️ Desfazer exige guardar estado suficiente para reverter a ação.

---

## 5. Iterator

**Intenção:** fornecer uma forma de acessar os elementos de uma coleção sequencialmente, sem expor sua representação interna.

> Não confundir com "iterativo" (adjetivo de repetição): o padrão é **Iterator**.

**Problema que resolve:** coleções diferentes (listas, árvores, grafos, resultados paginados) exigem formas diferentes de percorrer. O cliente não deveria depender da estrutura interna.

**Estrutura**

- `Iterator`: interface com algo como `proximo()` / `tem_proximo()`.
- `ConcreteIterator`: guarda a posição atual da travessia.
- `Aggregate` (coleção): cria o iterador.

**Exemplo (explícito)**

```python
class IteradorPlaylist:
    def __init__(self, musicas):
        self._musicas = musicas
        self._i = 0

    def __iter__(self):
        return self

    def __next__(self):
        if self._i >= len(self._musicas):
            raise StopIteration
        musica = self._musicas[self._i]
        self._i += 1
        return musica

class Playlist:
    def __init__(self):
        self._musicas = []

    def adicionar(self, musica):
        self._musicas.append(musica)

    def __iter__(self):
        return IteradorPlaylist(self._musicas)

p = Playlist()
p.adicionar("Faixa A")
p.adicionar("Faixa B")
for musica in p:
    print(musica)
```

**Exemplo (com generators, forma idiomática em Python)**

```python
class Playlist:
    def __init__(self):
        self._musicas = []

    def adicionar(self, musica):
        self._musicas.append(musica)

    def __iter__(self):
        yield from self._musicas

    def reversa(self):  # outro tipo de travessia
        yield from reversed(self._musicas)
```

**Quando usar**

- Quer ocultar a estrutura interna da coleção.
- Precisa de múltiplas formas de travessia (ordem normal, reversa, filtrada).
- Quer uma interface uniforme para percorrer coleções diferentes.

**Prós e contras**

- ✅ Responsabilidade única: a travessia fica fora da coleção.
- ✅ Várias travessias simultâneas, cada uma com seu estado.
- ⚠️ Pode ser exagero para coleções simples.
- ⚠️ Modificar a coleção durante a iteração pode gerar comportamento inesperado.

> A maioria das linguagens modernas (Python, Java, C#, JavaScript) já traz esse padrão embutido na linguagem.

---

## 6. Decorator

**Intenção:** adicionar responsabilidades a um objeto dinamicamente, envolvendo-o em outro objeto que implementa a mesma interface. É uma alternativa flexível à herança para estender comportamento.

**Problema que resolve:** combinar funcionalidades opcionais por herança gera uma explosão de subclasses (`NotificadorComSMS`, `NotificadorComSMSeSlack`, `NotificadorComSlack`...).

**Estrutura**

- `Component`: interface comum.
- `ConcreteComponent`: objeto original.
- `Decorator`: implementa a interface e guarda uma referência a outro `Component`.
- `ConcreteDecorator`: adiciona comportamento antes ou depois de delegar.

**Exemplo**

```python
from abc import ABC, abstractmethod

class Notificador(ABC):
    @abstractmethod
    def enviar(self, mensagem): ...

class NotificadorEmail(Notificador):
    def enviar(self, mensagem):
        print(f"Email: {mensagem}")

class DecoradorNotificador(Notificador):
    def __init__(self, envolvido: Notificador):
        self._envolvido = envolvido

    def enviar(self, mensagem):
        self._envolvido.enviar(mensagem)

class ComSMS(DecoradorNotificador):
    def enviar(self, mensagem):
        super().enviar(mensagem)
        print(f"SMS: {mensagem}")

class ComSlack(DecoradorNotificador):
    def enviar(self, mensagem):
        super().enviar(mensagem)
        print(f"Slack: {mensagem}")

# Combinações montadas em tempo de execução
notificador = ComSlack(ComSMS(NotificadorEmail()))
notificador.enviar("Deploy concluído")
# Email: Deploy concluído
# SMS: Deploy concluído
# Slack: Deploy concluído
```

**Quando usar**

- Quer adicionar ou remover responsabilidades em objetos individuais, em tempo de execução.
- Subclassificar geraria combinações demais.
- Exemplos clássicos: *streams* de I/O (`BufferedReader(FileReader(...))`), middlewares, compressão e criptografia empilhadas.

**Prós e contras**

- ✅ Combinações flexíveis, sem explosão de subclasses.
- ✅ Cada decorator tem uma única responsabilidade.
- ⚠️ Muitos objetos pequenos tornam a depuração mais difícil.
- ⚠️ A ordem dos decorators pode alterar o resultado.

> Atenção: o `@decorator` do Python (sintaxe de funções) é um recurso da linguagem relacionado, mas não é o mesmo que o padrão Decorator do GoF.

---

## Comparações importantes

### Strategy × Template Method

| | Strategy | Template Method |
|---|---|---|
| Mecanismo | Composição | Herança |
| Momento da troca | Em tempo de execução | Em tempo de compilação (escolha da subclasse) |
| Granularidade | Troca o algoritmo inteiro | Troca apenas passos do algoritmo |
| Acoplamento | Mais baixo | Mais alto |

### Strategy × Decorator

- **Strategy** muda o *miolo* (o algoritmo) do objeto.
- **Decorator** muda a *casca* (adiciona comportamento ao redor), mantendo o núcleo.

### Command × Strategy

- Ambos encapsulam comportamento em objetos.
- **Strategy** representa *como* fazer algo (variantes intercambiáveis de um mesmo objetivo).
- **Command** representa *o que* fazer (uma solicitação específica, com parâmetros, que pode ser guardada, adiada e desfeita).

### Observer × Command

- **Observer**: reage a eventos, com muitos receptores para um emissor.
- **Command**: executa uma ação específica solicitada, em geral com um receptor.

---

## Como escolher

| Se o seu problema é... | Considere |
|---|---|
| Muitos `if/else` escolhendo entre variantes de um algoritmo | **Strategy** |
| Vários objetos precisam reagir a uma mudança de estado | **Observer** |
| Várias classes com o mesmo fluxo e passos diferentes | **Template Method** |
| Desfazer/refazer, filas de ações, macros, logs de operações | **Command** |
| Percorrer uma coleção sem expor sua estrutura | **Iterator** |
| Combinar funcionalidades opcionais sem criar dezenas de subclasses | **Decorator** |

### Combinações comuns

- **Command + Observer**: a UI dispara comandos; observadores atualizam a tela após cada execução.
- **Command + Iterator**: executar ou reverter um histórico de comandos em ordem.
- **Strategy + Decorator**: escolher o algoritmo base e empilhar comportamentos extras (log, cache, métricas).
- **Template Method + Strategy**: o fluxo fixo vem do template, e os passos mais voláteis são delegados a estratégias.

---

## Dica final

Padrões são **vocabulário comum e soluções testadas**, não regras obrigatórias. Aplique-os quando o problema aparecer (variação, acoplamento, duplicação), e não preventivamente: um padrão desnecessário só adiciona complexidade.
