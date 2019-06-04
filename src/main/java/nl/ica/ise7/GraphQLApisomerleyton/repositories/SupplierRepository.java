package nl.ica.ise7.GraphQLApisomerleyton.repositories;

import nl.ica.ise7.GraphQLApisomerleyton.models.Supplier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SupplierRepository extends CrudRepository<Supplier, String> {

    @Modifying
    @Query(value = "UPDATE supplier SET supplier_name = ?2, phone_number = ?3, address = ?4 WHERE supplier_name = ?1", nativeQuery = true)
    void updateSupplier(String supplierToUpdate, String supplierName, String phone, String address);

}
