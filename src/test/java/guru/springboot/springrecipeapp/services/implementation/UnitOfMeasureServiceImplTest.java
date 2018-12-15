package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import guru.springboot.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    ConversionService conversionService;

    @InjectMocks
    UnitOfMeasureServiceImpl unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByDescription() {
    }

    @Test
    public void findAll() {

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Test Description");
        unitOfMeasure.setId(1L);

        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet();
        unitOfMeasureSet.add(unitOfMeasure);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureSet);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setDescription("Test Description");
        unitOfMeasureCommand.setId(1L);

        Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet();
        unitOfMeasureCommands.add(unitOfMeasureCommand);

        when(conversionService.convert(unitOfMeasure, UnitOfMeasureCommand.class)).thenReturn(unitOfMeasureCommand);

        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet = unitOfMeasureService.findAll();

        assertFalse(unitOfMeasureCommandSet.isEmpty());
    }
}