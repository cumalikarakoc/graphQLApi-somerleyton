package nl.ica.ise7.repositories;

import nl.ica.ise7.models.FoodKind;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface FoodKindRepository extends CrudRepository<FoodKind, String> {
    @Modifying
    @Query(value = "UPDATE food_kind SET food_type = ?2 WHERE food_type = ?1", nativeQuery = true)
    void updateFoodKind(String foodKindToUpdate, String foodType);
}
