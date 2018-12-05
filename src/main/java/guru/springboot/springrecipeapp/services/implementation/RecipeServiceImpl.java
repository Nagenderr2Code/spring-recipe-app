package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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

        if(!recipe.isPresent()){
            throw new RuntimeException("Recipe Not Found for the Id.." + id);
        }

        return recipe.get();
    }
}
