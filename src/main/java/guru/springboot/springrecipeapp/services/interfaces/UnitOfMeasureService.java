package guru.springboot.springrecipeapp.services.interfaces;

import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Optional;
import java.util.Set;

public interface UnitOfMeasureService {

    Optional<UnitOfMeasureCommand> findByDescription(String description);

    Set<UnitOfMeasureCommand> findAll();
}
