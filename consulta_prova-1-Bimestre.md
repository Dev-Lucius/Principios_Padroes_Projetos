# Guia Rápido de Design Patterns (Java)

Resumo de sete padrões do catálogo GoF (*Gang of Four*): **Strategy**, **Observer**, **Template Method**, **Command**, **Iterator**, **Decorator** e **State**.

> **Sobre os exemplos:** escritos para Java 17+. Em um projeto real, cada classe ou interface pública ficaria em seu próprio arquivo; aqui elas aparecem agrupadas para facilitar a leitura.

---

## Sumário

1. [Visão geral](#visão-geral)
2. [Strategy](#1-strategy)
3. [Observer](#2-observer)
4. [Template Method](#3-template-method)
5. [Command](#4-command)
6. [Iterator](#5-iterator)
7. [Decorator](#6-decorator)
8. [State](#7-state)
9. [Comparações importantes](#comparações-importantes)
10. [Como escolher](#como-escolher)

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
| **State** | Comportamental | Mudar o comportamento conforme o estado interno | Composição (delegação ao estado) |

---

## 1. Strategy

**Intenção:** definir uma família de algoritmos, encapsular cada um e torná-los intercambiáveis. O cliente escolhe qual usar sem alterar seu próprio código.

**Problema que resolve:** cadeias de `if/else` ou `switch` que escolhem entre variantes de um mesmo comportamento (cálculo de frete, formas de pagamento, ordenação).

**Estrutura**

- `Strategy`: interface comum dos algoritmos.
- `ConcreteStrategy`: cada algoritmo concreto.
- `Context`: guarda uma referência à estratégia e delega o trabalho a ela.

**Exemplo**

```java
public interface EstrategiaFrete {
    double calcular(double pesoKg);
}

public class FreteEconomico implements EstrategiaFrete {
    @Override
    public double calcular(double pesoKg) {
        return 10 + pesoKg * 1.5;
    }
}

public class FreteExpresso implements EstrategiaFrete {
    @Override
    public double calcular(double pesoKg) {
        return 25 + pesoKg * 3.0;
    }
}

public class Carrinho {
    private final double pesoKg;
    private EstrategiaFrete estrategia;

    public Carrinho(double pesoKg, EstrategiaFrete estrategia) {
        this.pesoKg = pesoKg;
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaFrete estrategia) { // troca em runtime
        this.estrategia = estrategia;
    }

    public double totalFrete() {
        return estrategia.calcular(pesoKg);
    }
}

// Uso
Carrinho carrinho = new Carrinho(2, new FreteExpresso());
System.out.println(carrinho.totalFrete()); // 31.0

// Como é uma interface com um único método, também funciona com lambda:
carrinho.setEstrategia(peso -> 0); // frete grátis
```

**Quando usar**

- Existem várias variantes de um algoritmo e você quer trocá-las dinamicamente.
- Quer isolar regras de negócio que mudam com frequência.
- Quer eliminar condicionais extensas.

**Prós e contras**

- ✅ Respeita o princípio Aberto/Fechado (novas estratégias sem alterar o contexto).
- ✅ Facilita testes isolados de cada algoritmo.
- ⚠️ Aumenta o número de classes (mitigável com lambdas e referências a métodos).
- ⚠️ O cliente precisa conhecer as diferenças entre estratégias para escolher bem.

**No JDK:** `Comparator` passado a `Collections.sort()` / `List.sort()`.

---

## 2. Observer

**Intenção:** definir uma dependência um-para-muitos, de modo que, quando um objeto (*subject*) muda de estado, todos os seus dependentes (*observers*) sejam notificados automaticamente.

**Problema que resolve:** vários componentes precisam reagir a uma mudança sem que o emissor conheça os detalhes de cada um (eventos de UI, alertas, atualização de dashboards, mensageria).

**Estrutura**

- `Subject`: mantém a lista de observadores e oferece `inscrever`, `desinscrever` e `notificar`.
- `Observer`: interface com o método `atualizar`.
- `ConcreteObserver`: reage à notificação.

**Exemplo**

```java
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Observador<T> {
    void atualizar(T evento);
}

public class Assunto<T> {
    // CopyOnWriteArrayList permite inscrever/desinscrever durante a notificação
    private final List<Observador<T>> observadores = new CopyOnWriteArrayList<>();

    public void inscrever(Observador<T> obs) {
        observadores.add(obs);
    }

    public void desinscrever(Observador<T> obs) {
        observadores.remove(obs);
    }

    protected void notificar(T evento) {
        for (Observador<T> obs : observadores) {
            obs.atualizar(evento);
        }
    }
}

public class Estoque extends Assunto<Integer> {
    private int quantidade;

    public void definir(int quantidade) {
        this.quantidade = quantidade;
        notificar(quantidade);
    }
}

// Uso
Estoque estoque = new Estoque();

estoque.inscrever(qtd -> {
    if (qtd < 5) System.out.println("⚠️ Estoque baixo!");
});
estoque.inscrever(qtd -> System.out.println("Log: estoque agora é " + qtd));

estoque.definir(3);
// ⚠️ Estoque baixo!
// Log: estoque agora é 3
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

**No JDK:** `java.beans.PropertyChangeListener` e `PropertyChangeSupport`, além dos *listeners* do Swing/JavaFX. As classes `java.util.Observable` e `Observer` estão **depreciadas desde o Java 9**, então evite usá-las.

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

```java
import java.util.List;

public abstract class ExportadorRelatorio {

    // Template method: final impede que subclasses alterem o fluxo
    public final void exportar(List<String[]> dados) {
        abrir();
        for (String[] linha : dados) {
            escreverLinha(linha);
        }
        fechar();
    }

    protected void abrir() { }                          // hook

    protected abstract void escreverLinha(String[] linha); // passo obrigatório

    protected void fechar() { }                         // hook
}

public class ExportadorCsv extends ExportadorRelatorio {
    @Override
    protected void abrir() {
        System.out.println("nome,valor");
    }

    @Override
    protected void escreverLinha(String[] linha) {
        System.out.println(linha[0] + "," + linha[1]);
    }
}

public class ExportadorHtml extends ExportadorRelatorio {
    @Override
    protected void abrir() {
        System.out.println("<ul>");
    }

    @Override
    protected void escreverLinha(String[] linha) {
        System.out.println("  <li>" + linha[0] + ": " + linha[1] + "</li>");
    }

    @Override
    protected void fechar() {
        System.out.println("</ul>");
    }
}

// Uso
List<String[]> dados = List.of(new String[]{"Ana", "10"}, new String[]{"Beto", "20"});
new ExportadorHtml().exportar(dados);
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

**No JDK:** `AbstractList`, `InputStream.read(byte[])` (que chama o `read()` abstrato) e `HttpServlet.service()` (que chama `doGet`, `doPost`...).

---

## 4. Command

**Intenção:** encapsular uma requisição como um objeto, permitindo parametrizar clientes com diferentes ações, enfileirar, registrar em log e suportar operações de desfazer.

**Problema que resolve:** quem dispara a ação (botão, menu, atalho) não deveria conhecer quem a executa nem como ela é feita. Além disso, você pode querer desfazer, refazer, agendar ou registrar ações.

**Estrutura**

- `Command`: interface com `executar()` (e opcionalmente `desfazer()`).
- `ConcreteCommand`: liga um receptor a uma ação e guarda os parâmetros necessários.
- `Receiver`: quem realmente faz o trabalho.
- `Invoker`: quem dispara o comando (e pode guardar o histórico).
- `Client`: monta os comandos e os associa aos invokers.

**Exemplo (editor com desfazer)**

```java
import java.util.ArrayDeque;
import java.util.Deque;

public interface Comando {
    void executar();
    void desfazer();
}

// Receiver
public class Editor {
    private final StringBuilder texto = new StringBuilder();

    public void inserir(String trecho) {
        texto.append(trecho);
    }

    public void removerFinal(int quantidade) {
        texto.setLength(texto.length() - quantidade);
    }

    public String getTexto() {
        return texto.toString();
    }
}

public class InserirTexto implements Comando {
    private final Editor editor;
    private final String trecho;

    public InserirTexto(Editor editor, String trecho) {
        this.editor = editor;
        this.trecho = trecho;
    }

    @Override
    public void executar() {
        editor.inserir(trecho);
    }

    @Override
    public void desfazer() {
        editor.removerFinal(trecho.length());
    }
}

// Invoker
public class Historico {
    private final Deque<Comando> pilha = new ArrayDeque<>();

    public void executar(Comando comando) {
        comando.executar();
        pilha.push(comando);
    }

    public void desfazer() {
        if (!pilha.isEmpty()) {
            pilha.pop().desfazer();
        }
    }
}

// Uso
Editor editor = new Editor();
Historico historico = new Historico();

historico.executar(new InserirTexto(editor, "Olá, "));
historico.executar(new InserirTexto(editor, "mundo!"));
System.out.println(editor.getTexto()); // Olá, mundo!

historico.desfazer();
System.out.println(editor.getTexto()); // Olá,
```

**Quando usar**

- Precisa de desfazer/refazer.
- Quer enfileirar, agendar ou registrar operações (filas de tarefas, *macros*, *logs* transacionais).
- Quer desacoplar quem solicita de quem executa.

**Prós e contras**

- ✅ Desacopla invoker e receiver.
- ✅ Facilita undo/redo, filas e composição de comandos (*macro commands*).
- ⚠️ Cria muitas classes pequenas (para comandos sem desfazer, uma lambda basta).
- ⚠️ Desfazer exige guardar estado suficiente para reverter a ação.

**No JDK:** `Runnable` e `Callable` submetidos a um `ExecutorService` são, na prática, comandos enfileirados.

---

## 5. Iterator

**Intenção:** fornecer uma forma de acessar os elementos de uma coleção sequencialmente, sem expor sua representação interna.

**Problema que resolve:** coleções diferentes (listas, árvores, grafos, resultados paginados) exigem formas diferentes de percorrer. O cliente não deveria depender da estrutura interna.

**Estrutura**

- `Iterator`: interface com `hasNext()` e `next()`.
- `ConcreteIterator`: guarda a posição atual da travessia.
- `Aggregate` (coleção): cria o iterador (`Iterable` em Java).

**Exemplo**

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Playlist implements Iterable<String> {
    private final List<String> musicas = new ArrayList<>();

    public void adicionar(String musica) {
        musicas.add(musica);
    }

    // Travessia padrão (ordem de inserção)
    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < musicas.size();
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                return musicas.get(i++);
            }
        };
    }

    // Outra forma de travessia: ordem reversa
    public Iterable<String> reversa() {
        return () -> new Iterator<>() {
            private int i = musicas.size() - 1;

            @Override
            public boolean hasNext() {
                return i >= 0;
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                return musicas.get(i--);
            }
        };
    }
}

// Uso
Playlist playlist = new Playlist();
playlist.adicionar("Faixa A");
playlist.adicionar("Faixa B");

for (String musica : playlist) {
    System.out.println(musica);   // Faixa A, Faixa B
}
for (String musica : playlist.reversa()) {
    System.out.println(musica);   // Faixa B, Faixa A
}
```

**Quando usar**

- Quer ocultar a estrutura interna da coleção.
- Precisa de múltiplas formas de travessia (ordem normal, reversa, filtrada).
- Quer uma interface uniforme para percorrer coleções diferentes.

**Prós e contras**

- ✅ Responsabilidade única: a travessia fica fora da coleção.
- ✅ Várias travessias simultâneas, cada uma com seu estado.
- ⚠️ Pode ser exagero para coleções simples (muitas vezes basta devolver `musicas.iterator()`).
- ⚠️ Modificar a coleção durante a iteração pode lançar `ConcurrentModificationException` nas coleções padrão (comportamento *fail-fast*).

**No JDK:** `Iterable` e `Iterator` (base do *for-each*), e `Stream` para travessias funcionais.

---

## 6. Decorator

**Intenção:** adicionar responsabilidades a um objeto dinamicamente, envolvendo-o em outro objeto que implementa a mesma interface. É uma alternativa flexível à herança para estender comportamento.

**Problema que resolve:** combinar funcionalidades opcionais por herança gera uma explosão de subclasses (`NotificadorComSms`, `NotificadorComSmsESlack`, `NotificadorComSlack`...).

**Estrutura**

- `Component`: interface comum.
- `ConcreteComponent`: objeto original.
- `Decorator`: implementa a interface e guarda uma referência a outro `Component`.
- `ConcreteDecorator`: adiciona comportamento antes ou depois de delegar.

**Exemplo**

```java
public interface Notificador {
    void enviar(String mensagem);
}

public class NotificadorEmail implements Notificador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("Email: " + mensagem);
    }
}

public abstract class DecoradorNotificador implements Notificador {
    protected final Notificador envolvido;

    protected DecoradorNotificador(Notificador envolvido) {
        this.envolvido = envolvido;
    }

    @Override
    public void enviar(String mensagem) {
        envolvido.enviar(mensagem);
    }
}

public class ComSms extends DecoradorNotificador {
    public ComSms(Notificador envolvido) {
        super(envolvido);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem);
        System.out.println("SMS: " + mensagem);
    }
}

public class ComSlack extends DecoradorNotificador {
    public ComSlack(Notificador envolvido) {
        super(envolvido);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem);
        System.out.println("Slack: " + mensagem);
    }
}

// Uso: combinações montadas em tempo de execução
Notificador notificador = new ComSlack(new ComSms(new NotificadorEmail()));
notificador.enviar("Deploy concluído");
// Email: Deploy concluído
// SMS: Deploy concluído
// Slack: Deploy concluído
```

**Quando usar**

- Quer adicionar ou remover responsabilidades em objetos individuais, em tempo de execução.
- Subclassificar geraria combinações demais.
- Exemplos clássicos: *streams* de I/O, middlewares, compressão e criptografia empilhadas.

**Prós e contras**

- ✅ Combinações flexíveis, sem explosão de subclasses.
- ✅ Cada decorator tem uma única responsabilidade.
- ⚠️ Muitos objetos pequenos tornam a depuração mais difícil.
- ⚠️ A ordem dos decorators pode alterar o resultado.

**No JDK:** `new BufferedReader(new InputStreamReader(System.in))` e `Collections.unmodifiableList(...)`, `Collections.synchronizedList(...)`.

---

## 7. State

**Intenção:** permitir que um objeto altere seu comportamento quando seu estado interno muda. Visualmente, o objeto parece mudar de classe.

**Problema que resolve:** métodos cheios de condicionais (`if (status == ...)`) que se repetem em várias operações, geralmente em objetos com um ciclo de vida bem definido (pedido, documento, conexão, player de mídia).

**Estrutura**

- `Context`: mantém uma referência ao estado atual e delega as operações a ele.
- `State`: interface com as operações que dependem do estado.
- `ConcreteState`: implementa o comportamento de um estado e, quando apropriado, **provoca a transição** para o próximo.

**Exemplo (ciclo de vida de um pedido)**

```java
public interface EstadoPedido {
    void pagar(Pedido pedido);
    void enviar(Pedido pedido);
    void cancelar(Pedido pedido);
}

// Context
public class Pedido {
    private EstadoPedido estado = new Novo();

    void setEstado(EstadoPedido estado) { // visibilidade de pacote: só os estados trocam
        this.estado = estado;
    }

    public void pagar()    { estado.pagar(this); }
    public void enviar()   { estado.enviar(this); }
    public void cancelar() { estado.cancelar(this); }

    public String getEstado() {
        return estado.getClass().getSimpleName();
    }
}

class Novo implements EstadoPedido {
    @Override public void pagar(Pedido p)    { p.setEstado(new Pago()); }
    @Override public void enviar(Pedido p)   { throw new IllegalStateException("Pague antes de enviar"); }
    @Override public void cancelar(Pedido p) { p.setEstado(new Cancelado()); }
}

class Pago implements EstadoPedido {
    @Override public void pagar(Pedido p)    { throw new IllegalStateException("Pedido já pago"); }
    @Override public void enviar(Pedido p)   { p.setEstado(new Enviado()); }
    @Override public void cancelar(Pedido p) { p.setEstado(new Cancelado()); } // aqui entraria o estorno
}

class Enviado implements EstadoPedido {
    @Override public void pagar(Pedido p)    { throw new IllegalStateException("Pedido já pago"); }
    @Override public void enviar(Pedido p)   { throw new IllegalStateException("Pedido já enviado"); }
    @Override public void cancelar(Pedido p) { throw new IllegalStateException("Não é possível cancelar após o envio"); }
}

class Cancelado implements EstadoPedido {
    @Override public void pagar(Pedido p)    { throw new IllegalStateException("Pedido cancelado"); }
    @Override public void enviar(Pedido p)   { throw new IllegalStateException("Pedido cancelado"); }
    @Override public void cancelar(Pedido p) { throw new IllegalStateException("Pedido já cancelado"); }
}

// Uso
Pedido pedido = new Pedido();
pedido.pagar();
pedido.enviar();
System.out.println(pedido.getEstado()); // Enviado

pedido.cancelar(); // lança IllegalStateException: Não é possível cancelar após o envio
```

**Variação com `enum`:** quando os estados não carregam dados próprios, cada constante do `enum` pode implementar o comportamento e indicar o próximo estado, evitando criar uma classe por estado.

**Quando usar**

- O comportamento de um objeto depende do seu estado e muda em tempo de execução.
- Há condicionais grandes e repetidas que testam o mesmo atributo de estado.
- As regras de transição são claras (máquina de estados).

**Prós e contras**

- ✅ Elimina condicionais espalhadas; cada estado concentra suas regras.
- ✅ Novos estados entram sem alterar os existentes (Aberto/Fechado).
- ✅ As transições ficam explícitas e fáceis de auditar.
- ⚠️ Muitas classes para máquinas de estado simples (com 2 ou 3 estados, um `enum` ou um `switch` pode bastar).
- ⚠️ Os estados conhecem uns aos outros, o que cria acoplamento entre eles.

---

## Comparações importantes

### Strategy × State

Estruturalmente quase idênticos (um contexto delega a um objeto trocável), mas com intenções diferentes:

| | Strategy | State |
|---|---|---|
| Quem escolhe | O **cliente** define a estratégia | O **próprio estado** decide a próxima transição |
| Relação entre as variantes | Independentes, não se conhecem | Conhecem-se (um provoca a troca para o outro) |
| Objetivo | Variar *como* uma tarefa é feita | Variar *o que o objeto faz* ao longo do seu ciclo de vida |

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
| Objeto com ciclo de vida e comportamento que muda conforme o status | **State** |

### Combinações comuns

- **State + Observer**: ao mudar de estado, o objeto notifica interessados (ex.: pedido enviado dispara e-mail).
- **Command + Observer**: a UI dispara comandos; observadores atualizam a tela após cada execução.
- **Command + Iterator**: executar ou reverter um histórico de comandos em ordem.
- **Strategy + Decorator**: escolher o algoritmo base e empilhar comportamentos extras (log, cache, métricas).
- **Template Method + Strategy**: o fluxo fixo vem do template, e os passos mais voláteis são delegados a estratégias.

---

## Dica final

Padrões são **vocabulário comum e soluções testadas**, não regras obrigatórias. Aplique-os quando o problema aparecer (variação, acoplamento, duplicação), e não preventivamente: um padrão desnecessário só adiciona complexidade.
