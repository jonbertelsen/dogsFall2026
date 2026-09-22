package app.dtos;

import app.enums.Breed;

public record DogDTO(
        int id,
        String name,
        Breed breed
) {
}
