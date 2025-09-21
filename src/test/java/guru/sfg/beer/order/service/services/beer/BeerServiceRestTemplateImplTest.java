package guru.sfg.beer.order.service.services.beer;

import guru.sfg.beer.order.service.services.beer.model.BeerDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Disabled // utility for manual testing
@SpringBootTest
class BeerServiceRestTemplateImplTest {

    private static final String UPC = "0631234200036";

    @Autowired
    private BeerService beerService;

    @Test
    void getBeerByUpc() {
        Optional<BeerDto> beer = beerService.getBeerByUpc(UPC);
        System.out.println("getBeerByUpc: " + (beer.isPresent()? beer.get().toString() : "notfnd"));
    }

}