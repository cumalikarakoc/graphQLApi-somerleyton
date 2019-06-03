package nl.ica.ise7.GraphQLApisomerleyton.repositories;

import nl.ica.ise7.GraphQLApisomerleyton.models.Species;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SpeciesRepository extends CrudRepository<Species, String> {
    Species findBySpeciesName(String speciesName);

    @Modifying
    @Query(value = "UPDATE species SET english_name = ?2 WHERE english_name = ?1", nativeQuery = true)
    void updateSpeciesName(String speciesNameToUpdate, String speciesName);
}
