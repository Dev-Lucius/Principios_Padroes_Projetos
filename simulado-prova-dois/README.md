# Lista de Exercícios — Padrões de Projeto em Java

Decorator • Proxy • Adapter

## Orientações

Para cada exercício:

1. Identifique o problema apresentado.
2. Identifique o padrão de projeto adequado.
3. Modele as principais classes/interfaces.
4. Implemente a solução em Java.
5. Crie uma classe `Main` para demonstrar o funcionamento.
6. Evite concentrar a solução em grandes blocos de `if/else` ou `switch` quando o objetivo for justamente aplicar um padrão de projeto.

Lembrete: Decorator, Proxy e Adapter têm estruturas parecidas — um objeto que envolve outro e delega chamadas a ele. O que muda é a **intenção** de cada padrão.

## 1. Decorator — Formatação de texto

Um editor de mensagens possui um texto básico:

```text
TextoSimples
```

O usuário pode aplicar formatações combináveis:

```text
Negrito
Italico
Sublinhado
Maiusculas
```

Por exemplo: um texto em negrito e itálico, ou em maiúsculas e sublinhado.

### Tarefas

Crie a interface:

```java
public interface Texto {
    String renderizar();
}
```

Implemente `TextoSimples` e os quatro decoradores, todos derivados de uma classe abstrata `DecoradorTexto`.

### Exemplo

```java
Texto texto = new TextoSimples("Padrões de Projeto");

texto = new Negrito(texto);
texto = new Italico(texto);

System.out.println(texto.renderizar());
// <i><b>Padrões de Projeto</b></i>
```

### Desafio

Troque a ordem dos decoradores e compare as saídas. A ordem faz diferença? Teste `Maiusculas` aplicado antes e depois de `Negrito` e explique o que acontece com as tags.

### Gabarito

**Padrão:** Decorator.

```text
                  Texto
                    ▲
         ┌──────────┴───────────┐
         │                      │
    TextoSimples          DecoradorTexto
                                ▲
              ┌─────────────────┼──────────────┐
              ▼                 ▼              ▼
           Negrito           Italico       Maiusculas
```

O decorador implementa a mesma interface do objeto decorado e guarda uma referência a ele:

```java
public abstract class DecoradorTexto implements Texto {
    protected Texto texto;

    public DecoradorTexto(Texto texto) {
        this.texto = texto;
    }
}

public class Negrito extends DecoradorTexto {
    public Negrito(Texto texto) {
        super(texto);
    }

    @Override
    public String renderizar() {
        return "<b>" + texto.renderizar() + "</b>";
    }
}
```

Se `Maiusculas` envolver `Negrito`, o resultado passa a ter `<B>` e `</B>`, porque o texto das tags também é convertido. Por isso a ordem dos decoradores importa.

## 2. Decorator — Fluxo de dados

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

1. A chamada percorre a cadeia de fora para dentro: log → criptografia → compactação → arquivo.
2. Não. Dados criptografados parecem aleatórios e compactam mal. O correto é compactar **antes** de criptografar, ou seja, `FluxoCompactado` deve ficar por fora de `FluxoCriptografado` na montagem:

```java
FluxoDados fluxo = new FluxoArquivo("dados.txt");

fluxo = new FluxoCriptografado(fluxo);
fluxo = new FluxoCompactado(fluxo);
fluxo = new FluxoComLog(fluxo);
```

3. `BufferedReader`, `BufferedOutputStream`, `GZIPOutputStream` e `DataInputStream` envolvem um fluxo existente e acrescentam funcionalidade.

## 3. Decorator — Ingresso de cinema

Um cinema vende ingressos que podem receber adicionais combináveis:

```text
MeiaEntrada   (50% do valor acumulado)
Sessao3D      (+ R$ 12,00)
SalaVIP       (+ R$ 25,00)
ComboPipoca   (+ R$ 18,00)
```

O ingresso comum custa R$ 30,00.

### Tarefas

Implemente utilizando **Decorator**:

```java
public interface Ingresso {
    String descricao();
    double preco();
}
```

Crie `IngressoComum` e os quatro decoradores.

### Exemplo

```java
Ingresso ingresso = new IngressoComum();

ingresso = new Sessao3D(ingresso);
ingresso = new MeiaEntrada(ingresso);
ingresso = new ComboPipoca(ingresso);

System.out.println(ingresso.descricao());
System.out.println(ingresso.preco());
```

### Perguntas

1. Qual é o preço final no exemplo acima?
2. E se `MeiaEntrada` for aplicada **antes** de `Sessao3D`?
3. Se cada combinação fosse uma subclasse (`IngressoVip3DComCombo`, ...), quantas classes seriam necessárias para 4 adicionais?

### Gabarito

**Padrão:** Decorator.

1. 30 → 3D (42) → meia-entrada (21) → combo (39). Preço final: **R$ 39,00**.
2. 30 → meia-entrada (15) → 3D (27) → combo (45). Preço final: **R$ 45,00**. A ordem altera o resultado quando um decorador depende do valor acumulado.
3. Com 4 adicionais opcionais existem 2⁴ = 16 combinações (15 além do ingresso comum), o que gera uma explosão de subclasses. Com Decorator bastam `IngressoComum` e os 4 decoradores (mais uma classe abstrata, se desejado).

