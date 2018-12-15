package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Ingredient;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import guru.springboot.springrecipeapp.services.interfaces.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        if(command.getRecipe() == null || command.getRecipe().getId() == null){
            log.error("Recipe Can not be null for the given .." );
            throw new RuntimeException("Recipe not found for the given Id..");
        }

        Long recipeId = command.getRecipe().getId();
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if (!recipe.isPresent()) {
            log.error("Recipe not found for the given Id.." + recipeId);
            throw new RuntimeException("Recipe not found for the given Id.." + recipeId);
        }

        Optional<Ingredient> ingredientOptional = recipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

        Ingredient ingredient= null;

        if (!ingredientOptional.isPresent()) {
            ingredient= new Ingredient();
            recipe.get().getIngredients().add(ingredient);
        }else{
            ingredient = ingredientOptional.get();
        }

        ingredient.setId(command.getId());
        ingredient.setRecipe(recipe.get());
        ingredient.setQuantity(command.getQuantity());
        if(command.getUom() != null){
            ingredient.setUom(conversionService.convert(command.getUom(), UnitOfMeasure.class));
        }
        ingredient.setDescription(command.getDescription());

        Recipe savedRecipe = recipeRepository.save(recipe.get());

        IngredientCommand ingredientCommand= null;
        if (ingredientOptional.isPresent() && command.getId() != null) {
            ingredientCommand = conversionService.convert(savedRecipe.getIngredients().stream()
                    .filter(savedIngredient -> savedIngredient.getId().equals(command.getId())).findFirst().get(), IngredientCommand.class);
        }else{
            ingredientCommand = conversionService.convert(savedRecipe.getIngredients().stream()
                    .filter(savedIngredient ->
                        savedIngredient.getDescription().equalsIgnoreCase(command.getDescription()) &&
                        savedIngredient.getQuantity().equals(command.getQuantity()) &&
                        savedIngredient.getUom().getId().equals(command.getUom().getId())
                    ).findFirst().get(), IngredientCommand.class);
        }
        ingredientCommand.setRecipe(conversionService.convert(savedRecipe, RecipeCommand.class));

        return ingredientCommand;
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
        RecipeCommand recipeCommand = conversionService.convert(recipe.get(), RecipeCommand.class);
        ingredientCommand.get().setRecipe(recipeCommand);
        return ingredientCommand.get();
    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        Optional<Ingredient> ingredientOptional = recipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();

        Ingredient ingredient = null;
        if(ingredientOptional.isPresent()){
            ingredient= ingredientOptional.get();
            ingredient.setRecipe(null);
            recipe.get().getIngredients().remove(ingredient);
            recipeRepository.save(recipe.get());
            log.info("Ingrident Sucessfully Deleted");
        }else{
            log.info("Ingrident does not exist to Delete");
        }

    }
}
