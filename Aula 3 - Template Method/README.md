# Aula: Template Method

O **Template Method** é um padrão de projeto comportamental que define o **esqueleto de um algoritmo em uma classe base**, deixando alguns passos para serem implementados pelas subclasses. Ele faz parte dos padrões catalogados no livro Design Patterns: Elements of Reusable Object-Oriented Software, escrito pela chamada Gang of Four.

---



## 1. Motivação

Imagine que vários algoritmos seguem **quase o mesmo processo**, mas **algumas etapas mudam**.

Exemplo: correção de avaliações.

Processo geral:

1. Ler respostas
2. Corrigir prova
3. Calcular nota
4. Gerar relatório

A estrutura é sempre a mesma, mas **a forma de corrigir** pode mudar:

* prova objetiva
* prova discursiva
* trabalho prático

O **Template Method** resolve isso.

---

# 2. Ideia central

A **classe base define o algoritmo**, mas **delegando alguns passos para subclasses**.

Estrutura:

```
ClasseAbstrata
 ├── templateMethod()
 ├── passo1()
 ├── passo2() (abstrato)
 └── passo3() (abstrato)
```

O método principal chama os passos na ordem correta.

---

# 3. Exemplo em Java

### Classe abstrata

```java
abstract class CorrecaoProva {

    // template method
    public final void corrigir() {
        lerRespostas();
        corrigirQuestoes();
        calcularNota();
        gerarRelatorio();
    }

    private void lerRespostas() {
        System.out.println("Lendo respostas...");
    }

    protected abstract void corrigirQuestoes();

    protected abstract void calcularNota();

    private void gerarRelatorio() {
        System.out.println("Relatório gerado");
    }
}
```

---

### Implementação 1

```java
class ProvaObjetiva extends CorrecaoProva {

    protected void corrigirQuestoes() {
        System.out.println("Corrigindo prova objetiva");
    }

    protected void calcularNota() {
        System.out.println("Nota baseada em acertos");
    }
}
```

---

### Implementação 2

```java
class ProvaDiscursiva extends CorrecaoProva {

    protected void corrigirQuestoes() {
        System.out.println("Professor avaliando resposta");
    }

    protected void calcularNota() {
        System.out.println("Nota baseada em critérios");
    }
}
```

---

### Uso

```java
public class Main {
    public static void main(String[] args) {

        CorrecaoProva prova1 = new ProvaObjetiva();
        prova1.corrigir();

        CorrecaoProva prova2 = new ProvaDiscursiva();
        prova2.corrigir();
    }
}
```

---

# 4. Estrutura do padrão

Participantes:

* **AbstractClass**

  * define o template method
* **ConcreteClass**

  * implementa passos específicos

---

# 5. Vantagens

✔ Reutilização de código
✔ Define ordem fixa do algoritmo
✔ Permite customizar partes do processo

---

# 6. Problemas

❌ Usa **herança forte**
❌ Subclasses podem ficar rígidas
❌ Difícil mudar o algoritmo depois

---

# 7. Por que não usar Strategy?

O padrão Strategy Pattern resolve **outro tipo de problema**.

### Strategy = trocar algoritmo inteiro

Exemplo:

```
CalcularFrete
  ├── FretePAC
  ├── FreteSedex
  └── FreteTransportadora
```

Aqui **o comportamento inteiro muda**.

Já no **Template Method**:

* o **fluxo do algoritmo é fixo**
* apenas **alguns passos mudam**

Comparação:

| Situação                   | Melhor padrão   |
| -------------------------- | --------------- |
| Algoritmo com passos fixos | Template Method |
| Algoritmos intercambiáveis | Strategy        |

---

# 8. Por que não usar Observer?

O padrão Observer Pattern resolve **problema de notificação entre objetos**.

Exemplo:

* sistema de estoque
* mudança de preço
* notificar clientes

```
Produto → notifica → Observers
```

Ele é usado quando:

* um objeto muda estado
* vários objetos precisam reagir

Ou seja, é **totalmente diferente** do problema que o Template Method resolve.

---

# 9. Comparação geral

| Padrão          | Problema que resolve                  |
| --------------- | ------------------------------------- |
| Template Method | Estrutura fixa de algoritmo           |
| Strategy        | Trocar algoritmo em tempo de execução |
| Observer        | Notificar mudanças de estado          |

---

# 10. Exemplo real

Frameworks usam muito Template Method.

Por exemplo:

* frameworks web
* frameworks de testes
* frameworks de jogos

Muitos métodos `run()` ou `execute()` são **template methods**.

---

✅ **Resumo**

Use **Template Method** quando:

* existe **um fluxo fixo**
* algumas **etapas variam**
* quer evitar duplicação de código.

---

