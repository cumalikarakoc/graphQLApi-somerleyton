package nl.ica.ise7.GraphQLApisomerleyton.repositories;

import nl.ica.ise7.GraphQLApisomerleyton.models.Enclosure;
import nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys.EnclosureIdentity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnclosureRepository extends CrudRepository<Enclosure, EnclosureIdentity> {
    List<Enclosure> findAllByEnclosureIdentity_AreaName(String areaName);

    @Query(value = "SELECT MAX(enclosure_num) + 1 FROM enclosure WHERE area_name = ?1", nativeQuery = true)
    int getNextEnclosureNumber(String areaName);
}
