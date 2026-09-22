package app;

import app.config.ApplicationConfig;

public class Main {
    static void main() {
        System.out.println("*** dogs api *****");
        ApplicationConfig.startServer(7070);
    }
}
