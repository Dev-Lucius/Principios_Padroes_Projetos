package negocio;

// exemplo de condimento
public class H1 extends CondimentDecorator {

    public H1(Markdown markdown, String h1) {
        super(markdown);
        this.codigo = "# "+h1+"\n";
    } 

}
