# Questão 1 — Cenário A: O Pesadelo da Explosão de Classes

## (a) Padrão: **Decorator** — categoria GoF: **Estrutural**

**Por que evita a explosão de heranças:** com herança, cada combinação de opcionais exige uma
subclasse. Com N opcionais isso chega a 2^N combinações (4 opcionais = 16 classes; com
"proteção de vidros" passam a ser 32). Com Decorator existe **uma classe por opcional** (N classes),
e as combinações são montadas **em tempo de execução**, embrulhando um objeto no outro:

    new CarroReserva(new CoberturaRoubo(new SeguroBasico()))

Um novo opcional (proteção de vidros) é **uma** classe nova, sem tocar em nenhuma existente.

Decorador e componente compartilham a interface `Seguro`, então o cliente não distingue um seguro
"puro" de um embrulhado. Cada camada soma seu custo e delega para a camada interna.

## (b) Estrutura

| Papel | Classe |
|---|---|
| Componente (interface) | `Seguro` |
| Componente concreto | `SeguroBasico` (colisão) |
| Decorador base | `SeguroDecorator` (é um `Seguro` e tem um `Seguro`) |
| Decoradores concretos | `CoberturaRoubo`, `Assistencia24h`, `CarroReserva`, `DanosTerceiros`, `ProtecaoVidros` |

Cálculo encadeado: `preco() = seguro.preco() + custoDaCamada`
