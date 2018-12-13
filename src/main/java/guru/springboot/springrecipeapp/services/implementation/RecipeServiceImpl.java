package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    ConversionService conversionService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ConversionService conversionService) {
        this.recipeRepository = recipeRepository;
        this.conversionService = conversionService;
    }

    @Override
    public Set<Recipe> findAll() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (!recipe.isPresent()) {
            throw new RuntimeException("Recipe Not Found for the Id.." + id);
        }

        return recipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {

        Recipe recipe = conversionService.convert(command, Recipe.class);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return conversionService.convert(savedRecipe, RecipeCommand.class);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {

        Recipe recipe = recipeRepository.findById(id).get();

        if (recipe == null) {
            throw new RuntimeException("Recipe not found with ID.." + id);
        }
        return conversionService.convert(recipe, RecipeCommand.class);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

}
