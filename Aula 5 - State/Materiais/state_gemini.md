
## 🧠 Parte 1: O Problema (Por que o State existe?)
Imagine que estamos programando uma Máquina de Vendas de Refrigerante. Ela possui regras rígidas baseadas em sua situação atual:

* Sem Moeda: Se você apertar o botão de "Pedir Refri", ela dá erro. Você precisa inserir uma moeda primeiro.
* Com Moeda: Se você inserir outra moeda, ela rejeita. Se apertar o botão, ela libera o refrigerante.
* Esgotada: Não aceita moedas e não libera nada.

Se usássemos uma abordagem tradicional, o código seria um emaranhado de if e else:

// Código ruim e difícil de manter:class MaquinaRefri {
    apertarBotao() {
        if (this.estado === "SEM_MOEDA") {
            console.log("Insira uma moeda primeiro!");
        } else if (this.estado === "COM_MOEDA") {
            console.log("Liberando refrigerante...");
            this.estado = "SEM_MOEDA";
        } else if (this.estado === "ESGOTADA") {
            console.log("Máquina vazia!");
        }
    }
}

O problema: Se amanhã inventarem um estado "Moeda Falsa" ou "Desconto", você terá que caçar e alterar todos os if/else do sistema. É fácil errar e quebrar o código.
------------------------------
## 🛠️ Parte 2: A Solução (Como o State resolve isso?)
O padrão State diz o seguinte: "Em vez de a máquina controlar as regras com if, vamos transformar cada estado em um objeto de verdade."
A máquina (Contexto) só guarda o estado atual e delega o trabalho para ele.
## A Estrutura em 3 Passos:

   1. O Contexto: A própria Máquina de Refrigerante.
   2. A Interface State: Uma lista de comandos que todo estado deve aceitar (ex: inserirMoeda(), apertarBotao()).
   3. Estados Concretos: Classes separadas para EstadoSemMoeda, EstadoComMoeda e EstadoEsgotada.

------------------------------
## 💻 Parte 3: O Código na Prática (TypeScript/JavaScript)
Veja como o código fica limpo, modular e elegante:

// 1. A Interface que define as ações possíveisinterface EstadoMaquina {
    inserirMoeda(): void;
    apertarBotao(): void;
}
// 2. Estado Concreto: Sem Moedaclass EstadoSemMoeda implements EstadoMaquina {
    private maquina: MaquinaVendas;

    constructor(maquina: MaquinaVendas) {
        this.maquina = maquina;
    }

    inserirMoeda() {
        console.log("Moeda aceita. Agora você pode pedir o refrigerante.");
        this.maquina.definirEstado(this.maquina.estadoComMoeda); // Transiciona o estado
    }

    apertarBotao() {
        console.log("Falhou: Você precisa inserir uma moeda primeiro!");
    }
}
// 3. Estado Concreto: Com Moedaclass EstadoComMoeda implements EstadoMaquina {
    private maquina: MaquinaVendas;

    constructor(maquina: MaquinaVendas) {
        this.maquina = maquina;
    }

    inserirMoeda() {
        console.log("Falhou: Você já inseriu uma moeda!");
    }

    apertarBotao() {
        console.log("Sucesso: Refrigerante liberado!");
        this.maquina.definirEstado(this.maquina.estadoSemMoeda); // Reseta o estado
    }
}
// 4. O Contexto (A Máquina)class MaquinaVendas {
    // Instancia os estados disponíveis
    public estadoSemMoeda: EstadoMaquina = new EstadoSemMoeda(this);
    public estadoComMoeda: EstadoMaquina = new EstadoComMoeda(this);
    
    private estadoAtual: EstadoMaquina;

    constructor() {
        this.estadoAtual = this.estadoSemMoeda; // Estado inicial
    }

    public definirEstado(novoEstado: EstadoMaquina) {
        this.estadoAtual = novoEstado;
    }

    // A mágica acontece aqui: NENHUM "if" ou "else"!
    public inserirMoeda() {
        this.estadoAtual.inserirMoeda();
    }

    public apertarBotao() {
        this.estadoAtual.apertarBotao();
    }
}
// --- Testando o Sistema ---const minhaMaquina = new MaquinaVendas();

minhaMaquina.apertarBotao(); // Saída: Falhou: Você precisa inserir uma moeda primeiro!
minhaMaquina.inserirMoeda(); // Saída: Moeda aceita...
minhaMaquina.apertarBotao(); // Saída: Sucesso: Refrigerante liberado!

------------------------------
## 🎯 Resumo da Aula

* Use o padrão State sempre que um objeto mudar drasticamente de comportamento dependendo de uma variável interna.
* Ele joga a lógica dos if/else para dentro de classes isoladas.
* Segue o princípio do "Aberto/Fechado" (SOLID): para criar um estado novo, você não mexe nas classes antigas, apenas cria uma nova.



