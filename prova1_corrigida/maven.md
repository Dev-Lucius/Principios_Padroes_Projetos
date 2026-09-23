# Guia Completo — Criando Projetos Maven com Java

> Guia prático para criar, configurar, compilar, executar e organizar projetos **Java + Maven** corretamente.

---

## 1. O que é Maven?

O **Maven** é uma ferramenta de gerenciamento e automação de projetos Java.

Ele pode:

* Compilar código Java;
* Executar testes;
* Gerenciar dependências;
* Criar arquivos `.jar`;
* Organizar o projeto;
* Executar aplicações;
* Gerenciar plugins;
* Padronizar a estrutura do projeto;
* Automatizar o processo de build.

Em vez de configurar manualmente:

```text
javac
classpath
bibliotecas
diretórios
.jar
```

o Maven organiza isso para você.

---

# 2. Pré-requisitos

Antes de criar um projeto Maven, verifique:

### Java

```bash
java -version
```

Exemplo:

```text
openjdk version "21.0.x"
```

Verifique também o compilador:

```bash
javac -version
```

### Maven

```bash
mvn -version
```

Exemplo:

```text
Apache Maven 3.x.x
Java version: 21
```

### Importante

O Java utilizado pelo Maven deve ser compatível com o Java que você pretende utilizar no projeto.

Confira:

```bash
which java
which javac
which mvn
```

E:

```bash
echo $JAVA_HOME
```

Se necessário:

```bash
export JAVA_HOME=/caminho/para/jdk-21
```

---

# 3. Estrutura padrão de um projeto Maven

A estrutura recomendada é:

```text
meu-projeto/
├── pom.xml
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── pacote/
│   │   │       └── Main.java
│   │   │
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       │   └── pacote/
│       │       └── MainTest.java
│       │
│       └── resources/
│
└── target/
```

### Significado

| Diretório            | Função                      |
| -------------------- | --------------------------- |
| `src/main/java`      | Código principal            |
| `src/main/resources` | Recursos da aplicação       |
| `src/test/java`      | Testes                      |
| `src/test/resources` | Recursos dos testes         |
| `target`             | Arquivos gerados pelo Maven |
| `pom.xml`            | Configuração do projeto     |

### Regra importante

Não coloque suas classes diretamente em:

```text
src/
```

Use:

```text
src/main/java/
```

---

# 4. Criando um projeto Maven manualmente

Para projetos pequenos, especialmente exercícios de faculdade, criar a estrutura manualmente é perfeitamente aceitável.

Exemplo:

```bash
mkdir meu-projeto
cd meu-projeto

mkdir -p src/main/java
mkdir -p src/main/resources
mkdir -p src/test/java
mkdir -p src/test/resources
```

Depois:

```bash
touch pom.xml
```

A estrutura:

```text
meu-projeto/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
        ├── java/
        └── resources/
```

---

# 5. Criando o primeiro `pom.xml`

Um POM básico para Java 21:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>br.edu.exemplo</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>21</maven.compiler.release>
    </properties>

</project>
```

Esse é o ponto de partida recomendado.

---

# 6. Entendendo o `pom.xml`

## `modelVersion`

```xml
<modelVersion>4.0.0</modelVersion>
```

Define a versão do modelo de POM utilizado pelo Maven.

Normalmente será:

```text
4.0.0
```

---

# 7. `groupId`

Exemplo:

```xml
<groupId>br.edu.exemplo</groupId>
```

Identifica o grupo/organização responsável pelo projeto.

Uma convenção muito utilizada é usar o domínio invertido:

```text
com.empresa
br.edu.faculdade
br.com.exemplo
```

Para projetos acadêmicos:

```xml
<groupId>br.edu.anhanguera</groupId>
```

ou:

```xml
<groupId>br.edu.ifrs</groupId>
```

---

# 8. `artifactId`

Exemplo:

```xml
<artifactId>sistema-requerimentos</artifactId>
```

É o nome do projeto.

Prefira:

```text
sistema-requerimentos
```

em vez de:

```text
SistemaRequerimentos
```

ou:

```text
sistema requerimentos
```

---

# 9. `version`

Exemplo:

```xml
<version>1.0-SNAPSHOT</version>
```

Indica a versão do projeto.

Durante o desenvolvimento:

```text
1.0-SNAPSHOT
```

Uma versão final poderia ser:

```text
1.0.0
```

---

# 10. Configurando Java 21

Para Java 21:

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.release>21</maven.compiler.release>
</properties>
```

