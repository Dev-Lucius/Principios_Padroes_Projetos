# Simulado — Princípios e Padrões de Projeto

**Disciplina**: Princípios e Padrões de Projeto

**Objetivo**: Identificar, a partir de uma situação-problema, qual padrão de projeto é mais adequado: **Strategy**, **Observer** ou **Template Method**.

### Instruções

Para cada exercício:

- 1. Leia atentamente o problema.
- 2. Não implemente o sistema inteiro.
- 3. Identifique qual padrão de projeto melhor resolve o problema.
- 4. Justifique sua escolha com base na estrutura e no comportamento esperado do sistema.
- 5. Descreva brevemente como você aplicaria o padrão, indicando as principais classes/interfaces envolvidas.
- 6. Considere princípios de boas práticas, como baixo acoplamento, alta coesão, responsabilidade única e princípio aberto/fechado.

> **Atenção**: Os exercícios não informam diretamente qual padrão deve ser utilizado. Seu objetivo é descobrir o padrão a partir das características do problema.

--- 

## Questão 1 - Sistema de Pagamento

Uma loja virtual precisa implementar seu sistema de pagamentos.

Atualmente, o sistema permite pagamentos utilizando cartão de crédito. Entretanto, a empresa pretende adicionar futuramente outras formas de pagamento, como:

- PIX;
- Boleto bancário;
- PayPal;
- Carteiras digitais.

Cada forma de pagamento possui uma maneira diferente de realizar a operação. Por exemplo, o pagamento com cartão precisa validar os dados do cartão, enquanto o PIX precisa gerar uma cobrança e aguardar sua confirmação.

O desenvolvedor responsável inicialmente criou vários if/else dentro da classe Pedido:

```
se pagamento == "cartao"
    ...
senão se pagamento == "pix"
    ...
senão se pagamento == "boleto"
    ...
```

A equipe percebeu que, conforme novas formas de pagamento forem adicionadas, essa classe ficará cada vez mais complexa e difícil de manter.

### Sua tarefa

#### a) Qual padrão de projeto você utilizaria para solucionar esse problema?

#### b) Explique por que esse padrão é adequado.

#### c) Descreva uma possível estrutura de classes/interfaces para solucionar o problema.

#### d) Explique como a solução facilita a inclusão de uma nova forma de pagamento sem precisar modificar excessivamente o código existente.

--- 

## Questão 2 - Sistema de Notificações

Uma plataforma de cursos online possui um objeto responsável por representar o progresso de um aluno.

Sempre que o aluno concluir uma atividade, seu progresso é atualizado.

Atualmente, somente a própria plataforma precisa saber quando isso acontece. Porém, novos requisitos foram definidos:

- o sistema deve enviar um e-mail ao aluno;
- o aplicativo mobile deve atualizar sua interface;
- um sistema de estatísticas deve registrar o progresso;
- o sistema de gamificação deve verificar se o aluno ganhou uma conquista;
- futuramente, outros componentes poderão precisar ser avisados.

A implementação atual faz com que a classe ``Aluno`` conheça diretamente todas essas outras classes:

```
Aluno
 ├── EmailService
 ├── AplicativoMobile
 ├── SistemaEstatisticas
 └── SistemaGamificacao
```

A equipe considera essa abordagem problemática porque qualquer alteração na forma como uma notificação é realizada pode exigir alterações na classe ```Aluno```.

### Sua Tarefa

#### a) Qual padrão de projeto você utilizaria?

#### b) Qual é o principal problema de acoplamento existente na implementação atual?

#### c) Explique como o padrão escolhido permitiria que vários objetos fossem avisados quando o progresso do aluno fosse alterado.

#### d) Proponha uma estrutura conceitual contendo as principais interfaces/classes necessárias.

#### e) Explique como seria possível adicionar um novo mecanismo de notificação sem modificar a classe responsável pelo progresso do aluno.

--- 

## Questão 3 - Processamento de Relatórios

Uma empresa possui um sistema que gera relatórios.

Todos os relatórios seguem praticamente o mesmo processo:

```
1. Buscar os dados
2. Processar os dados
3. Gerar o relatório
4. Salvar o arquivo
```

Porém, existem diferentes tipos de relatório:

- Relatório financeiro;
- Relatório de vendas;
- Relatório de estoque.

A equipe percebeu que algumas etapas são iguais para todos os relatórios, enquanto outras possuem implementações diferentes.

Por exemplo:

```
Relatório Financeiro
    → busca dados financeiros
    → processa informações financeiras
    → gera PDF
    → salva arquivo

Relatório de Vendas
    → busca dados de vendas
    → processa informações de vendas
    → gera PDF
    → salva arquivo
```

