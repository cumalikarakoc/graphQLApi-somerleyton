package nl.ica.ise7.repositories;

import nl.ica.ise7.models.Area;
import nl.ica.ise7.models.Keeper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AreaRepository extends CrudRepository<Area, String> {
    Area findByHeadKeeper(Keeper keeper);

    @Modifying
    @Query(value = "UPDATE area SET area_name = ?2, headkeeper = ?3 WHERE area_name = ?1", nativeQuery = true)
    void updateArea(String areaToUpdate, String areaName, String keeperName);
}
