# Lista de Exercícios — Padrões de Projeto em Java

## Strategy • Observer • Template Method • Decorator • Command • Iterator • State

### Orientações

Para cada exercício:

1. Identifique o problema apresentado.
2. Identifique o padrão de projeto adequado.
3. Modele as principais classes/interfaces.
4. Implemente a solução em Java.
5. Crie uma classe `Main` para demonstrar o funcionamento.
6. Evite concentrar a solução em grandes blocos de `if/else` ou `switch` quando o objetivo for justamente aplicar um padrão de projeto.

---

# 1. Strategy — Ordenação de produtos

Uma aplicação de comércio eletrônico precisa permitir que os produtos sejam exibidos utilizando diferentes critérios de ordenação:

* menor preço;
* maior preço;
* nome em ordem alfabética;
* maior avaliação.

A classe `Catalogo` não deve conhecer as regras específicas de cada tipo de ordenação.

### Tarefas

Implemente uma solução utilizando **Strategy**.

Crie uma interface:

```java
public interface EstrategiaOrdenacao {
    void ordenar(List<Produto> produtos);
}
```

Crie pelo menos quatro estratégias:

```text
OrdenarPorMenorPreco
OrdenarPorMaiorPreco
OrdenarPorNome
OrdenarPorAvaliacao
```

A classe `Catalogo` deverá receber a estratégia e utilizá-la para ordenar seus produtos.

### Exemplo de utilização

```java
Catalogo catalogo = new Catalogo();

catalogo.setEstrategia(new OrdenarPorMenorPreco());
catalogo.ordenar();

catalogo.setEstrategia(new OrdenarPorNome());
catalogo.ordenar();
```

### Gabarito

**Padrão:** Strategy.

A ideia central é encapsular diferentes **algoritmos** em classes separadas.

```text
              EstrategiaOrdenacao
                      ▲
          ┌───────────┼───────────┐
          │           │           │
    MenorPreco      PorNome    Avaliacao
          │
          ▼
       Catalogo
```

A classe `Catalogo` depende da abstração:

```java
private EstrategiaOrdenacao estrategia;
```

e não diretamente de uma implementação específica.

---

# 2. Strategy — Cálculo de frete

Uma empresa possui diferentes formas de calcular o frete:

* PAC;
* SEDEX;
* retirada na loja;
* transportadora.

Cada modalidade possui uma regra diferente de cálculo.

### Tarefas

Implemente:

```java
public interface CalculoFrete {
    double calcular(double peso, double distancia);
}
```

Crie pelo menos quatro estratégias:

```text
FretePAC
FreteSedex
FreteRetirada
FreteTransportadora
```

A classe `Pedido` deverá utilizar uma estratégia de cálculo de frete sem conhecer os detalhes de cada modalidade.

### Exemplo

```java
Pedido pedido = new Pedido();

pedido.setCalculoFrete(new FretePAC());

double valor = pedido.calcularFrete();
```

Depois:

```java
pedido.setCalculoFrete(new FreteSedex());

double valor = pedido.calcularFrete();
```

### Gabarito

**Padrão:** Strategy.

```text
             CalculoFrete
                  ▲
       ┌──────────┼──────────┐
       │          │          │
      PAC       SEDEX   Transportadora
       │          │          │
       └──────────┼──────────┘
                  ▼
                Pedido
```

O algoritmo de cálculo pode ser trocado sem modificar `Pedido`.

---

# 3. Observer — Sistema de notificações

Uma plataforma de vendas precisa avisar diferentes interessados quando um pedido muda de status.

Quando o pedido passa para:

```text
PAGO
ENVIADO
ENTREGUE
CANCELADO
```

devem ser notificados:

* cliente;
* sistema de estoque;
* sistema de entrega;
* sistema de emissão de nota fiscal.

### Tarefas

Implemente utilizando **Observer**.

Crie uma interface:

```java
public interface Observador {
    void atualizar(String status);
}
```

A classe `Pedido` será o objeto observado.

### Exemplo

