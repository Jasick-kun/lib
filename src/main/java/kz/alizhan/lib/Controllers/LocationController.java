package kz.alizhan.lib.Controllers;

import kz.alizhan.lib.Entity.Book;
import kz.alizhan.lib.Entity.Location;
import kz.alizhan.lib.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/empty")
    public Boolean isEmpty(@RequestBody Location location) {
        return locationService.isEmpty(location);

    }

    @GetMapping("/")
    public Book findBook(@RequestBody Location location) {
        return locationService.findBook(location);
    }
}