A propriedade:

```xml
<maven.compiler.release>21</maven.compiler.release>
```

informa ao compilador que o projeto deve utilizar Java 21.

---

# 11. Por que especificar a versão do Compiler Plugin?

Um erro comum é deixar o Maven escolher uma versão muito antiga do plugin.

Por exemplo:

```text
maven-compiler-plugin:3.1
```

pode acabar tentando compilar utilizando:

```text
source 5
target 5
```

Com JDK moderno isso pode gerar:

```text
Source option 5 is no longer supported.
Target option 5 is no longer supported.
```

Por isso, é recomendável declarar explicitamente uma versão moderna:

```xml
<build>

    <plugins>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.13.0</version>
        </plugin>

    </plugins>

</build>
```

Com isso:

```text
Java 21
    ↓
Maven
    ↓
Compiler Plugin moderno
    ↓
Código Java
```

---

# 12. POM recomendado para Java 21

Para projetos simples:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>br.edu.exemplo</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>21</maven.compiler.release>
    </properties>

    <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
            </plugin>

        </plugins>
    </build>

</project>
```

---

# 13. Criando a classe principal

Crie:

```text
src/main/java/br/edu/exemplo/Main.java
```

Código:

```java
package br.edu.exemplo;

public class Main {

    public static void main(String[] args) {
        System.out.println("Olá, Maven!");
    }
}
```

Observe que o pacote:

```java
package br.edu.exemplo;
```

corresponde à estrutura:

```text
br/
└── edu/
    └── exemplo/
        └── Main.java
```

Portanto:

```text
src/main/java/br/edu/exemplo/Main.java
```

---

# 14. Regra fundamental: package e diretório

Se você possui:

```java
package design_patters;
```

a classe deve estar em:

```text
src/main/java/design_patters/
```

Por exemplo:

```text
src/main/java/design_patters/Prova_questao1.java
```

E:

```java
package design_patters;

public class Prova_questao1 {

    public static void main(String[] args) {
        System.out.println("Funcionando!");
    }
}
```

### Regra

```text
package design_patters;
        ↓
src/main/java/design_patters/
```

```text
package br.edu.exemplo;
        ↓
src/main/java/br/edu/exemplo/
```

---

# 15. Compilando o projeto

Dentro da pasta que contém o `pom.xml`:

```bash
mvn compile
```

O Maven:

1. Lê o `pom.xml`;
2. Verifica dependências;
3. Baixa plugins necessários;
4. Compila o código;
5. Coloca os `.class` em:

```text
target/classes/
```

---

# 16. Limpando o projeto

Use:

```bash
mvn clean
```

Isso remove:

```text
target/
```

Por exemplo:

```text
target/
├── classes/
├── test-classes/
└── ...
```

será removido.

---

# 17. O comando mais utilizado durante desenvolvimento

```bash
mvn clean compile
```

Isso faz:

```text
clean
  ↓
remove target
  ↓
compile
  ↓
compila novamente
```

É uma ótima forma de verificar se o projeto está realmente compilando do zero.

---

# 18. Forçando atualização

Quando o Maven estiver reclamando de dependências ou plugins que falharam anteriormente:

```bash
mvn clean compile -U
```

O:

```text
-U
```

significa:

```text
Update
```

e força o Maven a verificar novamente os repositórios.

---

# 19. Executando a aplicação

O Maven, por padrão, não possui um comando simples equivalente a:

```bash
java Main
```

Para isso podemos utilizar o `exec-maven-plugin`.

Adicione:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.5.0</version>

    <configuration>
        <mainClass>br.edu.exemplo.Main</mainClass>
    </configuration>
</plugin>
```

Exemplo:

```xml
<build>
    <plugins>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.13.0</version>
        </plugin>

        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>3.5.0</version>

            <configuration>
                <mainClass>br.edu.exemplo.Main</mainClass>
            </configuration>
        </plugin>

    </plugins>
</build>
```

Depois:

```bash
mvn compile
mvn exec:java
```

---

# 20. Usando uma propriedade para a Main Class

Em vez de escrever diretamente:

