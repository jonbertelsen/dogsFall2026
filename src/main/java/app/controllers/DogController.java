package app.controllers;

import app.dtos.DogDTO;
import app.daos.DogDAO;
import app.exceptions.ApiException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DogController {

    private final DogDAO dogDAO;
    private final Logger logger = LoggerFactory.getLogger(DogController.class);

    public DogController(DogDAO dogDAO) {
        this.dogDAO = dogDAO;
    }

    public void getAll(Context ctx){
        List<DogDTO> dogDTOList = dogDAO.getAll();
        ctx.status(200).json(dogDTOList);
    }

    public void getById(Context ctx){

        DogDTO dogDTO = null;

            int id = ctx.pathParamAsClass("id", Integer.class)
                    .check(value -> value > 0, "id skal være større end 0")
                    .check(value -> value < 100, "id skal være mindre end 100")
                    .get();

            dogDTO = dogDAO.getById(id);
            logger.info("getById: id={}", id);
            ctx.status(200).json(dogDTO);
        }

    public void create(Context ctx){
        DogDTO newDog = ctx.bodyValidator(DogDTO.class)
                .required()
                .check(dog -> dog.name() != null && !dog.name().isBlank(),
                        "name skal udfyldes")
                .check(dog -> dog.breed() != null,
                        "breed skal udfyldes")
                .get();
        DogDTO createdDog = dogDAO.create(newDog);
        ctx.status(201).json(createdDog);
    }

    public void delete(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        dogDAO.delete(id);
        ctx.status(204);
    }
}
