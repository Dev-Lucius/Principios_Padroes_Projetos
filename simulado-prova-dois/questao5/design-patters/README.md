### Diagrama de Funcionamento

```txt
          CLIENTE (Main)
                |
                |
                v
       Imagem foto1 = Proxy
                |
                v
        +----------------+
        |  ImagemProxy   |
        +----------------+
        | imagemReal=null|
        +----------------+
                |
                v
          exibir()
                |
                v
       imagemReal == null?
                |
          +-----+-----+
          |           |
         SIM         NÃO
          |           |
          v           |
   Cria ImagemReal    |
          |           |
          v           |
   Carrega do disco   |
          |           |
          +-----+-----+
                |
                v
       imagemReal.exibir()
                |
                v
        Exibe a imagem
```

### Diferença Entre Proxy e Decorator

- **Decorator**: adiciona funcionalidades ou responsabilidades a um objeto.
- **Proxy**: controla o acesso a um objeto, podendo adiar sua criação.

Uma Frase para memorizar:
> Proxy é um padrão estrutural que fornece um representante de outro objeto para controlar o acesso a ele.