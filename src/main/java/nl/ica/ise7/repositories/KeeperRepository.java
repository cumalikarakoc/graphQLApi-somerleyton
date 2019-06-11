package nl.ica.ise7.repositories;

import nl.ica.ise7.models.Keeper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface KeeperRepository extends CrudRepository<Keeper, String> {
    @Modifying
    @Query(value = "UPDATE keeper SET keeper_name = ?2 WHERE keeper_name = ?1", nativeQuery = true)
    void updateKeeper(String keeperToUpdate, String name);
}