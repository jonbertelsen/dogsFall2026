package app;

import app.config.ApplicationConfig;
import app.controllers.DogController;
import app.daos.DogDAO;
import app.routes.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    // Create a logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Logger debugLogger = LoggerFactory.getLogger("app");

    static void main() {
       logger.error("*** dogs api *****");

       debugLogger.debug("debug besked");

        DogDAO dogDAO = new DogDAO();
        DogController dogController = new DogController(dogDAO);
        Routes routes = new Routes(dogController);

        ApplicationConfig applicationConfig = new ApplicationConfig(routes);

        applicationConfig.startServer(7070);
    }
}
