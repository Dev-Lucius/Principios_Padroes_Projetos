package negocio;

import io.javalin.http.Context;

public class TelaAdicionarUsuarioCommand implements Command {

    @Override
    public void execute(Context ctx) {
        ctx.html("<form action='post'> Nome: <input type='text' name='nome'> <input type = submit> </form>");
    }

}
