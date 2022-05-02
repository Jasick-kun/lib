package kz.alizhan.lib.Service;


import kz.alizhan.lib.Entity.Book;
import kz.alizhan.lib.Entity.Location;

public interface LocationService {
    Boolean isEmpty(Location location);

    Location addLocation(Location location);

    Book findBook(Location location);
}
