package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.domain.Ingredient;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import guru.springboot.springrecipeapp.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    ConversionService conversionService;

    RecipeRepository recipeRepository;

    public IngredientServiceImpl(ConversionService conversionService, RecipeRepository recipeRepository) {
        this.conversionService = conversionService;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Ingredient> findAll() {
        return null;
    }

    @Override
    public Ingredient findById(Long id) {
        return null;
    }

    @Override
    public IngredientCommand saveRecipeCommand(IngredientCommand command) {
        return null;
    }

    @Override
    public IngredientCommand findCommandById(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (!recipe.isPresent()) {
            log.error("Recipe not found for the given Id.." + recipeId);
            throw new RuntimeException("Recipe not found for the given Id.." + recipeId);
        }

        Optional<IngredientCommand> ingredientCommand = recipe.get().getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> conversionService.convert(ingredient, IngredientCommand.class)).findFirst();

        if (!ingredientCommand.isPresent()) {
            log.error("Ingredient not found for the given Id.." + ingredientId);
            throw new RuntimeException("Ingredient not found for the given Id.." + ingredientId);
        }
        return ingredientCommand.get();
    }

    @Override
    public void deleteById(Long idToDelete) {

    }
}
