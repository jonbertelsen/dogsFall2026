package app.config;

import app.routes.Routes;
import io.javalin.Javalin;

public class ApplicationConfig {



    public static void startServer(int port){
        Routes routes = new Routes();
        Javalin.create(config -> {
            config.routes.apiBuilder(routes.getRoutes());
            config.bundledPlugins.enableRouteOverview("/routes");
        }).start(port);
    }

}
