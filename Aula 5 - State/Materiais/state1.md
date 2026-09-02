
# 📘 1. O que é o padrão State?

O **State** permite que um objeto **mude seu comportamento quando seu estado interno muda**, como se ele mudasse de classe.

👉 Em outras palavras:

> Você tira vários `if/else` baseados em estado e transforma isso em **objetos separados**.

---

# 🧠 2. Problema clássico

Imagina um sistema de **pedido (e-commerce)**:

```java
if (estado == "NOVO") {
    // lógica A
} else if (estado == "PAGO") {
    // lógica B
} else if (estado == "ENVIADO") {
    // lógica C
}
```

### Problemas:

* Muitos `if/else`
* Código difícil de manter
* Difícil adicionar novos estados
* Violação do princípio **Open/Closed**

---

# 🧩 3. Solução com State

Você transforma cada estado em uma **classe**.

---

## 🏗 Estrutura

* `Context` → objeto principal (Pedido)
* `State` → interface
* `ConcreteState` → estados concretos

---

## 📌 Exemplo 1 – Pedido

### Interface State

```java
interface EstadoPedido {
    void processar(Pedido pedido);
}
```

---

### Estados concretos

```java
class Novo implements EstadoPedido {
    public void processar(Pedido pedido) {
        System.out.println("Pedido sendo pago...");
        pedido.setEstado(new Pago());
    }
}

class Pago implements EstadoPedido {
    public void processar(Pedido pedido) {
        System.out.println("Pedido sendo enviado...");
        pedido.setEstado(new Enviado());
    }
}

class Enviado implements EstadoPedido {
    public void processar(Pedido pedido) {
        System.out.println("Pedido já foi enviado.");
    }
}
```

---

### Contexto

```java
class Pedido {
    private EstadoPedido estado;

    public Pedido() {
        estado = new Novo();
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void processar() {
        estado.processar(this);
    }
}
```

---

### Uso

```java
Pedido p = new Pedido();

p.processar(); // Novo → Pago
p.processar(); // Pago → Enviado
p.processar(); // Enviado
```

---

# 🔥 4. Exemplo 2 – Semáforo

## Problema

```java
if (estado == "VERDE") ...
if (estado == "AMARELO") ...
if (estado == "VERMELHO") ...
```

---

## Com State

```java
interface SemaforoState {
    void proximo(Semaforo s);
}
```

```java
class Verde implements SemaforoState {
    public void proximo(Semaforo s) {
        System.out.println("Amarelo!");
        s.setEstado(new Amarelo());
    }
}

class Amarelo implements SemaforoState {
    public void proximo(Semaforo s) {
        System.out.println("Vermelho!");
        s.setEstado(new Vermelho());
    }
}

class Vermelho implements SemaforoState {
    public void proximo(Semaforo s) {
        System.out.println("Verde!");
        s.setEstado(new Verde());
    }
}
```

---

# 🎮 5. Exemplo 3 – Personagem de jogo

Estados:

* Normal
* Com poder
* Ferido

```java
interface EstadoPersonagem {
    void atacar();
}
```

```java
class Normal implements EstadoPersonagem {
    public void atacar() {
        System.out.println("Ataque normal");
    }
}

class Poderoso implements EstadoPersonagem {
    public void atacar() {
        System.out.println("Ataque forte!");
    }
}
```

---

# 🧪 6. Exemplo 4 – Máquina de venda (clássico)

Estados:

* Sem moeda
* Com moeda
* Produto entregue

👉 Esse exemplo é MUITO usado em provas.

---

# ⚖️ 7. Comparação com Strategy

| State                           | Strategy                       |
| ------------------------------- | ------------------------------ |
| Estado muda ao longo do tempo   | Estratégia é escolhida         |
| Comportamento depende do estado | Comportamento é intercambiável |
| Estado pode mudar sozinho       | Cliente escolhe estratégia     |

👉 Dica de prova:

* **State → "estado interno muda comportamento"**
* **Strategy → "trocar algoritmo"**

---

# 🚫 8. Quando NÃO usar

Evite usar State quando:

* Poucos estados simples
* Não há transições complexas
* Um simples `if` resolve

---

# ✅ 9. Quando usar

Use State quando:

* Muitos estados
* Muitas transições
* Muitos `if/else`
* Regras complexas por estado

---

# 🧠 10. Vantagens

* Elimina `if/else`
* Código mais limpo
* Fácil de expandir
* Segue Open/Closed

---

# ⚠️ 11. Desvantagens

* Mais classes
* Pode parecer complexo no início

---

# 💡 12. Dica prática (muito importante)

Se você ver código assim:

```java
if (estado == X) {
   comportamento A
}
if (estado == Y) {
   comportamento B
}
```

👉 **Isso é um forte candidato a State.**