O desenvolvedor inicialmente decidiu copiar todo o código de um relatório para criar os demais. Isso gerou bastante código duplicado.

A equipe deseja uma solução na qual o fluxo geral de execução permaneça padronizado, mas determinadas etapas possam ser especializadas pelas subclasses.

### Sua Tarefa

#### a) Qual padrão de projeto você utilizaria?

#### b) Qual característica do problema indica que esse padrão é adequado?

#### c) Como você estruturaria uma classe abstrata responsável por definir o fluxo de geração do relatório?

#### d) Quais métodos poderiam ser definidos na classe base e quais poderiam ser implementados pelas subclasses?

#### e) Explique como essa abordagem evita duplicação de código.

---

## Questão 4 - Sistema de Fretes

Uma empresa de comércio eletrônico precisa calcular o valor do frete de seus pedidos.

O cálculo pode variar de acordo com a modalidade escolhida pelo cliente:

Frete normal;
Frete expresso;
Frete econômico;
Retirada na loja.

Cada modalidade possui uma estratégia diferente de cálculo.

Por exemplo:

```
Frete normal:
    valor = distância × preçoPorKm

Frete expresso:
    valor = distância × preçoPorKm × fatorUrgência

Frete econômico:
    valor = distância × preçoPorKm × desconto
```

O sistema precisa permitir que a modalidade de frete seja escolhida durante a execução da aplicação.

Além disso, a empresa pretende adicionar novas modalidades futuramente.

O desenvolvedor percebeu que colocar todos os cálculos dentro da classe ``Pedido`` faria com que ela acumulasse diversas regras de negócio.

### Sua Tarefa

#### a) Qual padrão de projeto você utilizaria?

#### b) Explique por que esse problema possui características diferentes de uma simples estrutura de if/else.

#### c) Proponha uma interface ou classe abstrata que represente o comportamento comum entre as diferentes modalidades.

#### d) Como a classe Pedido utilizaria esse comportamento sem precisar conhecer os detalhes de cada cálculo?

#### e) Explique como sua solução permite trocar a modalidade de frete em tempo de execução.

---

## Questão 5 - Sistema de Exportação de Dados

Uma aplicação possui um processo para exportar informações de clientes.

O processo geral deve sempre seguir estas etapas:

```
1. Validar os dados
2. Buscar os clientes
3. Transformar os dados
4. Gerar o arquivo
5. Finalizar a exportação
```

Entretanto, o sistema precisa gerar diferentes formatos:

- CSV;
- JSON;
- XML.

A equipe percebeu que as etapas do processo são essencialmente as mesmas, mas a forma como os dados são transformados e o arquivo é gerado depende do formato escolhido.

A implementação atual possui três classes completamente independentes:

```
ExportadorCSV
ExportadorJSON
ExportadorXML
```

Cada uma possui seu próprio método responsável por executar todo o processo.

Isso fez com que alterações no fluxo de exportação precisassem ser realizadas em várias classes.

Por exemplo, se a empresa decidir adicionar uma nova etapa:

```
1. Validar os dados
2. Buscar os clientes
3. Registrar log
4. Transformar os dados
5. Gerar o arquivo
6. Finalizar a exportação
```

Seria necessário alterar todas as implementações.

### Sua Tarefa

#### a) Qual padrão de projeto seria mais adequado?

#### b) Identifique quais partes do algoritmo são comuns e quais são específicas de cada formato.

#### c) Explique como você estruturaria uma classe base para controlar o fluxo da exportação.

#### d) Como as subclasses poderiam personalizar apenas as etapas que realmente precisam ser diferentes?

#### e) Explique por que essa solução é preferível à criação de três algoritmos completamente independentes.

--- 

## Desafio Final

Depois de resolver as cinco questões, tente completar a tabela sem consultar suas respostas anteriores:

| Padrão              | Problema que ele costuma resolver | Ideia principal |
| ------------------- | --------------------------------- | --------------- |
| **Strategy**        | ?                                 | ?               |
| **Observer**        | ?                                 | ?               |
| **Template Method** | ?                                 | ?               |


--- 

## Dica

Ao ler um enunciado, tente fazer estas três perguntas:

#### 1. "Tenho vários comportamentos diferentes que podem ser trocados?"
→ Pense em Strategy.

#### 2. "Tenho um objeto que precisa avisar vários outros quando algo acontece?"
→ Pense em Observer.

#### 3. "Tenho vários algoritmos com o mesmo fluxo geral, mas algumas etapas diferentes?"
→ Pense em Template Method.

> **Importante**: não escolha o padrão apenas porque uma palavra-chave apareceu no enunciado. Procure entender qual problema de projeto está sendo apresentado e qual relação entre classes precisa ser melhorada.