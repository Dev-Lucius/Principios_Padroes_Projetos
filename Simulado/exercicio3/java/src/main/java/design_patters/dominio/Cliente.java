package design_patters.dominio;

public class Cliente implements Observer{

    private final String nome;
    private final String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public void atualizar(Pedido pedido) {
        System.out.printf("\tCliente %s foi Avisado Sobre o Pedido \n", nome);
    }

}
