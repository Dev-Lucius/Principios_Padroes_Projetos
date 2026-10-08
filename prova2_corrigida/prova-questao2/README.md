# Questão 2 — Cenário B: O Fluxo Rígido, mas com Detalhes Variáveis

## (a) Padrão: **Template Method** — categoria GoF: **Comportamental**

**Como elimina a duplicação:** o esqueleto (autenticar -> capturar -> filtrar -> salvar) existe uma única
vez, no método `minerar()` da classe abstrata, que é `final`. Instagram, TikTok e X só escrevem o que
muda (autenticação e, se preciso, captura). Adicionar um passo de auditoria passa a exigir mexer em
**um** lugar, não em três classes.

**Relacionamento entre classes: HERANÇA.** A classe base controla o fluxo e chama os passos; as subclasses
os preenchem ("não nos chame, nós chamamos você"). Isso difere do Strategy, que usa composição.

## (b) Estrutura

| Passo | Tipo | Onde |
|---|---|---|
| `minerar()` | método template (`final`) | `AlgoritmoMineracao` |
| `autenticar()` | abstrato (varia por plataforma) | subclasses |
| `capturarPostsBrutos()` | hook (implementação padrão, pode ser sobrescrito) | base / `ProcessadorInstagram` |
| `filtrarOfensivos()`, `salvar()` | concretos, iguais para todos | base |

`ProcessadorTwitter` sobrescreve **apenas** `autenticar()`.
