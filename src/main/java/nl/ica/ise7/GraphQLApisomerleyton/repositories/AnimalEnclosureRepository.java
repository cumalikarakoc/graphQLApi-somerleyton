package nl.ica.ise7.GraphQLApisomerleyton.repositories;

import nl.ica.ise7.GraphQLApisomerleyton.models.AnimalEnclosure;
import nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys.AnimalEnclosureIdentity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalEnclosureRepository extends CrudRepository<AnimalEnclosure, AnimalEnclosureIdentity> {

    @Query(value = "SELECT * FROM animal_enclosure WHERE animal_id = ?1", nativeQuery = true)
    List<AnimalEnclosure> findEnclosuresByAnimalId(String animalId);

    List<AnimalEnclosure> findAnimalEnclosuresByAreaNameAndEnclosureNumber(String areaName, int enclosureNumber);
}
