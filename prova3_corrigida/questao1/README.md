# Questão 1 - O Acesso Controlado aos Documentos

O problema da questão envolve o gerenciamento de acesso a determinados tipos de documentos.

Em suma, Alguns arquivos, além de serem muito pesados, contém informações sigilosas que podem ser acessadas por qualquer tipo de usuário.

A ideia é implementar uma solução na qual o acesso aos documentos deverá ser intermediado por outro objeto que também realizará a verificação do nivel de permissão do usuário.

- Isso é um Claro problema que pode ser resolvido com **Proxy**

> A solução  ́e usar outro objeto, um proxy (procurador), que funciona como um substituto temporário do documento real. 

> O proxy funciona exatamente como a imagem e toma conta da sua instanciação quando a mesma for necessária