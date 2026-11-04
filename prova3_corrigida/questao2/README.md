# Questão 2 - Integração entre Sistemas Legados

O problema da questão supracitada envolve realizar a integração entre os módulos de pagamento dentro de um sistema legado

Espera-se que os novos serviços de pagamento que serão implementados disponibilizem um métodos chamado ```processarPagamento()```

Contudo, existe dois problemas graves. Primeiro, a antiga biblioteca disponibiliza apenas um método chamado ```executarTransação(String conta, int valorCentavos)```.

Além disso, os Devs não possuem autorização para realizar quaisquer alterações dentro do código-fonte do Sistema, logo será necessário implementar uma solução que possibilite
utilizar tal biblioteca sem modificar o código antigo.

> Esse é um clássico problema que podemos resolver com ``Adapter``

- Vamos usar o Adapter a fim de **"adaptar"** a biblioteca antiga do sistema do sistema legado de tal modo a reaproveitar sua estrutura dentro da implementação do novo Gateway de Pagamento

- Além disso, O `Checkout` (Client) nunca conhece as classes dos fornecedores. Para trocar de fornecedor, basta trocar o adaptador.