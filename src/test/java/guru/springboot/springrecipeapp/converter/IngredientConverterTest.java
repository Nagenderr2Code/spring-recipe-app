package guru.springboot.springrecipeapp.converter;

import guru.springboot.springrecipeapp.RecipeAppConfig;
import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.domain.Ingredient;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Import(RecipeAppConfig.class)
public class IngredientConverterTest {

    @Autowired
    DefaultConversionService conversionService;


    IngredientConverter ingredientConverter;

    @Before
    public void setUp() throws Exception {
        ingredientConverter = new IngredientConverter(conversionService);
    }

    @Test
    public void convertToIngredientCommandTest() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("Test Ingredent");
        ingredient.setQuantity(new BigDecimal(10));

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        uom.setDescription("Test Description");
        ingredient.setUom(uom);

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription("Test recipe Description");

        ingredient.setRecipe(recipe);

        IngredientCommand ingredientCommand= (IngredientCommand)ingredientConverter
                .convert(ingredient, TypeDescriptor.valueOf(Ingredient.class), TypeDescriptor.valueOf(IngredientCommand.class));

        assertEquals(ingredient.getId(), ingredientCommand.getId());
    }

    @Test
    public void convertToIngredientTest() {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setDescription("Test Ingredent");
        ingredientCommand.setQuantity(new BigDecimal(10));

        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(1L);
        uom.setDescription("Test Description");
        ingredientCommand.setUom(uom);

        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(1L);
        recipe.setDescription("Test recipe Description");

        ingredientCommand.setRecipe(recipe);

        Ingredient ingredient = (Ingredient)ingredientConverter
                .convert(ingredientCommand, TypeDescriptor.valueOf(IngredientCommand.class), TypeDescriptor.valueOf(Ingredient.class));

        assertEquals(ingredientCommand.getId(), ingredient.getId());

    }
}