package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.domain.Ingredient;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceTest {

    @InjectMocks
    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    ConversionService conversionService;

    private final Long recipeId = 1L;
    private final Long ingredientId = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findCommandById() {

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredientId);

        //Optional<IngredientCommand> optionalIngredientCommand = Optional.of(ingredientCommand);

        when(conversionService.convert(ingredient, IngredientCommand.class)).thenReturn(ingredientCommand);
        IngredientCommand ingredientCommandFound= ingredientService.findCommandById(recipeId, ingredientId);

        assertEquals(ingredientId, ingredientCommandFound.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(conversionService, times(1)).convert(ingredient, IngredientCommand.class);

    }
}