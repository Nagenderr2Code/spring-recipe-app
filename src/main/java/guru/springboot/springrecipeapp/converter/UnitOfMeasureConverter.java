package guru.springboot.springrecipeapp.converter;

import com.google.common.collect.ImmutableSet;
import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class UnitOfMeasureConverter implements GenericConverter {


    ConversionService conversionService;

    public UnitOfMeasureConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public UnitOfMeasureConverter() {
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {

        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(UnitOfMeasure.class, UnitOfMeasureCommand.class),
                new ConvertiblePair(UnitOfMeasureCommand.class, UnitOfMeasure.class)

        };
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {

        if (source == null) {
            throw new RuntimeException("Source Object Can not be null..");
        }

        if (source instanceof UnitOfMeasure) {
            log.info("Converting UnitOfMeasure to UnitOfMeasureCommand..");
            UnitOfMeasure unitOfMeasure = (UnitOfMeasure) source;
            return convertToUnitOfMeasureCommand(unitOfMeasure);
        } else if (source instanceof UnitOfMeasureCommand) {
            log.info("Converting UnitOfMeasureCommand to UnitOfMeasure..");
            UnitOfMeasureCommand unitOfMeasureCommand = (UnitOfMeasureCommand) source;
            return convertSourceToUnitOfMeasure(unitOfMeasureCommand);
        } else {
            throw new RuntimeException("Invalid Source Type to Convert.."
                    + source.getClass().getName() + "Valid Source Type are.."
                    + UnitOfMeasure.class.getName() + " or " + UnitOfMeasureCommand.class.getName());
        }
    }

    private UnitOfMeasureCommand convertToUnitOfMeasureCommand(UnitOfMeasure source) {
        if (source != null) {
            final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
            uomc.setId(source.getId());
            uomc.setDescription(source.getDescription());
            return uomc;
        }
        return null;
    }

    private UnitOfMeasure convertSourceToUnitOfMeasure(UnitOfMeasureCommand source) {
        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }


}
