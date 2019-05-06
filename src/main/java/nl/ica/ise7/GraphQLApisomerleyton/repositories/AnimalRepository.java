package nl.ica.ise7.GraphQLApisomerleyton.repositories;

import nl.ica.ise7.GraphQLApisomerleyton.models.Animal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
    @Query(value = "SELECT * from ANIMAL a INNER JOIN ANIMAL_ENCLOSURE ae on a.animal_id = ae.animal_id WHERE ae.enclosure_num = ?1 ", nativeQuery = true)
    List<Animal> findAnimalsByEnclosure(Long enclosureId);
}
