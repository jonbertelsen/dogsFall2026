package app;

import app.config.ApplicationConfig;
import app.controllers.DogController;
import app.daos.DogDAO;
import app.routes.Routes;

public class Main {


    static void main() {
        DogDAO dogDAO = new DogDAO();
        DogController dogController = new DogController(dogDAO);
        Routes routes = new Routes(dogController);

        ApplicationConfig applicationConfig = new ApplicationConfig(routes);
        applicationConfig.startServer(7070);
    }
}
