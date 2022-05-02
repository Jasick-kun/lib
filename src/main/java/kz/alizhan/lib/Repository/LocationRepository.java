package kz.alizhan.lib.Repository;

import kz.alizhan.lib.Entity.Book;
import kz.alizhan.lib.Entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query(value = "select * from location where shelf_rack=:shelfRack " +
            "and shelf=:shelf and order_number=:orderNumber", nativeQuery = true)
    Location find(Integer shelfRack, Integer shelf, Integer orderNumber);

    @Query(value = "select * from location l join book b on l.id = b.location_id where shelf_rack=:shelfRack " +
            "and shelf=:shelf and order_number=:orderNumber", nativeQuery = true)
    Book findBook(Integer shelfRack, Integer shelf, Integer orderNumber);


}
