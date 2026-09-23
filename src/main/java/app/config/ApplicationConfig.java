package app.config;

import app.exceptions.ApiException;
import app.routes.Routes;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConfig {

    private final Routes routes;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    public ApplicationConfig(Routes routes) {
        this.routes = routes;
    }

    public void startServer(int port){
        Javalin.create(config -> {
            config.startup.showJavalinBanner = false;
            config.routes.apiBuilder(routes.getRoutes());
            config.router.contextPath = "/api"; // base path for all endpoints
            config.bundledPlugins.enableRouteOverview("/routes");
            config.routes.exception(Exception.class, ApplicationConfig::generalExceptionHandler);
            config.routes.exception(ApiException.class, ApplicationConfig::apiExceptionHandler);
        }).start(port);
    }

    public static void stopServer(Javalin app) {
        app.stop();
    }

    public static void apiExceptionHandler(ApiException e, Context ctx) {
        logger.warn("An API exception occurred: Code: {}, Message: {}", e.getStatusCode(), e.getMessage());
        ctx.status(e.getStatusCode()).json(convertToJsonMessage(ctx, "message", e.getMessage()));
    }

    private static void generalExceptionHandler(Exception e, Context ctx) {
        logger.error("An unhandled exception occurred: Message: {}", e.getMessage());
        ctx.json(convertToJsonMessage(ctx, "message", e.getMessage()));
    }

    public static String convertToJsonMessage(Context ctx, String property, String message) {
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(property, message);  // Put the message in the map
        msgMap.put("status", String.valueOf(ctx.status()));  // Put the status in the map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(msgMap);  // Convert the map to JSON
        } catch (Exception e) {
            return "{\"error\": \"Could not convert  message to JSON\"}";
        }
    }

}
