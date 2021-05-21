package com.filos.vertx;

import com.filos.configs.VertxConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainApp extends AbstractVerticle {
    private final VertxConfig config = new VertxConfig();
    private final Controller controller = new Controller();

    @Override
    public void start() {
        //        Json.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        var router = Router.router(vertx);
        router.route()
              .handler(BodyHandler.create());
        router.post("/users")
              .handler(controller::createUser);
        router.get("/login")
              .handler(controller::login);
        router.get("/users/:userId")
              .handler(controller::findUser);
        router.get("/users")
              .handler(controller::findAllUser);

        vertx.createHttpServer()
             .requestHandler(router)
             .listen(8080);
    }

    public static void main(final String[] args) {
        Launcher.executeCommand("run", MainApp.class.getName());
    }
}
