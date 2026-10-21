

# 🧱 O Padrão Adapter: O Exemplo do Simulador de Patos

Baseado no livro "Use a Cabeça! Padrões de Projetos"
O simulador de patos foi inteiramente construído esperando objetos do tipo Duck (Pato). De repente, surge a necessidade de introduzir um Turkey (Peru) no sistema. Como as interfaces de comportamento de ambos são incompatíveis, usa-se o padrão Adapter para criar uma "ponte" entre eles.

## 🦆 1. O Ecossistema Original (O Pato)

O simulador possui uma interface Duck e uma classe concreta de um pato real (MallardDuck):

```java
// Interface esperada pelo sistema (Target)
public interface Duck {
    public void quack(); // Patos grasnam
    public void fly();   // Patos voam longe
}

// Classe que o sistema já usa normalmente
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack! Quack!");
    }

    @Override
    public void fly() {
        System.out.println("Estou voando uma longa distância!");
    }
```
Use o código com cuidado.

## 🦃 2. A Classe Incompatível (O Peru)

Você precisa adicionar Perus ao simulador. No entanto, a interface de um peru é ligeiramente diferente: eles não grasnam (eles fazem guglar / gobble) e eles não voam distâncias longas (apenas curtas):

```java
// Interface do objeto que queremos integrar (Adaptee)
public interface Turkey {
    public void gobble();   // Perus fazem gulu-gulu
    public void flyShort(); // Perus voam distâncias curtas
}

// Classe concreta do Peru
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble! (Gulu-gulu)");
    }

    @Override
    public void flyShort() {
        System.out.println("Estou voando uma distância bem curtinha!");
    }
}
```
Use o código com cuidado.
O problema: O simulador só aceita quem assina o contrato Duck. Passar um WildTurkey direto causará um erro de compilação.

## 🔀 3. A Solução: O Adaptador (TurkeyAdapter)

Para resolver o problema sem alterar o simulador e nem reescrever a classe do peru, cria-se o TurkeyAdapter. Ele se disfarça de Pato (implementa Duck), mas guarda uma instância de Peru lá dentro:

```java
// O Adaptador implementa a interface que o cliente espera
public class TurkeyAdapter implements Duck {
    private Turkey turkey;
 
    // Recebemos o Peru (objeto incompatível) no construtor
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }
 
    // Quando o simulador pedir para grasnar, traduzimos para o som do peru
    @Override
    public void quack() {
        turkey.gobble();
    }
 
    // Como perus voam distâncias curtas, fazemos ele voar 5 vezes
    // para tentar simular o voo longo de um pato!
    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.flyShort();
        }
    }
}
Use o código com cuidado.
```

## 🎮 4. O Simulador Rodando (Client)

No método principal, o sistema interage com o peru adaptado exatamente da mesma forma que interage com qualquer pato:

```java
public class SimuladorDePatos {
    public static void main(String[] args) {
        MallardDuck pato = new MallardDuck();
        WildTurkey peru = new WildTurkey();
        
        // Envelopamos o peru dentro do adaptador de patos
        Duck peruDisfarcadoDePato = new TurkeyAdapter(peru);
 
        System.out.println("--- O Peru agindo como Peru ---");
        peru.gobble();
        peru.flyShort();
 
        System.out.println("\n--- O Pato agindo como Pato ---");
        testarPato(pato);
 
        System.out.println("\n--- O Peru fingindo ser um Pato (Adapter) ---");
        // O simulador aceita o objeto perfeitamente!
        testarPato(peruDisfarcadoDePato); 
    }
 
    // Método do simulador que SÓ aceita e entende a interface Duck
    private static void testarPato(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
```
Use o código com cuidado.

## 📊 Estrutura de Mapeamento do Padrão neste Exemplo

• Client (Cliente): Método testarPato(Duck duck) do simulador.

• Target (Alvo): Interface Duck.

• Adapter (Adaptador): Classe TurkeyAdapter.

• Adaptee (Adaptado): Interface Turkey / Classe WildTurkey.
