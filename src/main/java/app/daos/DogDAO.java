package app.daos;

import app.enums.Breed;
import app.dtos.DogDTO;
import app.exceptions.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DogDAO {

    private Map<Integer, DogDTO> dogMap = new HashMap<>();

    public DogDAO() {
        DogDTO ozzy = new DogDTO(1, "Ozzy", Breed.BRETON);
        DogDTO toffee = new DogDTO(2, "Toffee", Breed.COCKER_SPANIEL);
        dogMap.put(1, ozzy);
        dogMap.put(2, toffee);
    }

    public List<DogDTO> getAll(){
        return dogMap.values().stream().toList();
    }

    public DogDTO create(DogDTO dogDTO){
        dogMap.put(dogDTO.id(), dogDTO);
        return dogDTO;
    }

    public Optional<DogDTO> getById(int id) {
        return Optional.ofNullable(dogMap.get(id));
    }

    public boolean delete(int id){
        return dogMap.remove(id) != null;
    }

}
