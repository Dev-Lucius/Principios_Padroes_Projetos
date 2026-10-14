# 📑 Guia Completo: Padrão de Projeto Proxy em Java

O Proxy é um padrão de projeto estrutural que fornece um objeto substituto ou intermediário (o Proxy) para controlar, gerenciar ou proteger o acesso a outro objeto (o RealSubject).

Ele atua como uma camada de indireção entre o cliente e o serviço real. O cliente acredita que está conversando diretamente com a classe de negócio principal, mas o Proxy intercepta a chamada para injetar responsabilidades transversais (segurança, rede, desempenho, otimização).


## 🏗️ Estrutura Base das Interfaces de Teste

Para garantir que todos os exemplos sejam modulares e independentes, definimos interfaces específicas de contrato para cada cenário.

### 1. Proxy Virtual (Virtual Proxy)

* Fundamentação Teórica: Gerencia o ciclo de vida de objetos cujo custo de criação ou alocação em memória é muito elevado. Ele atua como um placeholder leve. O objeto real só é instanciado usando Lazy Loading (inicialização tardia) no exato instante em que o cliente invoca o método de execução.

* Objetivo: Otimizar o consumo de memória e acelerar o tempo de inicialização inicial da aplicação.

```java
// Contrato
interface Relatorio {
    void gerar();
}

// Objeto Real (Pesado)
class RelatorioReal implements Relatorio {
    private String nomeArquivo;

    public RelatorioReal(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        carregarDadosDoBanco();
    }

    private void carregarDadosDoBanco() {
        System.out.println("[BD] Pesquisando milhões de linhas no banco para: " + nomeArquivo + "... (Operação Lenta)");
    }

    @Override
    public void gerar() {
        System.out.println("[Sucesso] Exibindo PDF do Relatório: " + nomeArquivo);
    }
}

// Proxy Virtual
class RelatorioProxy implements Relatorio {
    private RelatorioReal relatorioReal;
    private String nomeArquivo;

    public RelatorioProxy(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void gerar() {
        if (relatorioReal == null) {
            relatorioReal = new RelatorioReal(nomeArquivo); // Instancia apenas sob demanda
        }
        relatorioReal.gerar();
    }
}
```

### 2. Proxy de Proteção (Protection Proxy)

* Fundamentação Teórica: Controla os direitos de acesso e privilégios ao objeto real agindo como um Guard Gate (Barreira de Isolamento). O Proxy valida credenciais, papéis de usuário (Roles) ou assinaturas digitais antes de decidir se repassa a requisição ou lança uma exceção de segurança.

* Objetivo: Isolar as regras de segurança e governança de dados da lógica de negócio principal.

```java
// Contrato
interface PastaCompartilhada {
    void acessar();
}

// Objeto Real
class PastaReal implements PastaCompartilhada {
    @Override
    public void acessar() {
        System.out.println("[Seguro] Acessando arquivos confidenciais da diretoria corporativa.");
    }
}

// Proxy de Proteção
class PastaProxy implements PastaCompartilhada {
    private final PastaReal pastaReal = new PastaReal();
    private String cargoUsuario;

    public PastaProxy(String cargoUsuario) {
        this.cargoUsuario = cargoUsuario;
    }

    @Override
    public void acessar() {
        if ("DIRETOR".equalsIgnoreCase(cargoUsuario)) {
            pastaReal.acessar();
        } else {
            System.out.println("[BLOQUEADO] Acesso Negado: O cargo '" + cargoUsuario + "' não possui permissão.");
        }
    }
}
```

### 3. Proxy Inteligente (Smart Proxy)

* Fundamentação Teórica: Executa lógicas operacionais adicionais ("efeitos colaterais seguros") sempre que o objeto real é acessado. É o pilar da intercepção, usado para auditoria, contagem de referências ativas para gerenciamento de memória ou sincronização de acessos concorrentes (thread-safe locking).

* Objetivo: Adicionar infraestrutura, métricas e observabilidade de forma totalmente transparente para o cliente.


```java
// Contrato
interface ConexaoInternet {
    void conectar(String url);
}

// Objeto Real
class ConexaoReal implements ConexaoInternet {
    @Override
    public void conectar(String url) {
        System.out.println("[Rede] Navegando em: " + url);
    }
}

// Proxy Inteligente
class ConexaoProxyInteligente implements ConexaoInternet {
    private final ConexaoReal conexaoReal = new ConexaoReal();
    private int contadorAcessos = 0;

    @Override
    public void conectar(String url) {
        contadorAcessos++; // Lógica inteligente de métricas
        System.out.println("[AUDITORIA] Log gravado. Acessando: " + url + " | Requisições totais: " + contadorAcessos);
        conexaoReal.conectar(url);
    }
}
```

### 4. Proxy de Cache (Caching Proxy)

* Fundamentação Teórica: Especialização do Proxy Inteligente focada no padrão arquitetural de Memoization. Ele intercepta chamadas de métodos, gerencia o ciclo de vida de dados voláteis e armazena os pares de entrada/saída em uma estrutura de dados rápida (como um Map). Se o mesmo input for solicitado, ele pula a execução do serviço real.

* Objetivo: Reduzir a latência do sistema, economizar processamento de CPU e evitar sobrecarga em APIs externas ou banco de dados.

```java
import java.util.HashMap;
import java.util.Map;

// Contrato
interface ServicoDados {
    String buscarDados(String chave);
}

// Objeto Real (Operação cara)
class ServicoDadosReal implements ServicoDados {
    @Override
    public String buscarDados(String chave) {
        return "Resultado complexo associado a chave [" + chave + "]";
    }
}

// Proxy de Cache
class ServicoDadosCacheProxy implements ServicoDados {
    private final ServicoDados servicoReal = new ServicoDadosReal();
    private final Map<String, String> cache = new HashMap<>();

    @Override
    public String buscarDados(String chave) {
        if (cache.containsKey(chave)) {
            System.out.println("[CACHE HIT] Retornando dados direto da memória para: " + chave);
            return cache.get(chave);
        }

        System.out.println("[CACHE MISS] Executando consulta pesada no serviço real para: " + chave);
        String resultado = servicoReal.buscarDados(chave);
        cache.put(chave, resultado);
        return resultado;
    }
}
```

#### 5. Proxy Remoto (Remote Proxy)

* Fundamentação Teórica: Fornece transparência de localização ocultando o fato de que um objeto pertence a outro servidor ou processo físico. Ele atua como um Stub local, empacotando os parâmetros em chamadas de rede através de serialização/marshaling (REST, gRPC, RPC, RMI) e desempacotando as respostas da rede para o cliente.

* Objetivo: Permitir que sistemas distribuídos ou microserviços sejam programados como se fossem locais.

```java
// Contrato
interface ServicoPagamento {
    void processar(double valor);
}

// Proxy Remoto (Simulador de integração distribuída HTTP/gRPC)
class ServicoPagamentoProxyRemoto implements ServicoPagamento {
    private final String endpointRemoto = "https://gateway.com";

    @Override
    public void processar(double valor) {
        System.out.println("[HTTP CLIENT] Serializando payload: { amount: " + valor + " }");
        System.out.println("[HTTP CLIENT] Enviando POST para o servidor remoto: " + endpointRemoto);
        System.out.println("[HTTP CLIENT] Status 200 OK recebido da rede externa.");
    }
}
```
