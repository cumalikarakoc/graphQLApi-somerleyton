package nl.ica.ise7.repositories;

import nl.ica.ise7.models.Exchange;
import nl.ica.ise7.models.compositeKeys.ExchangeIdentity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public interface ExchangeRepository extends CrudRepository<Exchange, ExchangeIdentity> {

    @Query(value = "SELECT * FROM exchange WHERE animal_id = ?1", nativeQuery = true)
    List<Exchange> findExchangesByAnimalId(String animalId);

    @Modifying
    @Query(value = "UPDATE exchange SET animal_id = ?3, exchange_date = ?4 WHERE animal_id = ?1 AND exchange_date = ?2", nativeQuery = true)
    void updateExchangeIdentity(String animalIdToUpdate, Date exchangeDateToUpdate, String animalId, Date exchangeDate);
}
