package apresentacao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.javalin.Javalin;
import negocio.Command;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        var app = Javalin.create(config -> {
            config.routes.get("/{command}", ctx -> {
                String acao = ctx.pathParam("command");
                Class<?> clazz = Class.forName("negocio." + acao + "Command");
                Constructor<?> constructor = clazz.getConstructor();
                Object instance = constructor.newInstance();
                Command command = (Command) instance;
                command.execute(ctx);
            });
        }).start(7070);
    }
}