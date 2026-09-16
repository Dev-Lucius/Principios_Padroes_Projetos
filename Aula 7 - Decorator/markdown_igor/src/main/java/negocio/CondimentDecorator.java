package negocio;

public abstract class CondimentDecorator extends Markdown {
    protected Markdown markdown;

    public CondimentDecorator(Markdown markdown){
        this.markdown = markdown;
    }

    public String codigo(){
        return this.markdown.codigo() + this.codigo;
    }


    // // aqui, propriamente, eu removo o ultimo item
    // @Override
    // public Markdown removeTag(){
    //     return markdown;
    // }   
   

}