```xml
<mainClass>br.edu.exemplo.Main</mainClass>
```

podemos utilizar:

```xml
<exec.mainClass>br.edu.exemplo.Main</exec.mainClass>
```

Nas propriedades:

```xml
<properties>

    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

    <maven.compiler.release>21</maven.compiler.release>

    <exec.mainClass>br.edu.exemplo.Main</exec.mainClass>

</properties>
```

E:

```xml
<configuration>
    <mainClass>${exec.mainClass}</mainClass>
</configuration>
```

### Atenção

Apenas:

```xml
<exec.mainClass>br.edu.exemplo.Main</exec.mainClass>
```

**não executa nada sozinho.**

É necessário que algum plugin utilize essa propriedade.

---

# 21. Ciclo de vida do Maven

O Maven possui fases de build.

As principais são:

```text
validate
compile
test
package
verify
install
deploy
```

Uma fase posterior executa as anteriores.

Por exemplo:

```bash
mvn package
```

faz aproximadamente:

```text
validate
   ↓
compile
   ↓
test
   ↓
package
```

---

# 22. Principais comandos Maven

## Verificar projeto

```bash
mvn validate
```

## Compilar

```bash
mvn compile
```

## Testar

```bash
mvn test
```

## Gerar JAR

```bash
mvn package
```

## Instalar no repositório local

```bash
mvn install
```

## Limpar

```bash
mvn clean
```

## Limpar e compilar

```bash
mvn clean compile
```

## Limpar e gerar JAR

```bash
mvn clean package
```

## Forçar atualização

```bash
mvn clean package -U
```

---

# 23. O que é a pasta `target`?

A pasta:

```text
target/
```

é gerada automaticamente pelo Maven.

Exemplo:

```text
target/
├── classes/
├── test-classes/
├── generated-sources/
└── meu-projeto-1.0-SNAPSHOT.jar
```

Você normalmente **não deve editar nada dentro dela**.

Também não é necessário versioná-la no Git.

No `.gitignore`:

```gitignore
target/
```

---

# 24. Dependências

Uma das maiores vantagens do Maven é gerenciar bibliotecas.

Por exemplo, se você quiser utilizar JUnit:

```xml
<dependencies>

    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.11.0</version>
        <scope>test</scope>
    </dependency>

</dependencies>
```

O Maven baixa automaticamente a biblioteca e suas dependências.

---

# 25. Onde o Maven guarda as dependências?

No Linux:

```text
~/.m2/repository/
```

Por exemplo:

```text
~/.m2/repository/
└── org/
    └── junit/
        └── jupiter/
```

Esse é o **repositório local do Maven**.

---

# 26. Maven Central

Quando você adiciona uma dependência, normalmente o Maven procura no Maven Central.

Fluxo:

```text
pom.xml
   ↓
Maven
   ↓
Maven Central
   ↓
Download da dependência
   ↓
~/.m2/repository
   ↓
Projeto
```

---

# 27. Problemas de dependências

Se aparecer:

```text
Could not resolve dependencies
```

verifique:

### Internet

```bash
curl -I https://repo.maven.apache.org/maven2/
```

### Forçar atualização

```bash
mvn clean compile -U
```

### Limpar uma dependência problemática

Exemplo:

```bash
rm -rf ~/.m2/repository/org/exemplo
```

Depois:

```bash
mvn clean compile -U
```

Não é necessário apagar todo:

```text
~/.m2
```

sem motivo.

---

# 28. Testes

Estrutura:

```text
src/test/java/
```

Exemplo:

```text
src/
├── main/
│   └── java/
│       └── br/
│           └── edu/
│               └── exemplo/
│                   └── Calculadora.java
│
└── test/
    └── java/
        └── br/
            └── edu/
                └── exemplo/
                    └── CalculadoraTest.java
```

Execute:

```bash
mvn test
```

---

# 29. Gerando um JAR

Execute:

```bash
mvn package
```

O Maven pode gerar:

```text
target/meu-projeto-1.0-SNAPSHOT.jar
```

Porém, isso **não significa automaticamente que o JAR é executável**.

Para um JAR executável, normalmente precisamos configurar o `maven-jar-plugin` ou outro plugin apropriado para definir a classe principal.

---

# 30. Padrão recomendado para projetos acadêmicos

