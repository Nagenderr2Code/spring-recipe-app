package guru.springboot.springrecipeapp.converter;

import guru.springboot.springrecipeapp.RecipeAppConfig;
import guru.springboot.springrecipeapp.commands.CategoryCommand;
import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.NotesCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Category;
import guru.springboot.springrecipeapp.domain.Ingredient;
import guru.springboot.springrecipeapp.domain.Notes;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.enums.Difficulity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(RecipeAppConfig.class)
public class RecipeConverterTest {

    @Autowired
    DefaultConversionService conversionService;

    RecipeConverter recipeConverter;

    @Before
    public void setUp() throws Exception {
        recipeConverter = new RecipeConverter(conversionService);
    }

    @Test
    public void convertToRecipeTest() {
        RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(1L);
        recipeCommand.setDescription("Test Description");
        recipeCommand.setDifficulity(Difficulity.MODERATE);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(1L);
        categoryCommand.setDescription("Test Category");

        Set<CategoryCommand>categoryCommands= new HashSet<>();
        categoryCommands.add(categoryCommand);

        recipeCommand.setCategories(categoryCommands);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setDescription("Test Ingredient");

        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        ingredientCommands.add(ingredientCommand);
        recipeCommand.setIngredients(ingredientCommands);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(1L);
        notesCommand.setRecipeNotes("Test Recipe Notes");

        recipeCommand.setNotes(notesCommand);

        Recipe recipe =  (Recipe) recipeConverter.convert(recipeCommand, TypeDescriptor.valueOf(RecipeCommand.class),
                TypeDescriptor.valueOf(Recipe.class));


        assertEquals(recipeCommand.getId(), recipe.getId());


    }

    @Test
    public void convertToRecipeCommandTest() {


        Recipe recipe = new Recipe();

        recipe.setId(1L);
        recipe.setDescription("Test Description");
        recipe.setDifficulity(Difficulity.MODERATE);

        Category category = new Category();
        category.setId(1L);
        category.setDescription("Test Category");

        Set<Category> categories= new HashSet<>();
        categories.add(category);

        recipe.setCategories(categories);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("Test Ingredient");

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);

        Notes notes = new Notes();
        notes.setId(1L);
        notes.setRecipeNotes("Test Recipe Notes");

        recipe.setNotes(notes);

        RecipeCommand recipeCommand =  (RecipeCommand) recipeConverter.convert(recipe, TypeDescriptor.valueOf(Recipe.class),
                TypeDescriptor.valueOf(RecipeCommand.class));


        assertEquals(recipe.getId(), recipeCommand.getId());
    }
}