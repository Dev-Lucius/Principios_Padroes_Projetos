# Result Set


O ResultSet do JDBC funciona exatamente como um Iterator, mas com uma nomenclatura ligeiramente diferente adaptada para bancos de dados. Ele foi desenhado especificamente para lidar com volumes massivos de dados sem estourar a memória ram do seu servidor Java.
Em vez de trazer todas as linhas do banco de dados de uma vez para a aplicação, o ResultSet atua como um ponteiro (cursor) que aponta para uma linha por vez.

------------------------------

## A Equivalência Direta
Se você comparar o ResultSet com a interface Iterator tradicional do Java, a lógica é idêntica:

| Conceito | java.util.Iterator | java.sql.ResultSet | O que faz por baixo dos panos? |
|---|---|---|---|
| Avançar/Verificar | hasNext() + next() | next() | Avança o cursor para a próxima linha e retorna true se ela existir, ou false se chegou ao fim. |
| Ler Dados | Retornado pelo next() | getString(), getInt(), etc. | Extrai os valores das colunas especificamente da linha onde o cursor está parado agora. |

------------------------------
## Como ele funciona na prática (Memória e Rede)

Quando você executa uma consulta, o banco de dados processa os dados, mas o driver JDBC não baixa tudo de uma vez. Ele utiliza um mecanismo chamado Fetch Size (Tamanho da Busca).

   1. O Cursor Inicial: Quando o ResultSet é criado, o cursor fica posicionado antes da primeira linha (posição 0). Por isso, você não pode ler dados imediatamente.
   2. O Avanço (next()): Quando você chama resultSet.next(), o cursor anda para a linha 1. Se o driver JDBC precisar de dados, ele faz uma viagem rápida até o banco e traz um lote de linhas (ex: 50 linhas).
   3. Extração: Os métodos getX() lêem apenas os bytes daquela linha atual que já está na memória do driver.
   4. Descarte: Quando o next() avança para a linha 2, a linha 1 pode ser descartada ou sobrescrevida na memória interna do driver.

```java
try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
     PreparedStatement stmt = conn.prepareStatement("SELECT id, nome FROM usuarios");
     // O ResultSet nasce posicionado ANTES da primeira linha
     ResultSet rs = stmt.executeQuery()) {

    // rs.next() funciona como o hasNext() e o next() ao mesmo tempo
    while (rs.next()) {
        // O cursor está parado em uma linha específica. Lemos os dados dela:
        int id = rs.getInt("id");
        String nome = rs.getString("nome");

        System.out.println("Usuário: " + id + " - " + nome);
        
        // Assim que o loop volta para o rs.next(), os dados da linha anterior 
        // são liberados para o Garbage Collector. A memória fica limpa!
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```
------------------------------

## Por que ele não é um java.util.Iterator oficial?

O ResultSet foi criado na versão 1.1 do Java, enquanto a interface Iterator moderna surgiu no Java 1.2. Além disso, o ResultSet precisa lidar com exceções de infraestrutura (SQLException), mapeamento de tipos complexos do SQL e cursores que podem voltar para trás (em conexões bidirecionais), o que tornaria a interface Iterator simples demais para ele.
Você sabia que se você esquecer de configurar o Fetch Size em alguns bancos (como o PostgreSQL), ele pode tentar baixar milhões de linhas de uma vez e dar OutOfMemoryError mesmo usando o ResultSet? Quer que eu mostre como configurar o tamanho desse lote de paginação do iterador do banco?


Para evitar que o banco de dados envie milhões de registros de uma vez e estoure a memória ram do seu servidor Java, você precisa configurar duas propriedades cruciais no seu PreparedStatement: o FetchSize e o AutoCommit.
Se você não alterar essas configurações, muitos drivers JDBC (como o do PostgreSQL) ignoram o comportamento de "iterador" por padrão e baixam todo o resultado da query de uma vez só para a memória da aplicação.
Aqui está como configurar o iterador do banco de dados para trabalhar em lotes (fetch), integrando-o com o exemplo do Javalin:

## O Código Java Configurado

```java
import io.javalin.Javalin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    private static final String URL = "jdbc:postgresql://localhost:5432/meu_banco";
    private static final String USER = "postgres";
    private static final String PASS = "senha";

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7070);

        app.sse("/stream-usuarios", client -> {
            // Usamos try-with-resources para garantir o fechamento seguro das conexões
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                
                // 1. OBRIGATÓRIO PARA O POSTGRESQL: Desativar o AutoCommit.
                // Sem isso, ele ignora o FetchSize e traz tudo de uma vez.
                conn.setAutoCommit(false);

                String sql = "SELECT id, nome FROM usuarios_gigante";
                
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    
                    // 2. CONFIGURA O TAMANHO DO LOTE (Fetch Size)
                    // O driver JDBC trará apenas 100 linhas por vez da rede.
                    stmt.setFetchSize(100); 

                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            // Extrai os dados da linha atual do iterador
                            int id = rs.getInt("id");
                            String nome = rs.getString("nome");

                            // Envia via Web (SSE) imediatamente para o cliente
                            client.sendEvent("usuario", "ID: " + id + " - Nome: " + nome);
                        }
                    }
                }
                
                client.sendEvent("fim", "Processamento concluído!");
                client.close();

            } catch (Exception e) {
                System.out.println("⚠️ Conexão encerrada ou erro no banco: " + e.getMessage());
            }
        });
    }
}
```
## O que mudou por baixo dos panos?

* conn.setAutoCommit(false): Diz ao banco para abrir uma transação estável. No PostgreSQL e no MySQL, o mecanismo de cursor interno que permite o avanço em partes (o iterador real) só funciona se uma transação explícita estiver ativa.
* stmt.setFetchSize(100): Define o tamanho do buffer na memória do Java. O seu loop while(rs.next()) vai rodar liso, mas a cada 100 iterações, o driver JDBC secretamente vai até o servidor do banco buscar as próximas 100 linhas.
* Consumo de Memória Constante: Se sua tabela tiver 10 milhões de linhas, sua aplicação Java vai consumir exatamente o mesmo espaço de memória que consumiria para processar apenas 100 linhas.

## ⚠️ Particularidade do MySQL

Se o seu banco de dados for o MySQL, o driver dele ignora números como 100. Para ativar o modo de streaming (linha por linha) no MySQL, você deve configurar exatamente assim:

```java
stmt.setFetchSize(Integer.MIN_VALUE); // Truque específico do MySQL para habilitar streaming
```





