package guru.springboot.springrecipeapp.converter;

import com.google.common.collect.ImmutableSet;
import guru.springboot.springrecipeapp.commands.CategoryCommand;
import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.NotesCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Category;
import guru.springboot.springrecipeapp.domain.Ingredient;
import guru.springboot.springrecipeapp.domain.Notes;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.enums.Difficulity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class RecipeConverter implements GenericConverter {


    ConversionService conversionService;

    public RecipeConverter() {
    }

    public RecipeConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(Recipe.class, RecipeCommand.class),
                new ConvertiblePair(RecipeCommand.class, Recipe.class)
        };
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {

        if (source == null) {
            throw new RuntimeException("Source Can not be Null for the conversion");
        }

        if (source instanceof Recipe) {
            log.info("Converting from Recipe to RecipeCommand.");
            Recipe recipe = (Recipe) source;
            return convertToRecipeCommand(recipe);

        } else if (source instanceof RecipeCommand) {
            log.info("Converting from RecipeCommand  to Recipe.");
            RecipeCommand recipeCommand = (RecipeCommand) source;
            return convertToRecipe(recipeCommand);
        } else {
            throw new IllegalArgumentException("Illegal SourceType only following instances are allowed for conversion."
                    + Recipe.class.getName() + " || "
                    + RecipeCommand.class.getName());
        }
    }

    private Recipe convertToRecipe(RecipeCommand recipeCommand) {
        final Recipe recipe = new Recipe();

        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        if (recipe.getDifficulity() != null) {
            recipe.setDifficulity(conversionService.convert(recipe.getDifficulity(), Difficulity.class));
        }
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDirections(recipeCommand.getDirections());

        if (recipeCommand.getNotes() != null) {
            recipe.setNotes(conversionService.convert(recipeCommand.getNotes(), Notes.class));
        }

        Set<Category> categories = new HashSet<>();
        if (recipeCommand.getCategories() != null && !recipeCommand.getCategories().isEmpty()) {
            recipeCommand.getCategories().forEach(categoryCommand -> {
                categories.add(conversionService.convert(categoryCommand, Category.class));
            });
        }
        recipe.setCategories(categories);

        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setImage(recipeCommand.getImage());
        recipe.setServings(recipeCommand.getServings());

        Set<Ingredient> ingredients = new HashSet<>();
        if (recipeCommand.getIngredients() != null && !recipeCommand.getIngredients().isEmpty()) {
            recipeCommand.getIngredients().forEach(ingredientCommand -> {
                ingredients.add(conversionService.convert(ingredientCommand, Ingredient.class));
            });
        }
        recipe.setIngredients(ingredients);

        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        return recipe;
    }

    private RecipeCommand convertToRecipeCommand(Recipe recipe) {
        {
            final RecipeCommand recipeCommand = new RecipeCommand();

            recipeCommand.setId(recipe.getId());
            recipeCommand.setDescription(recipe.getDescription());

            if (recipe.getDifficulity() != null) {
                recipeCommand.setDifficulity(conversionService.convert(recipe.getDifficulity(), Difficulity.class));
            }
            recipeCommand.setCookTime(recipe.getCookTime());
            recipeCommand.setDirections(recipe.getDirections());

            if (recipe.getNotes() != null) {
                recipeCommand.setNotes(conversionService.convert(recipe.getNotes(), NotesCommand.class));
            }

            Set<CategoryCommand> categorieCommands = new HashSet<>();

            if (recipe.getCategories() != null && !recipe.getCategories().isEmpty()) {
                recipe.getCategories().forEach(category -> {
                    categorieCommands.add(conversionService.convert(category, CategoryCommand.class));
                });
            }
            recipeCommand.setCategories(categorieCommands);

            recipeCommand.setPrepTime(recipe.getPrepTime());
            recipeCommand.setImage(recipe.getImage());
            recipeCommand.setServings(recipe.getServings());

            Set<IngredientCommand> ingredientCommands = new HashSet<>();
            if (recipe.getIngredients() != null) {
                recipe.getIngredients().forEach(ingredient -> {
                    ingredientCommands.add(conversionService.convert(ingredient, IngredientCommand.class));
                });
            }
            recipeCommand.setIngredients(ingredientCommands);

            recipeCommand.setSource(recipe.getSource());
            recipeCommand.setUrl(recipe.getUrl());
            return recipeCommand;
        }
    }
}
