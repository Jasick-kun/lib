package kz.alizhan.lib.Service.Impl;

import kz.alizhan.lib.Entity.Book;
import kz.alizhan.lib.Entity.Location;
import kz.alizhan.lib.Repository.LocationRepository;
import kz.alizhan.lib.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Boolean isEmpty(Location location) {
        if (null == locationRepository.find(location.getShelfRack(), location.getShelf(), location.getOrderNumber())) {
            return false;
        } else return true;
    }

    @Override
    public Location addLocation(Location location) {
        location.setId(null);
        locationRepository.save(location);
        return locationRepository.find(location.getShelfRack(), location.getShelf(), location.getOrderNumber());
    }

    @Override
    public Book findBook(Location location) {
        return locationRepository.findBook(location.getShelfRack(), location.getShelf(), location.getOrderNumber());

    }

}
