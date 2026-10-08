# Questão 3 — Cenário C: O Acoplamento dos Painéis de Controle

## (a) Padrão: **Observer** — categoria GoF: **Comportamental**

O personagem é o *Subject* (o "fofoqueiro"): mantém uma lista de *Observers* (os painéis) e, quando seu
estado muda, percorre a lista chamando `update()` em cada um. Os painéis se cadastram com `addObserver()`.

**Baixo acoplamento:** `Personagem` depende só da interface `Observer`, nunca de `BarraDeVida`,
`ControleAudio` ou `SistemaConquistas`. A equipe de interface cria um painel novo, implementa
`Observer<Personagem>` e o cadastra, **sem alterar `Personagem`** (Aberto/Fechado). Os painéis dependem do
`Personagem` (para ler os dados), mas o inverso não ocorre.

## (b) Estrutura

- `Subject<T>`: `addObserver`, `removeObserver`, `notifyObservers`
- `Observer<T>`: `update(T)`
- `Personagem`: guarda `List<Observer<Personagem>>` e chama `notifyObservers()` dentro de `setVida`,
  `setMana` e `setNivel`, por isso o aviso é automático
