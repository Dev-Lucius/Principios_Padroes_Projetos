# Sistema de Exportação

## Vamos Observar o Enunciado

Todos os exportadores seguem este Fluxo:

```text
1. Validar
2. Buscar
3. Registrar log
4. Transformar
5. Gerar arquivo
6. Finalizar
```

Existem:

```text
CSV
JSON
XML
```

Primeira impressão:

> "Ah, são três maneiras de exportar. Deve ser **Strategy**."

Mas, analise cuidadosamente.

Existe algo **mais importante**:

> **O Fluxo é Sempre Fluxo**

A sequência não muda.

```
VALIDAR
   ↓
BUSCAR
   ↓
LOG
   ↓
TRANSFORMAR
   ↓
GERAR
   ↓
FINALIZAR
```

O que muda são algumas etapas.

Portanto, aqui temos um **Template Method**

## Strategy vs. Template Method

Imagine **Strategy**

```text
Exportador
    │
    ├── CSV
    ├── JSON
    └── XML
```

Você estaria trocando o **comportamento completo**.

Mas o enunciado deixa claro:

```text
1 → 2 → 3 → 4 → 5 → 6
```

Então precisamos manter esse processo centralizado.

A solução é:

```text
Exportador
    │
    └── exportar()
           │
           ├── validar()
           ├── buscar()
           ├── registrarLog()
           ├── transformar()
           ├── gerarArquivo()
           └── finalizar()
```

# Implementação

## Classe Abstrata

```java
public abstract class Exportador {

    // Este Método é o nosso TemplateMethod
    // Basicamente, é ele quem vai controlar o Algoritmo
    public final void exportar() {

        validarDados();
        buscarDados();
        registrarLog();
        transformarDados();
        gerarArquivo();
        finalizarExportacao();
    }

    protected abstract void validarDados();

    protected abstract void buscarDados();

    protected void registrarLog() {
        System.out.println("Registrando log...");
    }

    protected abstract void transformarDados();

    protected abstract void gerarArquivo();

    protected void finalizarExportacao() {
        System.out.println("Exportação finalizada.");
    }
}
```

## Classe CSV

```java
public class ExportadorCSV extends Exportador {

    @Override
    protected void validarDados() {
        System.out.println("Validando dados para CSV...");
    }

    @Override
    protected void buscarDados() {
        System.out.println("Buscando clientes...");
    }

    @Override
    protected void transformarDados() {
        System.out.println("Transformando dados para CSV...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando arquivo CSV...");
    }
}
```

## Classe JSON

```java
public class ExportadorJSON extends Exportador {

    @Override
    protected void validarDados() {
        System.out.println("Validando dados para JSON...");
    }

    @Override
    protected void buscarDados() {
        System.out.println("Buscando clientes...");
    }

    @Override
    protected void transformarDados() {
        System.out.println("Transformando dados para JSON...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando arquivo JSON...");
    }
}
```

## XMl

```java
public class ExportadorXML extends Exportador {

    @Override
    protected void validarDados() {
        System.out.println("Validando dados para XML...");
    }

    @Override
    protected void buscarDados() {
        System.out.println("Buscando clientes...");
    }

    @Override
    protected void transformarDados() {
        System.out.println("Transformando dados para XML...");
    }

    @Override
    protected void gerarArquivo() {
        System.out.println("Gerando arquivo XML...");
    }
}
```

## Main

```java
public class Main {

    public static void main(String[] args) {

        Exportador csv = new ExportadorCSV();
        Exportador json = new ExportadorJSON();
        Exportador xml = new ExportadorXML();

        csv.exportar();

        System.out.println();

        json.exportar();

        System.out.println();

        xml.exportar();
    }
}
```

## Observações

Imagine que a empresa altere o processo:

Antes:

```text
1. Validar
2. Buscar
3. Transformar
4. Gerar
5. Finalizar
```

Depois:

```text
1. Validar
2. Buscar
3. Registrar log
4. Transformar
5. Gerar
6. Finalizar
```

Com Template Method, você altera:

```java
public final void exportar() {

    validarDados();
    buscarDados();
    registrarLog();
    transformarDados();
    gerarArquivo();
    finalizarExportacao();
}
```

**Uma única vez.**

Todas as subclasses passam automaticamente a seguir o novo fluxo.

Esse é exatamente o benefício buscado pelo exercício.