```java
Pedido pedido = new Pedido();

pedido.adicionarObservador(new Cliente());
pedido.adicionarObservador(new Estoque());
pedido.adicionarObservador(new Transportadora());

pedido.alterarStatus("ENVIADO");
```

### Gabarito

**Padrão:** Observer.

```text
                    Pedido
                  (Subject)
                      │
          ┌───────────┼───────────┐
          ▼           ▼           ▼
       Cliente     Estoque   Transportadora
      Observer     Observer     Observer
```

O `Pedido` mantém uma lista de observadores.

Quando o status muda:

```java
for (Observador observador : observadores) {
    observador.atualizar(status);
}
```

---

# 4. Observer — Sistema de temperatura

Uma estação meteorológica possui um sensor de temperatura.

Sempre que a temperatura mudar, diferentes componentes precisam ser atualizados:

* painel da temperatura;
* gráfico;
* sistema de alerta;
* aplicativo mobile.

### Tarefas

Implemente:

```text
EstacaoMeteorologica
```

como `Subject`.

Implemente os demais componentes como `Observers`.

Teste:

```java
estacao.setTemperatura(25);
estacao.setTemperatura(31);
estacao.setTemperatura(18);
```

### Gabarito

**Padrão:** Observer.

```text
             EstacaoMeteorologica
                    Subject
                       │
       ┌───────────────┼────────────────┐
       ▼               ▼                ▼
     Painel          Grafico          Alerta
    Observer        Observer         Observer
```

Ao alterar a temperatura, todos os observadores cadastrados são notificados.

---

# 5. Template Method — Geração de relatórios

Uma empresa possui diferentes tipos de relatórios.

Todos os relatórios seguem o mesmo processo:

```text
1. Abrir relatório
2. Inserir cabeçalho
3. Inserir dados
4. Inserir rodapé
5. Fechar relatório
```

Entretanto, a forma de gerar o cabeçalho, os dados e o rodapé depende do tipo de relatório.

Existem atualmente:

```text
RelatorioPDF
RelatorioHTML
RelatorioCSV
```

### Tarefas

Crie uma classe abstrata:

```java
public abstract class Relatorio {

    public final void gerar() {
        abrir();
        cabecalho();
        dados();
        rodape();
        fechar();
    }

    protected abstract void abrir();

    protected abstract void cabecalho();

    protected abstract void dados();

    protected abstract void rodape();

    protected abstract void fechar();
}
```

Implemente as três subclasses.

### Exemplo

```java
Relatorio relatorio = new RelatorioPDF();

relatorio.gerar();

relatorio = new RelatorioHTML();

relatorio.gerar();
```

### Gabarito

**Padrão:** Template Method.

O método:

```java
gerar()
```

define o **esqueleto do algoritmo**.

```text
                  Relatorio
                      │
                    gerar()
                      │
       ┌──────────────┼──────────────┐
       ▼              ▼              ▼
   RelatorioPDF   RelatorioHTML   RelatorioCSV
```

As subclasses implementam os detalhes de cada etapa.

### Conceito fundamental

No **Template Method**, o algoritmo geral permanece definido na superclasse.

```text
Algoritmo geral = fixo
Etapas específicas = variáveis
```

---

# 6. Template Method — Importação de arquivos

Um sistema precisa importar dados de diferentes formatos:

* CSV;
* JSON;
* XML.

O processo geral é:

```text
1. Abrir arquivo
2. Ler dados
3. Processar dados
4. Validar dados
5. Salvar dados
6. Fechar arquivo
```

### Tarefas

Crie:

```java
ImportadorDados
```

como classe abstrata.

Crie:

```text
ImportadorCSV
ImportadorJSON
ImportadorXML
```

O método `importar()` deverá controlar o algoritmo.

### Gabarito

**Padrão:** Template Method.

```text
              ImportadorDados
                     │
                  importar()
                     │
       ┌─────────────┼─────────────┐
       ▼             ▼             ▼
   Importador     Importador    Importador
      CSV            JSON          XML
```