Para exercícios de Padrões de Projeto, uma estrutura simples é suficiente:

```text
prova_questao1/
├── pom.xml
│
├── src/
│   └── main/
│       └── java/
│           └── design_patters/
│               ├── Main.java
│               ├── Aluno.java
│               ├── Sistema.java
│               ├── Observer.java
│               └── ...
│
└── target/
```

Se houver vários exercícios:

```text
prova/
├── pom.xml
│
└── src/
    └── main/
        └── java/
            └── design_patters/
                ├── questao1/
                ├── questao2/
                ├── questao3/
                └── questao4/
```

---

# 31. Padrão recomendado para projetos maiores

Em projetos maiores:

```text
projeto/
├── pom.xml
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── edu/
│   │   │           └── projeto/
│   │   │
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       └── resources/
│
├── README.md
├── .gitignore
└── target/
```

---

# 32. Convenção de nomes de pacotes

Prefira:

```text
br.edu.projeto
```

```text
br.edu.projeto.service
```

```text
br.edu.projeto.repository
```

```text
br.edu.projeto.model
```

Evite:

```text
MeuProjeto
```

```text
ProjetoJava
```

Pacotes devem normalmente ser escritos em:

```text
minúsculas
```

---

# 33. Convenção de nomes de classes

Classes:

```java
Aluno
SistemaAcademico
AlunoService
AlunoRepository
```

Interfaces:

```java
Observer
Subject
Repository
```

Métodos:

```java
cadastrarAluno()
buscarAluno()
removerAluno()
```

Variáveis:

```java
nomeAluno
listaAlunos
```

---

# 34. Maven + Git

Crie um `.gitignore`:

```gitignore
target/
.idea/
.vscode/
*.class
*.log
```

Dependendo da IDE, você pode adicionar arquivos específicos dela.

### Nunca versionar normalmente:

```text
target/
```

porque o Maven consegue recriá-la.

---

# 35. Como criar um projeto do zero — receita de bolo

Para um projeto simples:

### Passo 1 — Criar pasta

```bash
mkdir meu-projeto
cd meu-projeto
```

### Passo 2 — Criar estrutura

```bash
mkdir -p src/main/java
mkdir -p src/main/resources
mkdir -p src/test/java
mkdir -p src/test/resources
```

### Passo 3 — Criar POM

```bash
nano pom.xml
```

Coloque:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>br.edu.exemplo</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>21</maven.compiler.release>
    </properties>

    <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
            </plugin>

        </plugins>
    </build>

</project>
```

### Passo 4 — Criar pacote

```bash
mkdir -p src/main/java/br/edu/exemplo
```

### Passo 5 — Criar classe

```bash
nano src/main/java/br/edu/exemplo/Main.java
```

```java
package br.edu.exemplo;

public class Main {

    public static void main(String[] args) {
        System.out.println("Projeto funcionando!");
    }
}
```

### Passo 6 — Compilar

```bash
mvn clean compile
```

### Passo 7 — Verificar

Se aparecer:

```text
BUILD SUCCESS
```

o projeto compilou corretamente.

---

# 36. Diagnóstico rápido de erros

## Erro:

```text
Source option 5 is no longer supported
```

### Provável causa:

Compiler Plugin antigo.

### Solução:

Adicionar:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.13.0</version>
</plugin>
```

---

## Erro:

```text
Could not resolve dependencies
```

### Verificar:

```bash
mvn clean compile -U
```

Depois:

```bash
curl -I https://repo.maven.apache.org/maven2/
```

---

## Erro:

```text
Could not find or load main class
```

Verifique:

```text
package
```

e:

```text
estrutura dos diretórios
```

Por exemplo:

```java
package br.edu.exemplo;
```

precisa corresponder a:

```text
src/main/java/br/edu/exemplo/
```

---

## Erro:

```text
The goal you specified requires a project to execute
```

Provavelmente você executou:

```bash
mvn compile
```

fora da pasta que contém:

```text
pom.xml
```

Verifique:

```bash
ls
```

Deve aparecer:

```text
pom.xml
```

---

# 37. Comandos essenciais para decorar

Se você está começando com Maven, memorize estes:

```bash
mvn clean
```

```bash
mvn compile
```

```bash
mvn test
```

```bash
mvn package
```

```bash
mvn clean compile
```

