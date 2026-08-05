# 🔎 Observer vs Strategy

| Critério         | **Observer**                                 | **Strategy**                                      |
| ---------------- | -------------------------------------------- | ------------------------------------------------- |
| Tipo             | Comportamental                               | Comportamental                                    |
| Objetivo         | Notificar múltiplos objetos sobre mudanças   | Permitir trocar um algoritmo em tempo de execução |
| Foco             | Comunicação entre objetos                    | Variação de comportamento                         |
| Relação          | Um-para-muitos                               | Um-para-um                                        |
| Acoplamento      | Baixo entre sujeito e observadores           | Baixo entre contexto e estratégia                 |
| Mudança dinâmica | Observadores podem ser adicionados/removidos | Estratégia pode ser trocada                       |

---

# 📌 1️⃣ Padrão Observer

### 🎯 Intenção

Definir dependência **1:N** para notificação automática.

### 📦 Estrutura

* Subject (Observável)
* Observer (Observador)
* ConcreteSubject
* ConcreteObserver

### 💡 Quando usar?

Quando vários objetos precisam reagir à mudança de outro.

### 🧠 Exemplo clássico

Sistema de eventos do Spring Framework.

---

# 📌 2️⃣ Padrão Strategy

### 🎯 Intenção

Permitir selecionar um algoritmo em tempo de execução.

### 📦 Estrutura

* Strategy (interface)
* ConcreteStrategyA
* ConcreteStrategyB
* Context (usa a estratégia)

### 💡 Quando usar?

Quando existem **múltiplas formas de executar uma mesma tarefa**.

---

## 💻 Exemplo de Strategy (Java)

### Interface Strategy

```java
interface DescontoStrategy {
    double calcular(double valor);
}
```

### Estratégias concretas

```java
class DescontoNatal implements DescontoStrategy {
    public double calcular(double valor) {
        return valor * 0.8;
    }
}

class DescontoBlackFriday implements DescontoStrategy {
    public double calcular(double valor) {
        return valor * 0.5;
    }
}
```

### Contexto

```java
class Carrinho {
    private DescontoStrategy estrategia;

    public void setEstrategia(DescontoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularTotal(double valor) {
        return estrategia.calcular(valor);
    }
}
```

---

# 🧠 Diferença Conceitual Principal

## 🔔 Observer responde à pergunta:

> "Quem precisa ser avisado quando algo mudar?"

## 🔄 Strategy responde à pergunta:

> "Qual algoritmo eu quero usar agora?"

---

# 📊 Visualização Mental

### Observer

```
Sujeito → Observador1
        → Observador2
        → Observador3
```

### Strategy

```
Contexto → Estratégia A
Contexto → Estratégia B
Contexto → Estratégia C
(troca em tempo de execução)
```

---

# 🎓 Exemplo aplicado a um Sistema Acadêmico

## 🟢 Observer

Quando a situação do aluno muda:

* Financeiro é notificado
* Biblioteca é notificada
* Portal é atualizado

## 🔵 Strategy

Para calcular média:

* Média simples
* Média ponderada
* Média com exame

O sistema escolhe a estratégia de cálculo.

---

# 🚨 Erro comum

Muitos alunos confundem:

* ❌ "Trocar comportamento" com "Notificar objetos"
* ❌ Acham que Observer é para variar algoritmo (não é)

---

# 🎯 Resumo Final

| Se o problema é…         | Use      |
| ------------------------ | -------- |
| Notificação automática   | Observer |
| Trocar algoritmo         | Strategy |
| Sistema de eventos       | Observer |
| Várias formas de cálculo | Strategy |



