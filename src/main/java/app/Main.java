package app;

import io.javalin.Javalin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static void main() {
        System.out.println("*** dogs api *****");

        Map<Integer, DogDTO> dogMap = new HashMap<>();

        String jsonText = "{ \"title\": \"Dog API\"}";

        DogDTO ozzy = new DogDTO(1, "Ozzy", Breed.BRETON);
        DogDTO toffee = new DogDTO(2, "Toffee", Breed.COCKER_SPANIEL);

        dogMap.put(1, ozzy);
        dogMap.put(2, toffee);

        Collection<DogDTO> dogList = dogMap.values();
        Collection<Integer> dogKeys = dogMap.keySet();

        var app = Javalin.create(config -> {
            config.routes.get("/dogs", ctx -> ctx.json(dogList));

            config.routes.get("/dogs/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                ctx.json(dogMap.get(id));
            });
            config.routes.post("/dogs", ctx -> {
                DogDTO newDog = ctx.bodyAsClass(DogDTO.class);
                dogMap.put(newDog.id(), newDog);
                ctx.result("updated");
            });

            config.routes.delete("/dogs/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                dogMap.remove(id);
                ctx.result("dog with id = " + id + " deleted");

            });
        }).start(7070);
    }
}
