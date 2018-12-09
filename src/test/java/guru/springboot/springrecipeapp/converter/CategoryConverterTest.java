package guru.springboot.springrecipeapp.converter;

import guru.springboot.springrecipeapp.RecipeAppConfig;
import guru.springboot.springrecipeapp.commands.CategoryCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Category;
import guru.springboot.springrecipeapp.domain.Recipe;
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
public class CategoryConverterTest {

    @Autowired
    DefaultConversionService conversionService;

    CategoryConverter categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryConverter(conversionService);
    }

    @Test
    public void convertToCategoryCommandTest() {
        Category category = new Category();
        category.setId(1L);
        category.setDescription("Category Test Description");

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription("Test Recipe Description");

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        category.setRecipes(recipes);

        CategoryCommand categoryCommand = (CategoryCommand) categoryConverter.convert(category,
                TypeDescriptor.valueOf(Category.class), TypeDescriptor.valueOf(CategoryCommand.class));

        assertEquals(categoryCommand.getId(), category.getId());

    }

    @Test
    public void convertToCategory() {
        {
            CategoryCommand categoryCommand = new CategoryCommand();
            categoryCommand.setId(1L);
            categoryCommand.setDescription("Category Test Description");

            RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(1L);
            recipeCommand.setDescription("Test Recipe Description");

            Set<RecipeCommand> recipeCommands = new HashSet<>();
            recipeCommands.add(recipeCommand);

            categoryCommand.setRecipes(recipeCommands);

            Category category = (Category) categoryConverter.convert(categoryCommand,
                    TypeDescriptor.valueOf(Category.class), TypeDescriptor.valueOf(CategoryCommand.class));

            assertEquals(category.getId(), categoryCommand.getId());

        }
    }
}