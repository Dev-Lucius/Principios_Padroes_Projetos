# Questão 3 - Personalização Dinâmica dos Serviços

O problema final envolve uma plataforma de armazenamento em nuvem que oferece um serviço básico de armazenamentos de arquivos, porém, deseja-se adicionar novas funcionalidades que poderão ser contratadas individualmente ou combinadas livremente dependendo do critério do cliente.

Assim, deseja-se implementar uma solução que torne possível adicionar novas funcionalidades ao Sistema de forma dinâmica sem a necessidade de modificar a implementação original.

> Esse é um problema que pode ser resolvido com **Decorator**

- A ideia é usar o Decorator para anexar responsabilidades adicionais a um objeto dinamicamente. 

- Os decoradores fornecem uma alternativa flexível de subclasse para estender a funcionalidade 