# Guia Completo e Plano de Aula: O Padrão Iterator em Java (3 Horas)

Este documento reúne o material teórico, exemplos práticos progressivos e desafios para uma aula de **3 horas** sobre o padrão de projeto e protocolo **Iterator** no ecossistema Java, cobrindo desde a arquitetura clássica até o Java 8+.

---

## ⏱️ Cronograma Sugerido da Aula

*   **00:00 - 00:30 (30 min):** **O Problema** – Acoplamento, estruturas de dados distintas e o pesadelo de refatoração.
*   **00:30 - 01:20 (50 min):** **Exemplo 1** – O Padrão GoF Clássico (Implementação manual com interfaces próprias).
*   **01:20 - 01:35 (15 min):** *Intervalo / Café.*
*   **01:35 - 02:15 (40 min):** **Exemplo 2** – O "Jeito Java" (`java.util.Iterator`, `Iterable` e o funcionamento do `for-each`).
*   **02:15 - 02:40 (25 min):** **Evolução** – Java 8+ Streams (Como o Iterator pavimentou o caminho para a programação funcional).
*   **02:40 - 03:00 (20 min):** **Laboratório Prático** – Desafio do Filtro de Assentos Ocupados.

---

## 🚀 1. A Motivação: O Problema do Acoplamento

Explique aos alunos o cenário em que uma aplicação precisa varrer diferentes coleções (um Array estático, uma lista encadeada e uma tabela hash). Sem o Iterator, o código cliente precisaria conhecer intimamente a estrutura interna de cada coleção para percorrê-la (laços `for` baseados em índices, ponteiros ou chaves). 

Se a estrutura de dados mudar no futuro, todo o código cliente quebra. **O Iterator resolve isso encapsulando a travessia.**

---

## 🛠️ 2. Exemplo 1: O Padrão GoF Clássico (Abordagem Manual)

*Objetivo didático: Fazer os alunos entenderem o padrão estrutural puro através de polimorfismo antes de usarem as ferramentas nativas do ecossistema.*

### Cenário
Uma empresa unificou dois sistemas de catálogo de canais de TV. Um armazena os canais em um **Array estático (`String[]`)**, o outro em uma estrutura diferente. O cliente precisa iterar pelas listagens sem saber qual estrutura está por trás.

```java
// 1. As interfaces do padrão GoF
interface MeuIterator {
    boolean hasNext();
    String next();
}

interface Agregador {
    MeuIterator criarIterator();
}

// 2. Agregador Concreto: Encapsula um Array estático
class CanaisEsporte implements Agregador {
    private String[] canais = {"ESPN", "Sportv", "Fox Sports"};

    @Override
    public MeuIterator criarIterator() {
        return new ArrayIterator(this.canais);
    }
}

// 3. Iterador Concreto para a estrutura de Array
class ArrayIterator implements MeuIterator {
    private String[] itens;
    private int posicao = 0;

    public ArrayIterator(String[] itens) {
        this.itens = itens;
    }

    @Override
    public boolean hasNext() {
        return posicao < itens.length && itens[posicao] != null;
    }

    @Override
    public String next() {
        return itens[posicao++];
    }
}

// 4. Código do Cliente (Completamente desacoplado)
public class MainGof {
    public static void main(String[] args) {
        Agregador esportes = new CanaisEsporte();
        MeuIterator iterator = esportes.criarIterator();

        System.out.println("--- Canais de Esporte (GoF Clássico) ---");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

---

## ☕ 3. Exemplo 2: O "Jeito Java" Nativo (`Iterable` e `Iterator`)

*Objetivo didático: Demonstrar que a linguagem Java já abstrai esse comportamento e que o açúcar sintático do laço `for-each` exige esse contrato.*

### Cenário
Uma playlist musical. Ao implementar a interface `java.lang.Iterable`, o Java permite automaticamente o uso da sintaxe de loop moderno.

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Ao implementar Iterable, a classe se integra ao ecossistema de loops do Java
class Playlist implements Iterable<String> {
    private List<String> musicas = new ArrayList<>();

    public void adicionarMusica(String nome) {
        musicas.add(nome);
    }

    @Override
    public Iterator<String> iterator() {
        return new PlaylistIterator(this.musicas);
    }
}

// Iterador interno usando a interface oficial java.util.Iterator
class PlaylistIterator implements Iterator<String> {
    private List<String> lista;
    private int indice = 0;

    public PlaylistIterator(List<String> lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return indice < lista.size();
    }

    @Override
    public String next() {
        return lista.get(indice++);
    }
}

public class MainNativo {
    public static void main(String[] args) {
        Playlist minhaPlaylist = new Playlist();
        minhaPlaylist.adicionarMusica("Bohemian Rhapsody");
        minhaPlaylist.adicionarMusica("Hotel California");
        minhaPlaylist.adicionarMusica("Stairway to Heaven");

        System.out.println("--- Tocando Playlist (Nativo com For-Each) ---");
        // O compilador Java traduz este laço em chamadas ocultas a iterator.hasNext() e iterator.next()
        for (String musica : minhaPlaylist) {
            System.out.println("Tocando: " + musica);
        }
    }
}
```

