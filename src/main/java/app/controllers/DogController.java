package app.controllers;

import app.dtos.DogDTO;
import app.daos.DogDAO;
import app.exceptions.ApiException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class DogController {

    private final DogDAO dogDAO;

    public DogController(DogDAO dogDAO) {
        this.dogDAO = dogDAO;
    }

    public void getAll(Context ctx){
        List<DogDTO> dogDTOList = dogDAO.getAll();
        ctx.status(200).json(dogDTOList);
    }

    public void getById(Context ctx){
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        DogDTO dogDTO = dogDAO.getById(id)
                .orElseThrow(() -> new ApiException(404, "Hund med id = " + id + " findes ikke"));
        ctx.status(200).json(dogDTO);
    }

    public void create(Context ctx) {
        DogDTO newDog = ctx.bodyValidator(DogDTO.class)
                .check(dog -> dog.name() != null && !dog.name().isBlank(), "Name is required")
                .check(dog -> dog.breed() != null, "Breed is required")
                .get();
        DogDTO createdDog = dogDAO.create(newDog);
        ctx.status(201).json(createdDog);
    }

    public void delete(Context ctx){
        int id = ctx.pathParamAsClass("id", Integer.class)
                .check(value -> value > 0, "ID must be positive")
                .get();

        if (!dogDAO.delete(id)) {
            throw new ApiException(404, "Hund med id = " + id + " findes ikke");
        }
        ctx.status(204);
    }
}
