package app.config;

import app.controllers.DogController;
import app.daos.DogDAO;
import app.routes.Routes;
import io.javalin.Javalin;

public class ApplicationConfig {

    private Routes routes;
    private DogController dogController;
    private DogDAO dogDAO;

    public ApplicationConfig(Routes routes, DogController dogController, DogDAO dogDAO) {
        this.routes = routes;
        this.dogController = dogController;
        this.dogDAO = dogDAO;
    }

    public void startServer(int port){
        Javalin.create(config -> {
            config.routes.apiBuilder(routes.getRoutes());
            config.bundledPlugins.enableRouteOverview("/routes");
        }).start(port);
    }

}
