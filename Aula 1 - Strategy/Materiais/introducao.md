## Introdução a Padrões de Projeto em Orientação a Objetos

Padrões de projeto (*Design Patterns*) são **soluções reutilizáveis para problemas comuns** que surgem no desenvolvimento de software orientado a objetos (O.O.).

Eles **não são código pronto**, mas sim **modelos de solução** que ajudam a escrever sistemas mais organizados, reutilizáveis e fáceis de manter.

O conceito ficou muito popular com o livro:

* 📖 Design Patterns: Elements of Reusable Object-Oriented Software
  (conhecido como o livro do *Gang of Four* — GoF)

---

# Por que usar padrões?

Em projetos orientados a objetos, enfrentamos desafios como:

* Alto acoplamento entre classes
* Código difícil de manter
* Repetição de lógica
* Dificuldade de expansão

Os padrões ajudam a:

✅ Reduzir acoplamento
✅ Melhorar reutilização
✅ Organizar responsabilidades
✅ Facilitar manutenção e testes

---

# As 3 Categorias de Padrões

O GoF dividiu os padrões em três grandes grupos:

---

## 🏗️ 1. Padrões Criacionais

Relacionados à **criação de objetos**.

Eles ajudam a controlar como os objetos são instanciados.

### Exemplos:

* **Singleton** → garante apenas uma instância de uma classe.
* **Factory Method** → delega a criação para subclasses.
* **Abstract Factory** → cria famílias de objetos relacionados.
* **Builder** → constrói objetos complexos passo a passo.

---

## 🏛️ 2. Padrões Estruturais

Relacionados à **organização e composição de classes e objetos**.

### Exemplos:

* **Adapter** → permite que interfaces incompatíveis trabalhem juntas.
* **Facade** → fornece uma interface simplificada.
* **Composite** → trata objetos individuais e composições de forma uniforme.
* **Decorator** → adiciona responsabilidades dinamicamente.

---

## 3. Padrões Comportamentais

Relacionados à **comunicação e responsabilidade entre objetos**.

### Exemplos:

* **Observer** → um objeto notifica outros quando muda.
* **Strategy** → encapsula algoritmos intercambiáveis.
* **Command** → encapsula uma requisição como objeto.
* **State** → altera comportamento conforme estado interno.

---

# Exemplo Simples (Strategy)

Imagine um sistema de pagamento:

Sem padrão:

```java
if(tipo == "cartao") { ... }
if(tipo == "pix") { ... }
if(tipo == "boleto") { ... }
```

Com **Strategy**:

* Criamos uma interface `Pagamento`
* Cada forma de pagamento implementa essa interface
* O sistema usa a estratégia dinamicamente

Resultado: código mais flexível e extensível.

---

# Quando aprender padrões?

Depois que você:

* Já entende bem **classes, herança, polimorfismo e interfaces**
* Já sofreu com código difícil de manter 😅

Padrões fazem mais sentido quando você **já sentiu o problema na prática**.

---

# ⚠️ Cuidado

Não use padrões só porque existem.

> “Se tudo é padrão, nada é simples.”

Use quando houver **um problema real que o padrão resolva**.