A superclasse determina a sequência das operações.

As subclasses implementam as particularidades de cada formato.

---

# 7. Decorator — Sistema de café

Uma cafeteria deseja permitir que o cliente monte sua bebida adicionando ingredientes.

Bebidas:

```text
Cafe
Cha
Chocolate
```

Adicionais:

```text
Leite
Canela
Chantilly
Caramelo
```

O cliente pode combinar vários adicionais:

```text
Café
+ leite
+ chantilly
+ caramelo
```

### Tarefas

Implemente utilizando **Decorator**.

Crie:

```java
public interface Bebida {
    String descricao();
    double preco();
}
```

Depois implemente os decoradores.

### Exemplo

```java
Bebida bebida = new Cafe();

bebida = new Leite(bebida);
bebida = new Chantilly(bebida);
bebida = new Caramelo(bebida);

System.out.println(bebida.descricao());
System.out.println(bebida.preco());
```

### Gabarito

**Padrão:** Decorator.

```text
                    Bebida
                      ▲
          ┌───────────┴───────────┐
          │                       │
         Cafe              DecoradorBebida
                                  ▲
                     ┌────────────┼───────────┐
                     ▼            ▼           ▼
                   Leite      Chantilly    Caramelo
```

Cada decorador envolve o objeto anterior.

---

# 8. Decorator — Sistema de notificações

Um sistema possui uma notificação básica:

```text
Email
```

Porém, o usuário pode adicionar:

* SMS;
* WhatsApp;
* Push;
* log.

Uma notificação poderia ser:

```text
Email
+ SMS
+ Push
+ Log
```

### Tarefas

Crie uma solução utilizando Decorator.

O código:

```java
Notificacao notificacao = new Email();

notificacao = new SMS(notificacao);
notificacao = new Push(notificacao);
notificacao = new Log(notificacao);

notificacao.enviar("Pedido enviado");
```

deve executar todas as funcionalidades.

### Gabarito

**Padrão:** Decorator.

A principal ideia é adicionar responsabilidades dinamicamente.

Sem Decorator poderiam surgir muitas combinações:

```text
EmailComSMS
EmailComPush
EmailComSMSComPush
EmailComSMSComPushComLog
...
```

Com Decorator:

```text
Email
  ↓
SMS
  ↓
Push
  ↓
Log
```

---

# 9. Command — Sistema de impressão

Uma universidade possui um sistema de impressão.

O usuário pode solicitar diferentes operações:

```text
Imprimir documento
Cancelar impressão
Pausar impressão
Retomar impressão
```

O sistema possui uma fila de impressão.

Cada solicitação deve ser transformada em um objeto que possa ser colocado na fila e executado posteriormente.

### Tarefas

Crie:

```java
public interface Comando {
    void executar();
}
```

Crie uma classe:

```text
Impressora
```

com os métodos:

```java
imprimir(String documento);
cancelar(String documento);
pausar();
retomar();
```

Depois crie:

```text
ImprimirCommand
CancelarCommand
PausarCommand
RetomarCommand
```

### Exemplo

```java
Impressora impressora = new Impressora();

Comando comando1 =
    new ImprimirCommand(impressora, "trabalho.pdf");

Comando comando2 =
    new PausarCommand(impressora);

comando1.executar();
comando2.executar();
```

### Desafio

Crie:

```java
FilaDeImpressao
```

que permita:

```java
fila.adicionar(comando1);
fila.adicionar(comando2);
```

e posteriormente:

```java
fila.executarTodos();
```

### Gabarito

**Padrão:** Command.

A ideia central é transformar uma **ação em um objeto**.

```text
                  Comando
                     ▲
          ┌──────────┼──────────┐
          │          │          │
      Imprimir     Pausar     Cancelar
          │          │          │
          └──────────┼──────────┘
                     ▼
                 Impressora

                     ▲
                     │
              FilaDeImpressao
```

A `FilaDeImpressao` trabalha com `Comando`, sem precisar conhecer os detalhes da impressora.

