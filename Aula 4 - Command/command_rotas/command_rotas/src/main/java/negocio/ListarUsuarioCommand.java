package negocio;

import java.util.ArrayList;

import io.javalin.http.Context;

public class ListarUsuarioCommand implements Command {

    @Override
    public void execute(Context ctx) {
        // pode vir do bd
        ArrayList<String> vet = new ArrayList<>();
        vet.add("igor");
        vet.add("joao");
        ctx.json(vet);
    }
}
