package guru.springboot.springrecipeapp.converter;

import com.google.common.collect.ImmutableSet;
import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.domain.Ingredient;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

@Slf4j
public class IngredientConverter implements GenericConverter {

    ConversionService conversionService;

    public IngredientConverter() {
    }

    public IngredientConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(Ingredient.class, IngredientCommand.class),
                new ConvertiblePair(IngredientCommand.class, Ingredient.class)
        };
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {

        if (source == null) {
            throw new RuntimeException("Source Object Can not be null..");
        }

        if (source instanceof Ingredient) {
            log.info("Converting Ingredient to IngredientCommand..");
            Ingredient ingredient = (Ingredient) source;
            return convertToIngredientCommand(ingredient);
        } else if (source instanceof IngredientCommand) {
            log.info("Converting IngredientCommand to Ingredient..");
            IngredientCommand ingredientCommand = (IngredientCommand) source;
            return convertToIngredient(ingredientCommand);
        } else {
            throw new IllegalArgumentException("Conversion Service only supports these Types." +
                    Ingredient.class.getName() + " || " + IngredientCommand.class.getName());
        }
    }

    private IngredientCommand convertToIngredientCommand(Ingredient ingredient) {

        final IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setQuantity(ingredient.getQuantity());

        if (ingredient.getUom() != null) {
            ingredientCommand.setUom(conversionService.convert(ingredient.getUom(), UnitOfMeasureCommand.class));
        }

        if (ingredient.getRecipe() != null) {
            ingredientCommand.setRecipe(conversionService.convert(ingredient.getRecipe(), RecipeCommand.class));
        }

        return ingredientCommand;
    }

    private Ingredient convertToIngredient(IngredientCommand ingredientCommand) {

        final Ingredient ingredient = new Ingredient();

        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setQuantity(ingredientCommand.getQuantity());

        if (ingredientCommand.getUom() != null) {
            ingredient.setUom(conversionService.convert(ingredientCommand.getUom(), UnitOfMeasure.class));
        }

        if (ingredientCommand.getRecipe() != null) {
            ingredient.setRecipe(conversionService.convert(ingredientCommand.getRecipe(), Recipe.class));
        }

        return ingredient;
    }
}
