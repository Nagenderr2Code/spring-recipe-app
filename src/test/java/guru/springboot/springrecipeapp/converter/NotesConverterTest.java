package guru.springboot.springrecipeapp.converter;

import guru.springboot.springrecipeapp.RecipeAppConfig;
import guru.springboot.springrecipeapp.commands.NotesCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Notes;
import guru.springboot.springrecipeapp.domain.Recipe;
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
public class NotesConverterTest {

    @Autowired
    DefaultConversionService conversionService;

    NotesConverter notesConverter;

    @Before
    public void setUp() throws Exception {
        notesConverter = new NotesConverter(conversionService);
    }

    @Test
    public void convertToNotesCommandTest() {
        Notes notes = new Notes();
        notes.setId(1L);
        notes.setRecipeNotes("Test Notes");

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription("Test Recipe");

        notes.setRecipe(recipe);

       NotesCommand notesCommand =(NotesCommand) notesConverter.convert(notes, TypeDescriptor.valueOf(Notes.class),
                TypeDescriptor.valueOf(NotesCommand.class));

       assertEquals(notes.getId(), notesCommand.getId());

    }

    @Test
    public void convertToNotesTest() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(1L);
        notesCommand.setRecipeNotes("Test Notes");

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("Test Recipe");

        notesCommand.setRecipe(recipeCommand);

        Notes notes =(Notes) notesConverter.convert(notesCommand, TypeDescriptor.valueOf(Notes.class),
                TypeDescriptor.valueOf(NotesCommand.class));

        assertEquals(notes.getId(), notesCommand.getId());
    }
}