package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.beer.BeerService;
import guru.sfg.beer.order.service.services.beer.model.BeerDto;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class BeerOrderLineDecorator implements BeerOrderLineMapper {
    private BeerService beerService;
    private BeerOrderLineMapper mapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setMapper(BeerOrderLineMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto dto = mapper.beerOrderLineToDto(line);
        Optional<BeerDto> beer = beerService.getBeerByUpc(dto.getUpc());

        beer.ifPresent(beerDto -> {
           dto.setBeerName(beerDto.getBeerName());
           dto.setBeerStyle(beerDto.getBeerStyle());
           dto.setBeerId(beerDto.getId());
           dto.setPrice(beerDto.getPrice());
        });
        return dto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        return mapper.dtoToBeerOrderLine(dto);
    }

//    @Override
//    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
//        BeerDto beer = beerService.getBeerByUpc(dto.getUpc());
//        BeerOrderLine entity = mapper.dtoToBeerOrderLine(dto);
//        entity.setBeerId(beer.getId());
//        entity.setBeerName(beer.getBeerName());
//        return entity;
//    }
}
