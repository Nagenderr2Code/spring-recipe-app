package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import guru.springboot.springrecipeapp.repositories.UnitOfMeasureRepository;
import guru.springboot.springrecipeapp.services.interfaces.UnitOfMeasureService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    UnitOfMeasureRepository unitOfMeasureRepository;

    ConversionService conversionService;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, ConversionService conversionService) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.conversionService = conversionService;
    }

    @Override
    public Optional<UnitOfMeasureCommand> findByDescription(String description) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Set<UnitOfMeasureCommand> findAll() {

        Set<UnitOfMeasure> unitOfMeasures= unitOfMeasureRepository.findAll();

        return unitOfMeasures.stream()
                .map(uom ->conversionService.convert(uom, UnitOfMeasureCommand.class))
                .collect(Collectors.toSet());
    }
}
