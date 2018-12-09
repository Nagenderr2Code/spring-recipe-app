package guru.springboot.springrecipeapp.converter;

import com.google.common.collect.ImmutableSet;
import guru.springboot.springrecipeapp.commands.NotesCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Notes;
import guru.springboot.springrecipeapp.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

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
    public Object convert(Object source, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {

        if (source == null) {
            throw new RuntimeException("Source Object Can not be null..");
        }

        if (source instanceof Notes) {
            log.info("Converting from Notes to NotesCommand..");
            Notes notes = (Notes) source;
            return convertToNotesCommand(notes);
        } else if (source instanceof NotesCommand) {
            log.info("Converting from NotesCommand to Notes..");
            NotesCommand notesCommand = (NotesCommand) source;
            return convertToNotes(notesCommand);
        } else {
            throw new IllegalArgumentException("Conversion Service only supports these Types." +
                    Notes.class.getName() + " || " + NotesCommand.class.getName());
        }
    }

    private NotesCommand convertToNotesCommand(Notes notes) {
        final NotesCommand notesCommand = new NotesCommand();

        notesCommand.setId(notes.getId());
        notesCommand.setRecipe(conversionService.convert(notes.getRecipe(), RecipeCommand.class));
        notesCommand.setRecipeNotes(notes.getRecipeNotes());
        return notesCommand;
    }

    private Notes convertToNotes(NotesCommand notesCommand) {
        final Notes notes = new Notes();

        notes.setId(notesCommand.getId());
        notes.setRecipe(conversionService.convert(notesCommand.getRecipe(), Recipe.class));
        notes.setRecipeNotes(notesCommand.getRecipeNotes());
        return notes;
    }
}
