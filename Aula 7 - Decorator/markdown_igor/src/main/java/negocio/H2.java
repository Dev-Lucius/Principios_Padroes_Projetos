package negocio;

// exemplo de condimento
public class H2 extends CondimentDecorator {

    public H2(Markdown markdown, String h2) {
        super(markdown);
        this.codigo = "## "+h2+"\n";

    }
 

}