---

# 10. Command — Editor de texto

Crie um editor de texto que permita executar operações:

```text
Inserir texto
Remover texto
Salvar
```

Cada operação deve ser representada por um objeto `Command`.

### Tarefas

Crie uma interface:

```java
public interface Comando {
    void executar();
}
```

Crie comandos para as operações do editor.

### Desafio

Acrescente:

```text
desfazer()
```

Cada comando deverá possuir:

```java
executar()
desfazer()
```

### Gabarito

**Padrão:** Command.

Uma solução pode utilizar:

```java
Stack<Comando> historico;
```

Ao executar:

```java
comando.executar();
historico.push(comando);
```

Para desfazer:

```java
Comando comando = historico.pop();
comando.desfazer();
```

O padrão facilita a implementação de funcionalidades como:

* histórico;
* desfazer;
* fila;
* repetição de comandos.

---

# 11. Iterator — Coleção de alunos

Crie uma classe:

```text
Turma
```

que armazene alunos internamente.

O usuário da classe não deve precisar conhecer se os alunos estão armazenados em:

```text
ArrayList
Array
LinkedList
```

### Tarefas

Implemente um Iterator para percorrer os alunos.

O código cliente deverá ser semelhante a:

```java
Iterator<Aluno> iterator = turma.iterator();

while (iterator.hasNext()) {
    Aluno aluno = iterator.next();

    System.out.println(aluno.getNome());
}
```

### Gabarito

**Padrão:** Iterator.

```text
                  Turma
                    │
                    │ cria
                    ▼
                 Iterator
                    │
           ┌────────┴────────┐
           ▼                 ▼
       hasNext()           next()
```

A estrutura interna da `Turma` fica encapsulada.

---

# 12. Iterator — Playlist

Uma aplicação de música possui uma playlist.

A playlist contém objetos:

```text
Musica
```

Cada música possui:

```text
titulo
artista
duracao
```

### Tarefas

Implemente um Iterator que permita:

```java
while (iterator.hasNext()) {
    Musica musica = iterator.next();

    System.out.println(musica);
}
```

### Desafio

Crie dois iteradores:

```text
Iterator normal
Iterator reverso
```

O cliente não deve conhecer como a playlist está armazenada.

### Gabarito

**Padrão:** Iterator.

```text
                 Playlist
                    │
          ┌─────────┴─────────┐
          ▼                   ▼
  IteratorNormal       IteratorReverso
```

A `Playlist` é responsável pelo armazenamento.

O `Iterator` é responsável pela navegação.

---

# 13. State — Máquina de venda automática

Uma máquina de venda automática possui diferentes estados:

```text
SEM_PRODUTO
COM_PRODUTO
PAGAMENTO
PRODUTO_LIBERADO
```

O comportamento da máquina depende do estado atual.

Por exemplo:

* em `SEM_PRODUTO`, o cliente pode selecionar um produto;
* em `COM_PRODUTO`, o cliente pode iniciar o pagamento;
* em `PAGAMENTO`, o cliente pode inserir dinheiro ou cancelar;
* quando o valor necessário é atingido, o produto é liberado;
* depois da liberação, a máquina volta ao estado `SEM_PRODUTO`.

### Tarefas

Implemente utilizando o padrão **State**.

Crie a interface:

```java
public interface EstadoMaquina {

    void selecionarProduto(Maquina maquina);

    void inserirDinheiro(Maquina maquina, double valor);

    void cancelar(Maquina maquina);

    void liberarProduto(Maquina maquina);
}
```

Crie as classes:

```text
EstadoSemProduto
EstadoComProduto
EstadoPagamento
EstadoProdutoLiberado
```

A classe `Maquina` deverá manter o estado atual:

```java
private EstadoMaquina estado;
```

### Exemplo

```java
Maquina maquina = new Maquina();

maquina.selecionarProduto();

maquina.inserirDinheiro(2.00);

maquina.inserirDinheiro(3.00);

maquina.liberarProduto();
```

