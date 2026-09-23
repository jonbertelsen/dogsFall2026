package app.controllers;

import app.dtos.DogDTO;
import app.daos.DogDAO;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

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
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO dogDTO = dogDAO.getById(id);
        ctx.status(200).json(dogDTO);
    }

    public void create(Context ctx){
        DogDTO newDog = ctx.bodyAsClass(DogDTO.class);
        DogDTO createdDog = dogDAO.create(newDog);
        ctx.status(201).json(createdDog);
    }

    public void delete(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        dogDAO.delete(id);
        ctx.status(204);
    }
}
