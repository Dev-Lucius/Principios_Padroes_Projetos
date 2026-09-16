package negocio;

// exemplo de condimento
public class Link extends CondimentDecorator {

    public Link(Markdown markdown, String nome, String link) {
        super(markdown);
        this.codigo = "["+nome+"]("+link+")\n";
    }


  

}