```bash
mvn clean package
```

```bash
mvn clean compile -U
```

E, se configurado o `exec-maven-plugin`:

```bash
mvn exec:java
```

---

# 38. Checklist antes de dizer "meu Maven está com problema"

Quando algo der errado:

### 1. Estou na pasta correta?

```bash
ls
```

Deve existir:

```text
pom.xml
```

### 2. Java está funcionando?

```bash
java -version
```

### 3. Maven está funcionando?

```bash
mvn -version
```

### 4. O `pom.xml` está correto?

```bash
mvn validate
```

### 5. Estou usando a estrutura correta?

```text
src/main/java
```

### 6. O `package` corresponde ao diretório?

Exemplo:

```java
package br.edu.projeto;
```

deve estar em:

```text
src/main/java/br/edu/projeto/
```

### 7. O Compiler Plugin é moderno?

Procure:

```xml
<maven-compiler-plugin>
```

### 8. Há problema de download?

```bash
curl -I https://repo.maven.apache.org/maven2/
```

### 9. O Maven possui um erro armazenado em cache?

Tente:

```bash
mvn clean compile -U
```

---

# 39. Template definitivo

Para a maioria dos seus projetos Java 21:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>br.edu.projeto</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>21</maven.compiler.release>

        <exec.mainClass>br.edu.projeto.Main</exec.mainClass>
    </properties>

    <dependencies>

        <!-- Dependências do projeto entram aqui -->

    </dependencies>

    <build>

        <plugins>

            <!-- Compilador Java -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
            </plugin>

            <!-- Execução da aplicação -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.5.0</version>

                <configuration>
                    <mainClass>${exec.mainClass}</mainClass>
                </configuration>
            </plugin>

        </plugins>

    </build>

</project>
```

---

# 40. Fluxo mental para trabalhar com Maven

Sempre pense:

```text
                 pom.xml
                    │
                    ▼
                 Maven
                    │
          ┌─────────┴─────────┐
          ▼                   ▼
     Dependências          Plugins
          │                   │
          └─────────┬─────────┘
                    ▼
              Código Java
                    │
                    ▼
              src/main/java
                    │
                    ▼
                 compile
                    │
                    ▼
                 target/
                    │
              ┌─────┴─────┐
              ▼           ▼
            .class       .jar
```

---

# 41. A "receita de bolo" definitiva

Quando receber um exercício novo em Java:

```bash
mkdir exercicio
cd exercicio
```

Crie:

```text
pom.xml
src/main/java/
```

Configure:

```xml
<maven.compiler.release>21</maven.compiler.release>
```

e:

```xml
<artifactId>maven-compiler-plugin</artifactId>
<version>3.13.0</version>
```

Crie:

```text
src/main/java/pacote/MinhaClasse.java
```

Garanta:

```java
package pacote;
```

Compile:

```bash
mvn clean compile
```

Se aparecer:

```text
BUILD SUCCESS
```

está tudo certo.

Se precisar executar:

```bash
mvn exec:java
```

Se houver problema com dependências:

```bash
mvn clean compile -U
```

---

# 42. Resumo para consulta rápida

| Tarefa                        | Comando                |
| ----------------------------- | ---------------------- |
| Verificar Java                | `java -version`        |
| Verificar Maven               | `mvn -version`         |
| Validar POM                   | `mvn validate`         |
| Compilar                      | `mvn compile`          |
| Limpar                        | `mvn clean`            |
| Limpar + compilar             | `mvn clean compile`    |
| Testar                        | `mvn test`             |
| Gerar JAR                     | `mvn package`          |
| Instalar localmente           | `mvn install`          |
| Forçar atualização            | `mvn -U`               |
| Limpar + compilar + atualizar | `mvn clean compile -U` |
| Executar                      | `mvn exec:java`        |

---

## Regra de ouro

> **Se você está usando Java 21, configure explicitamente o Java 21 no Maven e evite depender de versões antigas de plugins.**

A estrutura mais importante para memorizar é:

```text
projeto/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
        ├── java/
        └── resources/
```

E o fluxo:

```text
Criar projeto
      ↓
Configurar pom.xml
      ↓
src/main/java
      ↓
package = diretório
      ↓
mvn clean compile
      ↓
BUILD SUCCESS
      ↓
mvn exec:java
```