---

## ⚡ 4. Evolução: Java 8+ Streams e a Abordagem Funcional

*Objetivo didático: Conectar os conceitos tradicionais com a modernidade. Mostrar que a API de Streams substitui a necessidade de escrever loops manuais de leitura e filtros, convertendo iterações imperativas em declarações funcionais.*

### Da Iteração Imperativa para a Declarativa
Com a chegada do Java 8, a manipulação de coleções ganhou poder com os métodos padrão (como `forEach` direto na interface `Iterable`) e a API de `Stream` (que internamente utiliza conceitos avançados baseados em `Spliterator`, um iterador especializado para processamento paralelo e sequencial).

Abaixo, veja como o código cliente se transforma ao usar coleções nativas combinadas com **Streams, Expressões Lambda e Method References**:

```java
import java.util.Arrays;
import java.util.List;

public class MainStreams {
    public static void main(String[] args) {
        List<String> bandas = Arrays.asList("Pink Floyd", "Led Zeppelin", "Queen", "Deep Purple");

        System.out.println("--- Abordagem 1: Iterable.forEach (Java 8+) ---");
        // Substitui o loop for-each tradicional por uma expressão funcional limpa
        bandas.forEach(banda -> System.out.println("Banda: " + banda));

        System.out.println("
--- Abordagem 2: API de Streams com Filtro ---");
        // Transforma a coleção em um fluxo de dados (Stream), filtra e opera sobre os resultados
        bandas.stream()
            .filter(banda -> banda.startsWith("P") || banda.startsWith("Q")) // Predicado (Filtro)
            .map(String::toUpperCase)                                       // Transformação (Mapeamento)
            .forEach(System.out.println);                                  // Consumidor Final
    }
}
```

### Quadro Comparativo para Lousa

| Critério | Iterator Clássico (GoF) / Loops | Java 8+ Streams |
| :--- | :--- | :--- |
| **Estilo de Código** | Imperativo (Como fazer passo a passo) | Declarativo (O que deve ser feito) |
| **Estado** | Modifica ponteiros/índices manualmente | Estado imutável (Não altera a coleção original) |
| **Legibilidade** | Pode se tornar verboso com muitos filtros | Altamente legível e encadeável (*Fluent API*) |
| **Execução** | Sequencial e síncrona por padrão | Suporta concorrência nativa (`parallelStream()`) |

---

## 📝 5. Laboratório Prático: O Desafio do Filtro de Assentos

Forneça este esqueleto aos alunos. O objetivo é criar uma lógica que ignore assentos vazios (`null`), garantindo que o cliente final receba apenas os nomes dos passageiros de forma transparente no loop.

```java
import java.util.Iterator;

class Onibus implements Iterable<String> {
    // Array com posições vazias no meio
    private String[] assentos = {"Carlos", null, "Ana", null, "Julia", null};

    @Override
    public Iterator<String> iterator() {
        return new AssentoIterator(assentos);
    }
}

// DESAFIO: Implementar a lógica de filtragem transparente dentro do Iterator
class AssentoIterator implements Iterator<String> {
    private String[] assentos;
    private int posicao = 0;

    public AssentoIterator(String[] assentos) {
        this.assentos = assentos;
    }

    @Override
    public boolean hasNext() {
        // REGRA DE NEGÓCIO: Avança o ponteiro enquanto encontrar assentos nulos
        while (posicao < assentos.length && assentos[posicao] == null) {
            posicao++;
        }
        return posicao < assentos.length;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        return assentos[posicao++];
    }
}

public class MainDesafio {
    public static void main(String[] args) {
        Onibus onibus = new Onibus();
        
        System.out.println("--- Lista de Passageiros Abordando com Iterator Customizado ---");
        for (String passageiro : onibus) {
            System.out.println("Passageiro: " + passageiro);
        }
    }
}
```

### Solução Alternativa Express com Java 8+ Streams (Para fechamento da aula)
Mostre aos alunos que, caso os dados estivessem expostos como um fluxo, o Java 8+ resolveria o mesmo desafio com apenas duas linhas:

```java
import java.util.Objects;
import java.util.stream.Stream;

public class SolucaoModerna {
    public static void main(String[] args) {
        String[] assentos = {"Carlos", null, "Ana", null, "Julia", null};

        System.out.println("
--- Mesma Solução usando Streams (Filtro de Nulos) ---");
        Stream.of(assentos)
              .filter(Objects::nonNull)
              .forEach(System.out.println);
    }
}
```