---

# 🚀 13. Resumo final

O padrão State:

* Encapsula estados em classes
* Permite mudar comportamento dinamicamente
* Remove condicionais complexas
* Torna o código extensível

---

# 🧠 Visão geral rápida

| Padrão       | Ideia central                                |
| ------------ | -------------------------------------------- |
| **State**    | Comportamento muda conforme o estado interno |
| **Strategy** | Trocar algoritmos de forma flexível          |
| **Observer** | Notificar vários objetos quando algo muda    |

---

# 📘 1. State vs Strategy

Eles são MUITO parecidos estruturalmente — a diferença está na **intenção**.

---

## 🧩 Semelhança estrutural

Ambos usam:

* Interface
* Classes concretas
* Composição

---

## ⚖️ Diferença principal

| State                                  | Strategy                            |
| -------------------------------------- | ----------------------------------- |
| Representa **estado interno**          | Representa **estratégia escolhida** |
| O estado muda sozinho                  | O cliente escolhe                   |
| Mudança automática                     | Mudança manual                      |
| Modela comportamento ao longo do tempo | Modela variação de algoritmo        |

---

## 🔥 Exemplo comparativo

### 🎯 Strategy (frete)

```java
interface Frete {
    double calcular(double valor);
}
```

* SEDEX
* PAC
* Transportadora

👉 Quem decide? **O cliente**

---

### 🔄 State (pedido)

* Novo
* Pago
* Enviado

👉 Quem decide? **O próprio sistema (fluxo)**

---

## 💡 Regra de ouro

> 👉 Se o comportamento muda por **decisão externa → Strategy**
> 👉 Se muda por **evolução interna → State**

---

# 📡 2. State vs Observer

Aqui a diferença é bem mais clara.

---

## ⚖️ Comparação direta

| State                        | Observer                  |
| ---------------------------- | ------------------------- |
| Um objeto muda comportamento | Um objeto notifica vários |
| Foco interno                 | Foco em comunicação       |
| Encapsula estados            | Encapsula assinantes      |
| Troca lógica                 | Dispara eventos           |

---

## 🔥 Exemplo

### 🧠 State

Pedido:

* Novo → Pago → Enviado

👉 muda comportamento

---

### 📡 Observer

Quando pedido é pago:

* envia email
* atualiza estoque
* gera nota fiscal

👉 vários objetos reagem

---

## 💡 Regra de ouro

> 👉 State = **"como eu me comporto agora?"**
> 👉 Observer = **"quem precisa saber que algo aconteceu?"**

---

# 🔁 3. Strategy vs Observer

Menos comum comparar, mas cai às vezes.

---

## ⚖️ Diferença

| Strategy                 | Observer           |
| ------------------------ | ------------------ |
| Escolhe um comportamento | Notifica múltiplos |
| Um algoritmo por vez     | Vários ouvintes    |
| Foco em execução         | Foco em eventos    |

---

## 🔥 Exemplo

* Strategy → escolher algoritmo de ordenação
* Observer → avisar UI que dados mudaram

---

# 🧠 4. Comparação completa (visão prova)

| Critério        | State                 | Strategy        | Observer                   |
| --------------- | --------------------- | --------------- | -------------------------- |
| Tipo            | Comportamental        | Comportamental  | Comportamental             |
| Foco            | Estado interno        | Algoritmo       | Comunicação                |
| Troca dinâmica  | Sim                   | Sim             | Não é troca, é notificação |
| Quem decide     | Sistema               | Cliente         | Evento                     |
| Quantos objetos | 1 ativo por vez       | 1 ativo por vez | Vários ouvintes            |
| Uso típico      | Fluxos (pedido, jogo) | Cálculo, regras | Eventos, UI                |

---

# 🧪 5. Exemplo real combinando os 3 (muito importante)

Imagina um e-commerce:

### 🧠 State

* Pedido muda:

  * Novo → Pago → Enviado

---

### ⚙️ Strategy

* Forma de pagamento:

  * Cartão
  * Pix
  * Boleto

---

### 📡 Observer

* Quando paga:

  * envia email
  * atualiza estoque
  * notifica sistema financeiro

---

👉 Isso aqui é arquitetura real.

---

# 🚨 6. Erro comum (cai em prova)

Confundir:

* **State** com **Strategy**

👉 Estrutura igual, intenção diferente.

---

# 🎯 7. Resumo final (pra decorar)

* **State** → muda comportamento com o estado
* **Strategy** → troca algoritmo
* **Observer** → avisa vários objetos

---

# 💡 Dica de prova (muito forte)

Se a questão disser:

* "objeto muda comportamento automaticamente" → **State**
* "escolher entre vários algoritmos" → **Strategy**
* "notificar múltiplos objetos" → **Observer**

---


