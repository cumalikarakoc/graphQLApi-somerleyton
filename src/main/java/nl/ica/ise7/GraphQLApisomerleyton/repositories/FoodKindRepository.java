package nl.ica.ise7.GraphQLApisomerleyton.repositories;

import nl.ica.ise7.GraphQLApisomerleyton.models.FoodKind;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface FoodKindRepository extends CrudRepository<FoodKind, String> {
    @Modifying
    @Query(value = "UPDATE food_kind SET food_type_ft = ?2 WHERE food_type_ft = ?1", nativeQuery = true)
    void updateFoodKind(String foodKindToUpdate, String foodType);
}
