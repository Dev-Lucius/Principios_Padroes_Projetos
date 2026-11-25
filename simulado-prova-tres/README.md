# Exercícios de Padrões de Projeto em Java

**Decorator • Proxy • Adapter • Prototype • Builder**

Um exercício completo (enunciado, tarefas, exemplo e gabarito) para cada padrão.

## Índice

1. [Decorator — Fluxo de dados](#1-decorator--fluxo-de-dados)
2. [Proxy — Controle de acesso a documentos](#2-proxy--controle-de-acesso-a-documentos)
3. [Adapter — Gateways de pagamento](#3-adapter--gateways-de-pagamento)
4. [Prototype — Inimigos de um jogo](#4-prototype--inimigos-de-um-jogo)
5. [Builder — Montagem de computadores](#5-builder--montagem-de-computadores)
6. [Quadro-resumo](#quadro-resumo)

## Orientações

Para cada exercício:

1. Identifique o problema apresentado.
2. Identifique o padrão de projeto adequado.
3. Modele as principais classes/interfaces.
4. Implemente a solução em Java.
5. Crie uma classe `Main` para demonstrar o funcionamento.
6. Evite concentrar a solução em grandes blocos de `if/else` ou `switch` quando o objetivo for justamente aplicar um padrão de projeto.

---

## 1. Decorator — Fluxo de dados

Um sistema grava dados em arquivo. Em alguns casos, esses dados precisam ser compactados, criptografados, armazenados em buffer ou registrados em log — em qualquer combinação.

### Tarefas

Crie a interface:

```java
public interface FluxoDados {
    void escrever(String dados);
}
```

Implemente `FluxoArquivo` (pode apenas imprimir o que seria gravado) e os decoradores:

```text
FluxoCompactado
FluxoCriptografado
FluxoBuffer
FluxoComLog
```

A compactação e a criptografia podem ser simuladas (por exemplo, prefixar `[ZIP]` ou inverter a String).

### Exemplo

```java
FluxoDados fluxo = new FluxoArquivo("dados.txt");

fluxo = new FluxoCompactado(fluxo);
fluxo = new FluxoCriptografado(fluxo);
fluxo = new FluxoComLog(fluxo);

fluxo.escrever("Informação confidencial");
```

### Perguntas

1. Em que ordem as operações são executadas sobre o dado?
2. Essa ordem é adequada para compactação e criptografia? Como corrigir a montagem da cadeia?
3. Que classes do pacote `java.io` seguem esse mesmo padrão?

### Gabarito

**Padrão:** Decorator.

```text
                 FluxoDados
                     ▲
          ┌──────────┴───────────┐
          │                      │
     FluxoArquivo         DecoradorFluxo
                                 ▲
             ┌──────────┬────────┴─────┬──────────┐
             ▼          ▼              ▼          ▼
        Compactado  Criptografado    Buffer    ComLog
```

Estrutura básica:

```java
public abstract class DecoradorFluxo implements FluxoDados {
    protected final FluxoDados fluxo;

    protected DecoradorFluxo(FluxoDados fluxo) {
        this.fluxo = fluxo;
    }
}

public class FluxoCompactado extends DecoradorFluxo {
    public FluxoCompactado(FluxoDados fluxo) {
        super(fluxo);
    }

    @Override
    public void escrever(String dados) {
        fluxo.escrever("[ZIP]" + dados);
    }
}
```

**Respostas:**

1. A chamada percorre a cadeia de fora para dentro: log → criptografia → compactação → arquivo.
2. Não. Dados criptografados parecem aleatórios e compactam mal. O correto é compactar **antes** de criptografar, ou seja, `FluxoCompactado` deve ficar por fora de `FluxoCriptografado` na montagem:

```java
FluxoDados fluxo = new FluxoArquivo("dados.txt");

fluxo = new FluxoCriptografado(fluxo);
fluxo = new FluxoCompactado(fluxo);
fluxo = new FluxoComLog(fluxo);
```

3. `BufferedReader`, `BufferedOutputStream`, `GZIPOutputStream` e `DataInputStream` envolvem um fluxo existente e acrescentam funcionalidade.

**Conceito fundamental:** o decorador implementa a **mesma interface** do objeto decorado, guarda uma referência a ele e acrescenta comportamento antes ou depois de delegar. A ordem dos decoradores altera o resultado.

---

## 2. Proxy — Controle de acesso a documentos

Um sistema de gestão de documentos possui três perfis:

```text
LEITOR   → pode ler
EDITOR   → pode ler e editar
ADMIN    → pode ler, editar e excluir
```

A classe `DocumentoReal` não deve conter nenhuma regra de permissão.

### Tarefas

Crie:

```java
public interface Documento {
    String ler();
    void editar(String novoConteudo);
    void excluir();
}
```

Crie também:

```text
Perfil (enum)
Usuario (nome, perfil)
AcessoNegadoException
DocumentoReal
DocumentoProxy
```

O `DocumentoProxy` deve verificar o perfil do usuário antes de repassar cada chamada ao `DocumentoReal`, lançando `AcessoNegadoException` quando a operação não for permitida.

### Exemplo

```java
Documento doc = new DocumentoReal("Contrato");

Documento acessoLeitor = new DocumentoProxy(doc, new Usuario("Ana", Perfil.LEITOR));

acessoLeitor.ler();                 // permitido
acessoLeitor.editar("novo texto");  // AcessoNegadoException
acessoLeitor.excluir();             // AcessoNegadoException
```

### Desafio

Registre em log todas as tentativas de acesso negadas.

### Gabarito

**Padrão:** Proxy (proxy de proteção).

```text
          Documento
              ▲
       ┌──────┴────────┐
       │               │
 DocumentoReal   DocumentoProxy
       ▲               │
       └───────────────┘
 (repassa a chamada se o acesso for permitido)
```

```java
public class DocumentoProxy implements Documento {
    private final Documento documento;
    private final Usuario usuario;

    public DocumentoProxy(Documento documento, Usuario usuario) {
        this.documento = documento;
        this.usuario = usuario;
    }

    @Override
    public String ler() {
        return documento.ler();
    }

    @Override
    public void editar(String novoConteudo) {
        if (usuario.getPerfil() == Perfil.LEITOR) {
            registrarNegado("editar");
            throw new AcessoNegadoException("Sem permissão para editar");
        }
        documento.editar(novoConteudo);
    }

    @Override
    public void excluir() {
        if (usuario.getPerfil() != Perfil.ADMIN) {
            registrarNegado("excluir");
            throw new AcessoNegadoException("Sem permissão para excluir");
        }
        documento.excluir();
    }

    private void registrarNegado(String operacao) {
        System.out.println("[LOG] Acesso negado: " + usuario.getNome() + " tentou " + operacao);
    }
}
```

**Conceito fundamental:** o Proxy **controla o acesso** ao objeto real mantendo a mesma interface. A classe `DocumentoReal` fica apenas com a lógica de negócio, e o cliente não precisa saber que está usando um proxy.

Outras variações do Proxy: virtual (carregamento tardio), cache, remoto e de log/contagem.

---

## 3. Adapter — Gateways de pagamento

Uma loja virtual utiliza a interface abaixo para cobrar seus clientes:

```java
public interface GatewayPagamento {
    boolean pagar(double valor, String numeroCartao);
}
```

A loja precisa integrar duas bibliotecas de terceiros, que **não podem ser alteradas**:

```java
public class PagSeguroAPI {
    public void efetuarCobranca(String cartao, int valorEmCentavos) {
        System.out.println("PagSeguro: cobrando " + valorEmCentavos + " centavos");
    }
}

public class MercadoPagoSDK {
    public String processarPagamento(Map<String, Object> dados) {
        System.out.println("MercadoPago: " + dados);
        return "APROVADO";
    }
}
```

### Tarefas

Implemente utilizando **Adapter**:

```text
PagSeguroAdapter
MercadoPagoAdapter
```

Crie a classe `Checkout`, que depende apenas de `GatewayPagamento`.

Dicas:

* converta reais para centavos com `(int) Math.round(valor * 100)`;
* considere o pagamento do PagSeguro bem-sucedido quando nenhuma exceção for lançada;
* considere o pagamento do Mercado Pago bem-sucedido quando o retorno for `"APROVADO"`.

### Exemplo

```java
Checkout checkout = new Checkout(new PagSeguroAdapter(new PagSeguroAPI()));
checkout.finalizarCompra(150.00, "1234567890123456");

checkout = new Checkout(new MercadoPagoAdapter(new MercadoPagoSDK()));
checkout.finalizarCompra(150.00, "1234567890123456");
```

### Gabarito

**Padrão:** Adapter (adaptador de objeto).

```text
Checkout ──► GatewayPagamento                 (Target)
                    ▲
         ┌──────────┴───────────┐
         │                      │
 PagSeguroAdapter      MercadoPagoAdapter     (Adapters)
         │                      │
         ▼                      ▼
   PagSeguroAPI           MercadoPagoSDK      (Adaptees)
```

```java
public class PagSeguroAdapter implements GatewayPagamento {
    private final PagSeguroAPI api;

    public PagSeguroAdapter(PagSeguroAPI api) {
        this.api = api;
    }

    @Override
    public boolean pagar(double valor, String numeroCartao) {
        api.efetuarCobranca(numeroCartao, (int) Math.round(valor * 100));
        return true;
    }
}

public class MercadoPagoAdapter implements GatewayPagamento {
    private final MercadoPagoSDK sdk;

    public MercadoPagoAdapter(MercadoPagoSDK sdk) {
        this.sdk = sdk;
    }

    @Override
    public boolean pagar(double valor, String numeroCartao) {
        Map<String, Object> dados = new HashMap<>();
        dados.put("valor", valor);
        dados.put("cartao", numeroCartao);
        return "APROVADO".equals(sdk.processarPagamento(dados));
    }
}
```

**Conceito fundamental:** o Adapter **converte uma interface em outra** que o cliente espera. O `Checkout` nunca conhece as classes dos fornecedores; para trocar de fornecedor, basta trocar o adaptador.

---

## 4. Prototype — Inimigos de um jogo

Em um jogo, centenas de inimigos são criados durante uma fase. Cada tipo de inimigo (Orc, Goblin) é configurado com nome, vida e uma lista de habilidades, e essa configuração é custosa (leitura de arquivos, cálculos de atributos).

Em vez de construir cada inimigo do zero, o jogo deve **clonar** um modelo já configurado.

### Tarefas

Implemente utilizando **Prototype**:

* uma classe abstrata `Inimigo` com `nome`, `vida` e `List<String> habilidades`, e o método abstrato `clonar()`;
* as classes `Orc` e `Goblin`, cada uma com seus valores iniciais;
* a classe `RegistroPrototipos`, que guarda protótipos em um `Map<String, Inimigo>` e oferece:

```java
void registrar(String chave, Inimigo prototipo);
Inimigo criar(String chave);
```

Evite `Object.clone()` e `Cloneable`: use um construtor de cópia.

### Exemplo

```java
RegistroPrototipos registro = new RegistroPrototipos();
registro.registrar("orc", new Orc());
registro.registrar("goblin", new Goblin());

Inimigo orc1 = registro.criar("orc");
Inimigo orc2 = registro.criar("orc");

orc2.getHabilidades().add("Fúria");

System.out.println(orc1.getHabilidades()); // não deve conter "Fúria"
System.out.println(orc2.getHabilidades()); // contém "Fúria"
```

### Perguntas

1. O que aconteceria se o construtor de cópia fizesse `this.habilidades = outro.habilidades`?
2. Qual é a diferença entre cópia rasa (*shallow*) e cópia profunda (*deep*)?
3. Por que muitos desenvolvedores evitam `Object.clone()`?

### Gabarito

**Padrão:** Prototype.

```text
                Inimigo (abstrata)
                   + clonar()
                       ▲
              ┌────────┴────────┐
              │                 │
             Orc             Goblin
        clonar() → new Orc(this)

 RegistroPrototipos ──► Map<String, Inimigo> ──► clonar()
```

```java
public abstract class Inimigo {
    private String nome;
    private int vida;
    private final List<String> habilidades;

    protected Inimigo(String nome, int vida, List<String> habilidades) {
        this.nome = nome;
        this.vida = vida;
        this.habilidades = new ArrayList<>(habilidades);
    }

    // construtor de cópia (cópia profunda da lista)
    protected Inimigo(Inimigo outro) {
        this.nome = outro.nome;
        this.vida = outro.vida;
        this.habilidades = new ArrayList<>(outro.habilidades);
    }

    public abstract Inimigo clonar();

    public List<String> getHabilidades() { return habilidades; }
    public String getNome() { return nome; }
    public int getVida() { return vida; }
}

public class Orc extends Inimigo {
    public Orc() {
        super("Orc", 120, List.of("Golpe pesado", "Grito de guerra"));
    }

    private Orc(Orc outro) {
        super(outro);
    }

    @Override
    public Orc clonar() {
        return new Orc(this);
    }
}

public class RegistroPrototipos {
    private final Map<String, Inimigo> prototipos = new HashMap<>();

    public void registrar(String chave, Inimigo prototipo) {
        prototipos.put(chave, prototipo);
    }

    public Inimigo criar(String chave) {
        Inimigo prototipo = prototipos.get(chave);
        if (prototipo == null) {
            throw new IllegalArgumentException("Protótipo inexistente: " + chave);
        }
        return prototipo.clonar();
    }
}
```

**Respostas:**

1. Todos os clones compartilhariam a **mesma lista**. Adicionar "Fúria" ao `orc2` alteraria também `orc1` e o protótipo original.
2. Na cópia **rasa**, os campos de referência apontam para os mesmos objetos do original. Na cópia **profunda**, os objetos referenciados também são copiados, e o clone fica totalmente independente.
3. `Object.clone()` faz cópia rasa por padrão, é `protected`, exige `Cloneable` (uma interface sem método) e lança `CloneNotSupportedException`. Construtores de cópia ou métodos próprios são mais claros e seguros.

**Conceito fundamental:** o Prototype cria novos objetos **copiando uma instância existente**, sem depender da classe concreta no código cliente. O cuidado central é garantir cópia profunda onde houver estado mutável.

---

## 5. Builder — Montagem de computadores

Uma loja de informática precisa representar computadores com muitos atributos:

```text
Obrigatórios: processador, memória (GB)
Opcionais:    SSD (GB), placa de vídeo, fonte (W), Wi-Fi
```

Regras de negócio:

* memória mínima de 4 GB;
* uma placa de vídeo dedicada exige fonte de, no mínimo, 500 W.

Valores padrão: SSD de 256 GB, placa de vídeo "Integrada", fonte de 400 W, sem Wi-Fi.

Uma solução com vários construtores (`new Computador("Ryzen 5", 16, 512, "RTX 4060", 650, true)`) é difícil de ler e propensa a erros na ordem dos parâmetros.

### Tarefas

Implemente utilizando **Builder**:

* a classe `Computador` deve ser **imutável** (campos `final`, sem setters);
* o construtor de `Computador` deve ser privado;
* crie a classe interna estática `Computador.Builder`, com métodos encadeáveis (*fluent interface*) e o método `build()`;
* as regras de negócio devem ser validadas em `build()`, lançando `IllegalStateException`.

### Exemplo

```java
Computador pc = new Computador.Builder("Ryzen 5", 16)
        .ssd(512)
        .placaVideo("RTX 4060")
        .fonteWatts(650)
        .wifi(true)
        .build();

Computador basico = new Computador.Builder("Core i3", 8).build();

// Deve lançar IllegalStateException (fonte insuficiente):
Computador invalido = new Computador.Builder("Ryzen 7", 32)
        .placaVideo("RTX 4090")
        .build();
```

### Perguntas

1. Que problemas o Builder resolve em relação a construtores com muitos parâmetros e a setters?
2. Por que validar em `build()` e não em cada método do Builder?
3. Cite classes do JDK que utilizam Builder.

### Gabarito

**Padrão:** Builder.

```text
   Cliente
      │ configura passo a passo
      ▼
Computador.Builder ──── build() ───► Computador (imutável)
  .ssd(512)
  .placaVideo("RTX 4060")
  .wifi(true)
```

```java
public class Computador {
    private final String processador;
    private final int memoriaGb;
    private final int ssdGb;
    private final String placaVideo;
    private final int fonteWatts;
    private final boolean wifi;

    private Computador(Builder b) {
        this.processador = b.processador;
        this.memoriaGb = b.memoriaGb;
        this.ssdGb = b.ssdGb;
        this.placaVideo = b.placaVideo;
        this.fonteWatts = b.fonteWatts;
        this.wifi = b.wifi;
    }

    @Override
    public String toString() {
        return processador + " | " + memoriaGb + " GB RAM | SSD " + ssdGb
                + " GB | " + placaVideo + " | Fonte " + fonteWatts + " W | Wi-Fi: " + wifi;
    }

    public static class Builder {
        // obrigatórios
        private final String processador;
        private final int memoriaGb;

        // opcionais, com valores padrão
        private int ssdGb = 256;
        private String placaVideo = "Integrada";
        private int fonteWatts = 400;
        private boolean wifi = false;

        public Builder(String processador, int memoriaGb) {
            this.processador = processador;
            this.memoriaGb = memoriaGb;
        }

        public Builder ssd(int gb) {
            this.ssdGb = gb;
            return this;
        }

        public Builder placaVideo(String placaVideo) {
            this.placaVideo = placaVideo;
            return this;
        }

        public Builder fonteWatts(int watts) {
            this.fonteWatts = watts;
            return this;
        }

        public Builder wifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public Computador build() {
            if (memoriaGb < 4) {
                throw new IllegalStateException("Memória mínima: 4 GB");
            }
            if (!placaVideo.equals("Integrada") && fonteWatts < 500) {
                throw new IllegalStateException("Placa dedicada exige fonte de, no mínimo, 500 W");
            }
            return new Computador(this);
        }
    }
}
```

**Respostas:**

1. Com construtores longos, a ordem dos parâmetros é fácil de errar e o código fica ilegível (*telescoping constructors*). Com setters, o objeto fica mutável e pode existir em estado incompleto ou inválido. O Builder dá legibilidade, valores padrão e entrega o objeto **pronto e válido**.
2. Algumas regras dependem de **mais de um campo** (placa de vídeo e fonte), e só em `build()` todos os valores já foram informados. Além disso, a ordem das chamadas no Builder é livre.
3. `StringBuilder`, `Stream.builder()`, `HttpRequest.newBuilder()` e `Locale.Builder`.

**Conceito fundamental:** o Builder **separa a construção de um objeto complexo da sua representação**, montando-o em etapas e validando o resultado final. Aqui foi usada a variante comum em Java (Builder interno e fluente); o Builder clássico do GoF acrescenta uma interface de construtor e uma classe *Director*.

---

## Quadro-resumo

| Padrão | Categoria | Pergunta que ajuda a identificar | Ideia central |
| --- | --- | --- | --- |
| **Decorator** | Estrutural | Preciso **adicionar responsabilidades** combináveis, em tempo de execução, sem alterar a classe? | Envolve o objeto e mantém a mesma interface |
| **Proxy** | Estrutural | Preciso **controlar o acesso** ou a criação do objeto (permissão, cache, carregamento tardio, rede)? | Substituto com a mesma interface do objeto real |
| **Adapter** | Estrutural | Duas **interfaces incompatíveis** precisam trabalhar juntas? | Converte uma interface na que o cliente espera |
| **Prototype** | Criacional | É mais fácil **copiar um objeto pronto** do que construí-lo do zero? | Clona instâncias existentes (atenção à cópia profunda) |
| **Builder** | Criacional | O objeto é **complexo**, com muitos parâmetros opcionais e regras de validação? | Constrói passo a passo e entrega o objeto pronto |

### Distinções importantes

```text
DECORATOR vs PROXY
  Decorator → "O que mais este objeto deve fazer?"       (acrescenta)
  Proxy     → "Este objeto pode ser acessado agora/como?" (controla)

ADAPTER vs DECORATOR / PROXY
  Adapter              → muda a interface
  Decorator e Proxy    → mantêm a mesma interface

PROTOTYPE vs BUILDER
  Prototype → cria copiando algo que já existe
  Builder   → cria montando, parte por parte
```