### Gabarito

**Padrão:** State.

```text
                 Maquina
                    │
                    ▼
              EstadoMaquina
                    ▲
       ┌────────────┼────────────┐
       │            │            │
       ▼            ▼            ▼
 SemProduto    ComProduto    Pagamento
                                   │
                                   ▼
                          ProdutoLiberado
                                   │
                                   ▼
                              SemProduto
```

O ponto principal é evitar uma classe `Maquina` cheia de condicionais:

```java
if (estado == SEM_PRODUTO) {
    ...
} else if (estado == COM_PRODUTO) {
    ...
} else if (estado == PAGAMENTO) {
    ...
}
```

Cada estado passa a ser responsável pelo comportamento correspondente.

---

# 14. State — Personagem de jogo

Um personagem de jogo pode estar nos estados:

```text
PARADO
CORRENDO
PULANDO
ATACANDO
MORTO
```

O comportamento de:

```text
andar()
pular()
atacar()
```

depende do estado atual.

Por exemplo:

* personagem morto não pode atacar;
* personagem pulando não pode iniciar outro pulo;
* personagem parado pode correr;
* personagem correndo pode parar.

### Tarefas

Implemente utilizando **State**.

Crie:

```java
EstadoPersonagem
```

e classes concretas para cada estado.

### Gabarito

**Padrão:** State.

```text
                    Personagem
                        │
                        ▼
                EstadoPersonagem
                        ▲
           ┌────────────┼────────────┐
           │            │            │
         Parado       Correndo     Pulando
           │            │            │
           └────────────┼────────────┘
                        │
                      Morto
```

O `Personagem` mantém uma referência para o estado atual e delega a ele as operações.

---


# Resumo — Como identificar cada padrão?

```text
PADRÕES COMPORTAMENTAIS
│
├── STRATEGY
│   │
│   └── "Tenho vários algoritmos para fazer a mesma coisa."
│
├── OBSERVER
│   │
│   └── "Quando algo mudar, preciso avisar vários objetos."
│
├── TEMPLATE METHOD
│   │
│   └── "O processo é fixo, mas algumas etapas variam."
│
├── DECORATOR
│   │
│   └── "Quero adicionar responsabilidades dinamicamente."
│
├── COMMAND
│   │
│   └── "Quero transformar uma ação em objeto."
│
├── ITERATOR
│   │
│   └── "Quero percorrer uma coleção sem conhecer
│       sua estrutura interna."
│
└── STATE
    │
    └── "O comportamento muda conforme o estado atual."
```

## Diferenças importantes

| Padrão              | Pergunta que ajuda a identificar                                      |
| ------------------- | --------------------------------------------------------------------- |
| **Strategy**        | Tenho **algoritmos diferentes** que podem ser trocados?               |
| **Observer**        | Preciso **notificar vários objetos** quando algo acontece?            |
| **Template Method** | Tenho um **algoritmo com sequência fixa**, mas algumas etapas variam? |
| **Decorator**       | Preciso **adicionar funcionalidades** sem alterar a classe original?  |
| **Command**         | Preciso transformar uma **ação em objeto**?                           |
| **Iterator**        | Preciso **percorrer uma coleção** sem expor sua estrutura?            |
| **State**           | O comportamento muda conforme o **estado atual** do objeto?           |

### Uma distinção especialmente importante

```text
STRATEGY
   ↓
"Qual algoritmo vou utilizar?"

STATE
   ↓
"Em que estado estou e qual comportamento
devo apresentar agora?"
```

Por exemplo:

```text
Strategy
Pedido → FretePAC
       → FreteSedex
       → FreteTransportadora
```

O cliente/sistema escolhe a estratégia.

Já no State:

```text
Máquina
   ↓
SemProduto
   ↓ selecionar
ComProduto
   ↓ pagar
Pagamento
   ↓ valor suficiente
ProdutoLiberado
   ↓
SemProduto
```

O **estado muda durante o funcionamento do objeto**, e essa mudança altera o comportamento disponível.

