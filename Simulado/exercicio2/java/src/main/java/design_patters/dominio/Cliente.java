package design_patters.dominio;

public class Cliente implements Observer<Pedido>{
    
    private final int id;
    private final String cpf;
    private final String nome;
    private final String email;
    
    public Cliente(int id, String cpf, String nome, String email) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void update(Pedido t) {
        System.out.println(this.nome + ": Foi Notificado que o Novo Status do seu Pedido é " + t.getStatus());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("id=").append(id);
        sb.append(", cpf=").append(cpf);
        sb.append(", nome=").append(nome);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
}
