package guru.springboot.springrecipeapp.services.interfaces;

import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.domain.Ingredient;

import java.util.Set;

public interface IngredientService {

    Set<Ingredient> findAll();

    Ingredient findById(Long id);

    IngredientCommand saveRecipeCommand(IngredientCommand command);

    IngredientCommand findCommandById(Long recipeId, Long ingredientId);

    void deleteById(Long idToDelete);
}