## 4. Decorator — Corrigindo o código

O código abaixo tenta aplicar o padrão Decorator, mas não funciona como esperado.

```java
public interface Produto {
    String descricao();
    double preco();
}

public class Notebook implements Produto {
    public String descricao() { return "Notebook"; }
    public double preco() { return 3000.0; }
}

public class GarantiaEstendida implements Produto {
    private Produto produto;

    public GarantiaEstendida(Produto produto) {
        this.produto = produto;
    }

    public String descricao() {
        return "Garantia estendida";
    }

    public double preco() {
        return 300.0;
    }
}
```

Utilização:

```java
Produto p = new Notebook();
p = new GarantiaEstendida(p);

System.out.println(p.descricao() + " - " + p.preco());
```

Saída esperada: `Notebook + Garantia estendida - 3300.0`

Saída obtida: `Garantia estendida - 300.0`

### Tarefas

1. Explique o erro.
2. Corrija `GarantiaEstendida`.
3. Crie uma classe abstrata `DecoradorProduto` para evitar repetição de código nos decoradores.
4. Crie um decorador `Seguro`, que acrescenta 10% sobre o preço acumulado.
5. A classe `Notebook` precisa ser modificada para receber novos decoradores?

### Gabarito

**Padrão:** Decorator.

**Erro (item 1).** `GarantiaEstendida` recebe o produto, mas nunca o utiliza: não delega para `produto.descricao()` nem para `produto.preco()`. O objeto decorado é descartado e o decorador passa a funcionar como um produto independente.

**Correção (itens 2 e 3).** A delegação fica na classe abstrata e o acréscimo, nas subclasses:

```java
public abstract class DecoradorProduto implements Produto {
    protected Produto produto;

    public DecoradorProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String descricao() { return produto.descricao(); }

    @Override
    public double preco() { return produto.preco(); }
}

public class GarantiaEstendida extends DecoradorProduto {
    public GarantiaEstendida(Produto produto) { super(produto); }

    @Override
    public String descricao() { return super.descricao() + " + Garantia estendida"; }

    @Override
    public double preco() { return super.preco() + 300.0; }
}
```

**Seguro (item 4).** Segue o mesmo molde, com `return super.preco() * 1.10;`.

**Item 5.** Não. Novos decoradores são adicionados sem alterar as classes existentes (princípio aberto/fechado).

## 5. Proxy — Carregamento tardio de imagens

Um visualizador de documentos exibe várias imagens de alta resolução. Carregar uma imagem do disco é uma operação cara, e o usuário normalmente visualiza só algumas delas.

As imagens só devem ser carregadas quando forem realmente exibidas.

### Tarefas

Implemente utilizando **Proxy**:

```java
public interface Imagem {
    void exibir();
}
```

- `ImagemReal`: ao ser construída, "carrega" o arquivo (imprima `Carregando arquivo.png do disco...`).
- `ImagemProxy`: guarda apenas o nome do arquivo e só cria a `ImagemReal` na primeira chamada de `exibir()`.

### Exemplo

```java
Imagem foto1 = new ImagemProxy("foto1.png");
Imagem foto2 = new ImagemProxy("foto2.png");
Imagem foto3 = new ImagemProxy("foto3.png");

foto1.exibir();
foto1.exibir();
foto3.exibir();
```

### Perguntas

1. Quantos carregamentos ocorrem no exemplo?
2. O código cliente precisa saber que está usando um proxy?

### Gabarito

**Padrão:** Proxy (proxy virtual).

```text
          Imagem
            ▲
     ┌──────┴───────┐
     │              │
 ImagemReal     ImagemProxy ── cria sob demanda ──► ImagemReal
```

```java
public class ImagemProxy implements Imagem {
    private final String arquivo;
    private ImagemReal imagemReal;

    public ImagemProxy(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void exibir() {
        if (imagemReal == null) {
            imagemReal = new ImagemReal(arquivo);
        }
        imagemReal.exibir();
    }
}
```

1. Dois carregamentos: `foto1.png` (uma única vez, apesar de duas exibições) e `foto3.png`. A `foto2.png` nunca é carregada.
2. Não. O proxy implementa a mesma interface `Imagem`.

## 6. Proxy — Controle de acesso a documentos

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
@Override
public void editar(String novoConteudo) {
    if (usuario.getPerfil() == Perfil.LEITOR) {
        throw new AcessoNegadoException("Sem permissão para editar");
    }
    documento.editar(novoConteudo);
}
```

O proxy **controla o acesso** ao objeto real. A classe `DocumentoReal` mantém apenas a lógica de negócio.

## 7. Proxy — Cache de cotações

Um sistema financeiro consulta a cotação de moedas em um serviço externo. Cada consulta é lenta (cerca de 2 segundos) e a mesma moeda é consultada várias vezes.

### Tarefas

Crie:

```java
public interface ServicoCotacao {
    double obterCotacao(String moeda);
}
```

- `ServicoCotacaoReal`: simula a consulta lenta com `Thread.sleep(2000)` e devolve valores fixos para cada moeda.
- `ServicoCotacaoProxy`: guarda em um `Map<String, Double>` as cotações já consultadas e só chama o serviço real quando a moeda ainda não estiver em cache.

### Exemplo

```java
ServicoCotacao servico = new ServicoCotacaoProxy(new ServicoCotacaoReal());

