package nl.ica.ise7.repositories;

import nl.ica.ise7.models.Area;
import nl.ica.ise7.models.FoodStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface FoodStockRepository extends CrudRepository<FoodStock, String> {


    @Query(value = "SELECT * FROM stock WHERE area_name = ?1", nativeQuery = true)
    Set<FoodStock> getFoodStockByArea(String area);

}
