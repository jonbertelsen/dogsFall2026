package app.controllers;

import app.dtos.DogDTO;
import app.daos.DogDAO;
import io.javalin.http.Context;

import java.util.List;

public class DogController {

    private final DogDAO dogDAO;

    public DogController(DogDAO dogDAO) {
        this.dogDAO = dogDAO;
    }

    public void getAll(Context ctx){
        List<DogDTO> dogDTOList = dogDAO.getAll();
        ctx.json(dogDTOList);
    }

    public void getById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO dogDTO = dogDAO.getById(id);
        ctx.json(dogDTO);
    }

    public void create(Context ctx){
        DogDTO newDog = ctx.bodyAsClass(DogDTO.class);
        DogDTO createdDog = dogDAO.create(newDog);
        ctx.json(createdDog);
    }

    public void delete(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        dogDAO.delete(id);
    }
}
