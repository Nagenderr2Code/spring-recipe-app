package guru.springboot.springrecipeapp.converter;

import guru.springboot.springrecipeapp.RecipeAppConfig;
import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(RecipeAppConfig.class)
public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UnitOfMeasureConverter converter;

    @Autowired
    DefaultConversionService service;


    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureConverter(service);
    }

    @Test
    public void ConvertToUnitOfMeasureCommand() {
        UnitOfMeasure uom = new UnitOfMeasure();

        uom.setDescription(DESCRIPTION);
        uom.setId(LONG_VALUE);

        UnitOfMeasureCommand uomCommand = (UnitOfMeasureCommand)converter.convert(uom, TypeDescriptor.valueOf(UnitOfMeasure.class),
                TypeDescriptor.valueOf(UnitOfMeasureCommand.class));

        assertEquals(LONG_VALUE, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());
    }

    @Test
    public void ConvertToUnitOfMeasure() {

        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();

        uomc.setDescription(DESCRIPTION);
        uomc.setId(LONG_VALUE);

        UnitOfMeasure uomCommand = (UnitOfMeasure)converter.convert(uomc, TypeDescriptor.valueOf(UnitOfMeasure.class),
                TypeDescriptor.valueOf(UnitOfMeasureCommand.class));

        assertEquals(LONG_VALUE, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());

    }
}