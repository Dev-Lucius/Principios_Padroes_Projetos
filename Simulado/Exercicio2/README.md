# Solução

## Padrão Usado -> Observer

## Justificativa

O ponto principal desta questão trata-se do fato de duas entidades.

- Uma Entidade Observadora -> Aluno 
- Uma Entidade Observável -> Progresso do Aluno

Assim, podemos adotar a clássica estrutura do Observer:

```
Subject
   │
   ├── anexar()
   ├── separar()
   └── notificarObservadores()
          │
          ↓
      Observer
          │
          ↓
        Aluno
```

Logo, nossa ideia central com essa Implementação é:

> "Eu tenho Vários Objetos Interessados em Receber uma Atualização"

--- 

# Resolvendo um Observer

## 1. Encontre o "Evento"

Dentro do Problema, analise:

> "O que acontece que precisa fazer outras coisas reagirem?"

Por Exemplo:

```
Aluno concluiu atividade
        ↓
?

Pedido foi aprovado
        ↓
?

Preço do produto mudou
        ↓
?

Usuário publicou uma mensagem
        ↓
?

Temperatura mudou
        ↓
?
```

Chamamos esse acontencimento de **Evento/Mudança de Estado**

---

## 2. Encontre quem Muda

Reflita:

> "Qual objeto possui o estado que está mudando?"

Esse (normalmente) será o nosso **Subject**

Exemplo:

- Quando o Preço do Produto Mudar -> Subject é o ``Produto``
- Quando o Pedido estiver com o Status Alterado -> Subject é o ``Pedido``

---

## 3. Descubra quem Precisa ser Avisado

Reflita:

> "Quem precisa saber que essa mudança aconteceu?"

Esses são os Nossos **Observadores** (``Observer``)

Por Exemplo:

```
Pedido
  │
  │ status mudou
  ↓
 ┌───────────────────┐
 │                   │
 ↓                   ↓
EmailService    SistemaEstoque
 │
 ↓
Notificacao
```

Logo:

```
Subject:
    Pedido

Observers:
    EmailService
    SistemaEstoque
    Notificacao
```

--- 

## 4. Estruture a Interface de ```Observer```

Pense:

> "o que TODOS os observadores precisam saber?"

A resposta geralmente é:

> "**Receber uma Atualização (Update)**"

Assim:

```java
public interface Observer {

    void update();
}

// OU então
public interface Observer {

    void update(String mensagem);
}

// Também é possível
public interface Observer {

    void update(Pedido pedido);
}
```

> **Observação**

Nunca faça isso:

```java
public interface Observer {

    void enviarEmail();
    void atualizarAplicativo();
    void registrarEstatistica();
    void verificarGamificacao();
}
```
Porque você está colocando responsabilidades específicas dentro da interface.

A interface deve representar apenas o comportamento comum -> ``` "Eu consigo receber uma atualização." ```

--- 

## 5. Estruture os Observadores de Forma Concreta

Aqui, cada interessado implementa a Interface

Por Exemplo:

```java
public class EmailService implements Observer {

    @Override
    public void update(String mensagem) {
        // enviar email
    }
}
```

Assim, repetimos o processo para cada um destes Interessados.

Ao final nossa estrutura estará dessa forma:

```
                  Observer
                     ▲
          ┌──────────┼──────────┐
          │          │          │
          ↓          ↓          ↓
       Email        App    Estatísticas
```

---

## 6. Estruture a Interface Subject

Aqui vamos representar quem **possui os Observers**

Normalmente:

```java
public interface Subject {

    void anexar(Observer observer);

    void remover(Observer observer);

    void notificar();
}
```

Estes nomes podem sofrer variações

```
attach()
detach()
notify()
```

Ou...

```
adicionarObserver()
removerObserver()
notificarObservers()
```

--- 

## 7. O ``SUBJECT`` Guarda os ``OBSERVERS``

Esta é uma das características fundamentais deste padrão

```java
public class Pedido implements Subject {

    private List<Observer> observers = new ArrayList<>();

}
```

Essencialmente, o ``Pedido`` precisa saber:

> "Quem está interessado nas minhas alterações?"

---

## 8. Implemente o "Cadastro"

Para Anexar

```java
@Override
public void anexar(Observer observer) {
    observers.add(observer);
}
```

Para remover

```java
@Override
public void remover(Observer observer) {
    observers.remove(observer);
}
```

Assim, nossa estrutura ficará dessa forma:

```
Pedido
  │
  ├── EmailService
  ├── AplicativoMobile
  └── SistemaEstatisticas
```

---

## 9. Implemente as Notificações

É a parte central do projeto

```java
@Override
public void notificar() {

    for (Observer observer : observers) {
        observer.update();
    }

}
```

Aqui estamos dizendo:

> **"Para cada pessoa interessada, avise que alguma coisa mudou."**

--- 

## 10. Dispare a Notificação

Esse é um detalhe muito importante.

Não é o Observer que decide quando notificar.

É o Subject.

Por exemplo:

```java
public void alterarStatus(String novoStatus) {

    this.status = novoStatus;

    notificar();
}
```

Nosso fluxo ficará dessa forma:

```
alterarStatus()
      ↓
estado mudou
      ↓
notificar()
      ↓
┌─────┼──────┐
↓     ↓      ↓
Email App Estatística
```

--- 

## Receita Completa e Resumida

```
┌─────────────────────────────────────┐
│ 1. Qual estado/evento está mudando? │
└─────────────────┬───────────────────┘
                  ↓
┌─────────────────────────────────────┐
│ 2. Quem possui esse estado?         │
│            ↓                        │
│          SUBJECT                    │
└─────────────────┬───────────────────┘
                  ↓
┌─────────────────────────────────────┐
│ 3. Quem precisa saber da mudança?   │
│            ↓                        │
│         OBSERVERS                   │
└─────────────────┬───────────────────┘
                  ↓
┌─────────────────────────────────────┐
│ 4. O que todo Observer sabe fazer?  │
│            ↓                        │
│          update()                   │
└─────────────────┬───────────────────┘
                  ↓
┌─────────────────────────────────────┐
│ 5. Subject mantém lista de          │
│    Observers                        │
└─────────────────┬───────────────────┘
                  ↓
┌─────────────────────────────────────┐
│ 6. Estado mudou → notify()          │
└─────────────────┬───────────────────┘
                  ↓
┌─────────────────────────────────────┐
│ 7. Cada Observer recebe update()    │
└─────────────────────────────────────┘

```