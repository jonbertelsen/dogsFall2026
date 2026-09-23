package app;

import app.config.ApplicationConfig;
import app.controllers.DogController;
import app.daos.DogDAO;
import app.routes.Routes;

public class Main {


    static void main() {
        System.out.println("*** dogs api *****");

        DogDAO dogDAO = new DogDAO();
        DogController dogController = new DogController(dogDAO);
        Routes routes = new Routes(dogController);

        ApplicationConfig applicationConfig = new ApplicationConfig(routes, dogController, dogDAO);

        applicationConfig.startServer(7070);
    }
}
