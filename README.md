# Princípios e Padrões de Projeto

Repositório dedicado aos estudos, exercícios, anotações e projetos práticos da disciplina **Princípios e Padrões de Projeto**, cursada na faculdade. Aqui você encontrará o material desenvolvido ao longo do semestre, organizado por tópico, incluindo implementações de padrões de projeto, refatorações, exemplos de aplicação dos princípios SOLID e discussões sobre arquitetura de software.

## 📖 Sobre a disciplina

A disciplina tem como objetivo aprofundar o conhecimento sobre boas práticas de projeto orientado a objetos, capacitando o aluno a tomar decisões de design mais conscientes, escrever código mais flexível, reutilizável e de fácil manutenção, além de reconhecer e evitar armadilhas comuns no desenvolvimento de software.

### Ementa

- **Conceitos de projeto**: hierarquia, abstração, modularização e encapsulamento
- **Decisões de projeto**: uso de herança, composição e delegação
- **Princípios de projeto de software**: SOLID e outros princípios
- **Refatoração** de código
- **Padrões de projeto de software**: catálogo GoF (Gang of Four) e padrões emergentes
- **Anti-padrões** (anti-patterns)
- **Noções de princípios e padrões de arquitetura** de software

## 🧠 Principais tópicos abordados

### 1. Conceitos de projeto
Fundamentos que sustentam um bom design orientado a objetos:
- **Hierarquia** – organização de classes e responsabilidades em níveis
- **Abstração** – foco no essencial, ocultando detalhes de implementação
- **Modularização** – divisão do sistema em módulos coesos e independentes
- **Encapsulamento** – proteção do estado interno dos objetos

### 2. Herança, composição e delegação
Discussão sobre quando utilizar cada abordagem, vantagens e desvantagens, e o princípio *"favoreça composição em vez de herança"*.

### 3. Princípios SOLID
| Sigla | Princípio | Descrição resumida |
|-------|-----------|---------------------|
| **S** | Single Responsibility Principle | Uma classe deve ter apenas um motivo para mudar |
| **O** | Open/Closed Principle | Aberto para extensão, fechado para modificação |
| **L** | Liskov Substitution Principle | Subtipos devem ser substituíveis por seus tipos base |
| **I** | Interface Segregation Principle | Muitas interfaces específicas são melhores que uma genérica |
| **D** | Dependency Inversion Principle | Dependa de abstrações, não de implementações concretas |

Além do SOLID, também são explorados outros princípios como **DRY** (Don't Repeat Yourself), **KISS** (Keep It Simple, Stupid), **YAGNI** (You Aren't Gonna Need It) e **Lei de Demeter**.

### 4. Refatoração
Técnicas para melhorar a estrutura interna do código sem alterar seu comportamento externo, identificando *code smells* e aplicando refatorações catalogadas (ex.: Extract Method, Extract Class, Replace Conditional with Polymorphism, entre outras).

### 5. Padrões de projeto GoF
Implementações e exemplos dos 23 padrões clássicos do livro *Design Patterns: Elements of Reusable Object-Oriented Software* (Gamma, Helm, Johnson e Vlissides), divididos em:
- **Criacionais**: tratam da criação de objetos
- **Estruturais**: tratam da composição de classes e objetos
- **Comportamentais**: tratam da comunicação entre objetos

### 6. Padrões emergentes
Padrões que surgiram após o catálogo GoF, adaptados a contextos modernos de desenvolvimento (ex.: Dependency Injection, Repository, Unit of Work, MVVM, entre outros).

### 7. Anti-padrões
Soluções recorrentes, porém ineficazes ou prejudiciais, para problemas de projeto — como *God Object*, *Spaghetti Code*, *Golden Hammer* e *Copy-Paste Programming* — com discussão sobre como identificá-las e evitá-las.

### 8. Princípios e padrões de arquitetura
Introdução a conceitos arquiteturais, como camadas (*layered architecture*), MVC, arquitetura hexagonal, microsserviços e princípios como separação de responsabilidades em nível arquitetural.

## 🛠️ Tecnologias utilizadas

> Ajuste conforme as linguagens/ferramentas utilizadas no seu curso.

- Linguagem principal: `Java` 
- Diagramas UML: [draw.io](https://draw.io) / [PlantUML](https://plantuml.com)
- Controle de versão: Git & GitHub

## 🚀 Como executar os projetos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/Principios_Padroes_Projetos.git

# Acesse a pasta desejada

# Siga as instruções específicas de cada subpasta (README individual, se houver)
```

## 📚 Referências bibliográficas

- GAMMA, E.; HELM, R.; JOHNSON, R.; VLISSIDES, J. **Design Patterns: Elements of Reusable Object-Oriented Software**. Addison-Wesley, 1994.
- FREEMAN, E.; ROBSON, E. **Head First Design Patterns**. O'Reilly Media.
- MARTIN, R. C. **Clean Code: A Handbook of Agile Software Craftsmanship**. Prentice Hall.
- MARTIN, R. C. **Clean Architecture: A Craftsman's Guide to Software Structure and Design**. Prentice Hall.
- FOWLER, M. **Refactoring: Improving the Design of Existing Code**. Addison-Wesley.
- BROWN, W. J. et al. **AntiPatterns: Refactoring Software, Architectures, and Projects in Crisis**. Wiley.

## 👤 Autor

Desenvolvido por **Lucas Oliveira**, estudante do TADS - IFRS

- GitHub: [@seu-usuario](https://github.com/Dev-Lucius)

## 📄 Licença

Este repositório é de uso educacional e está disponível sob a licença [MIT](LICENSE), salvo indicação contrária.

---

*Repositório em constante atualização conforme o andamento da disciplina.*