long inicio = System.currentTimeMillis();
servico.obterCotacao("USD");
System.out.println(System.currentTimeMillis() - inicio); // ~2000 ms

inicio = System.currentTimeMillis();
servico.obterCotacao("USD");
System.out.println(System.currentTimeMillis() - inicio); // ~0 ms
```

### Desafio

Faça o cache expirar após 10 segundos (guarde o instante em que cada cotação foi consultada).

### Gabarito

**Padrão:** Proxy (proxy de cache).

```java
@Override
public double obterCotacao(String moeda) {
    if (!cache.containsKey(moeda)) {
        cache.put(moeda, servicoReal.obterCotacao(moeda));
    }
    return cache.get(moeda);
}
```

Para o desafio, o cache pode armazenar um objeto com o valor e o horário da consulta, e o proxy compara `System.currentTimeMillis()` com esse horário antes de reutilizar o valor.

O cliente continua usando `ServicoCotacao`, sem saber se a resposta veio do cache ou do serviço real.

## 8. Proxy — Serviço remoto de previsão do tempo

Uma aplicação consulta a previsão do tempo em um servidor remoto. O código da aplicação não deve se preocupar com conexão, envio da requisição, falhas de rede ou novas tentativas.

### Tarefas

Crie:

```java
public interface ServicoClima {
    String previsao(String cidade);
}
```

- `ServidorClima` (implementa `ServicoClima`): representa o serviço remoto. Para simular instabilidade, lance `ConexaoFalhouException` em cerca de 50% das chamadas (use `Random`).
- `ServicoClimaProxy` (implementa `ServicoClima`): representa o serviço remoto na aplicação. Deve abrir a "conexão", enviar a requisição, tentar novamente até 3 vezes em caso de falha e fechar a "conexão" (use mensagens no console).

### Exemplo

```java
ServicoClima servico = new ServicoClimaProxy(new ServidorClima());

System.out.println(servico.previsao("São Paulo"));
```

### Gabarito

**Padrão:** Proxy (proxy remoto, simulado).

```text
Aplicação ──► ServicoClimaProxy ══ rede ══► ServidorClima
              (representante local)         (objeto remoto)
```

O proxy esconde os detalhes de comunicação e oferece ao cliente a mesma interface do objeto remoto. Em aplicações reais, esse papel é desempenhado por *stubs* de RMI, gRPC e clientes HTTP gerados por bibliotecas.

Um esboço do método principal:

```java
@Override
public String previsao(String cidade) {
    for (int tentativa = 1; tentativa <= 3; tentativa++) {
        try {
            return servidor.previsao(cidade);
        } catch (ConexaoFalhouException e) {
            System.out.println("Falha na tentativa " + tentativa);
        }
    }
    throw new IllegalStateException("Serviço indisponível");
}
```

## 9. Adapter — Gateways de pagamento

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

- converta reais para centavos com `(int) Math.round(valor * 100)`;
- considere o pagamento do PagSeguro bem-sucedido quando nenhuma exceção for lançada;
- considere o pagamento do Mercado Pago bem-sucedido quando o retorno for `"APROVADO"`.

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
```

O `Checkout` (Client) nunca conhece as classes dos fornecedores. Para trocar de fornecedor, basta trocar o adaptador.

## 10. Adapter — Sensores de temperatura legados

Um sistema de monitoramento trabalha com a interface:

```java
public interface SensorTemperatura {
    double getTemperaturaCelsius();
}
```

Porém, parte dos sensores instalados é de um fabricante antigo, e a classe fornecida **não pode ser modificada**:

```java
public class SensorLegadoFahrenheit {
    public double lerFahrenheit() {
        return 98.6;
    }
}
```

### Tarefas

1. Crie `SensorFahrenheitAdapter`, que converte a leitura para Celsius: `C = (F - 32) × 5 / 9`.
2. Crie `SensorCelsius` (um sensor nativo) e monte uma `List<SensorTemperatura>` com sensores nativos e adaptados.
3. Calcule a média das temperaturas sem que o código de cálculo saiba quais sensores são legados.

### Desafio

Crie também `SensorLegadoKelvin` (com `lerKelvin()`) e seu adaptador (`C = K - 273,15`).

### Gabarito

**Padrão:** Adapter.

```java
public class SensorFahrenheitAdapter implements SensorTemperatura {
    private final SensorLegadoFahrenheit sensor;

    public SensorFahrenheitAdapter(SensorLegadoFahrenheit sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getTemperaturaCelsius() {
        return (sensor.lerFahrenheit() - 32) * 5 / 9;
    }
}
```

O adaptador não apenas troca o nome do método: ele também **converte os dados** para o formato esperado pelo cliente. O resultado para 98,6 °F é 37 °C.