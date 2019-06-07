package nl.ica.ise7.repositories;

import nl.ica.ise7.models.Animal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AnimalRepository extends CrudRepository<Animal, String> {

    @Modifying
    @Query(value = "UPDATE animal SET animal_id = ?2 WHERE animal_id = ?1", nativeQuery = true)
    void updateAnimalId(String id, String value);
}
