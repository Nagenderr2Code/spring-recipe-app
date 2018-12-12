package guru.springboot.springrecipeapp.converter;

import com.google.common.collect.ImmutableSet;
import guru.springboot.springrecipeapp.commands.NotesCommand;
import guru.springboot.springrecipeapp.domain.Notes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@Slf4j
public class NotesConverter implements GenericConverter {


    ConversionService conversionService;

    public NotesConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public NotesConverter() {
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {

        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(Notes.class, NotesCommand.class),
                new ConvertiblePair(NotesCommand.class, Notes.class)
        };
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (source == null) {
            throw new RuntimeException("Source Object Can not be null..");
        }
        log.info("Source Type For Conversion.." + sourceType.getObjectType().getName());
        log.info("Target Type For Conversion.." + targetType.getObjectType().getName());
        if (source instanceof Notes) {
            log.info("Converting from Notes to NotesCommand..");
            Notes notes = (Notes) source;
            return convertToNotesCommand(notes, targetType);
        } else if (source instanceof NotesCommand) {
            log.info("Converting from NotesCommand to Notes..");
            NotesCommand notesCommand = (NotesCommand) source;
            return convertToNotes(notesCommand, targetType);
        } else {
            throw new IllegalArgumentException("Conversion Service only supports these Types." +
                    Notes.class.getName() + " || " + NotesCommand.class.getName());
        }
    }

    private NotesCommand convertToNotesCommand(Notes notes, TypeDescriptor targetType) {

        NotesCommand notesCommand= new NotesCommand();

        notesCommand.setId(notes.getId());
        //notesCommand.setRecipe(conversionService.convert(notes.getRecipe(), RecipeCommand.class));
        notesCommand.setRecipeNotes(notes.getRecipeNotes());
        return notesCommand;
    }

    private Notes convertToNotes(NotesCommand notesCommand, TypeDescriptor targetType) {
        final Notes notes = new Notes();

        notes.setId(notesCommand.getId());
       // notes.setRecipe(conversionService.convert(notesCommand.getRecipe(), Recipe.class));
        notes.setRecipeNotes(notesCommand.getRecipeNotes());
        return notes;
    }
}
