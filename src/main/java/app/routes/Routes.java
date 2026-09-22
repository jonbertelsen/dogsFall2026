package app.routes;

import app.controllers.DogController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {
    private final DogController dogController = new DogController();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/dogs", () -> {
                get("", dogController::getAll);
                get("/{id}", dogController::getById);
                post("/", dogController::create);
                delete("/{id}", dogController::delete);
            });
        };
    }
}
