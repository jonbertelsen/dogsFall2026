package app.config;

import app.Main;
import app.controllers.DogController;
import app.daos.DogDAO;
import app.exceptions.ApiException;
import app.routes.Routes;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationConfig {

    private final Routes routes;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    public ApplicationConfig(Routes routes) {
        this.routes = routes;
    }

    public void startServer(int port){
        Javalin.create(config -> {
            config.router.contextPath = "/api";
            config.routes.apiBuilder(routes.getRoutes());
            config.bundledPlugins.enableRouteOverview("/routes");
            config.routes.exception(ValidationException.class, ApplicationConfig::validationExceptionHandler);
            config.routes.exception(ApiException.class, ApplicationConfig::apiExceptionHandler);
            config.routes.exception(Exception.class, ApplicationConfig::exceptionHandler);
        }).start(port);
    }

    private static void validationExceptionHandler(ValidationException e, Context ctx){
        logger.error(e.getMessage());
        ctx.json(e.getMessage());
    }

    private static void apiExceptionHandler(ApiException e, Context ctx){
        logger.error(e.getMessage());
        int statusCode = e.getStatusCode();
        ctx.status(statusCode);
        ctx.json(e.getMessage());
    }

    private static void exceptionHandler(Exception e, Context ctx){
        logger.error(e.getMessage());
        ctx.status(500).json(e.getMessage());
    }

}
