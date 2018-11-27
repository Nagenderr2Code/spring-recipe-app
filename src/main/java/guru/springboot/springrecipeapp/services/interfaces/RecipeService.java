package guru.springboot.springrecipeapp.services.interfaces;

import guru.springboot.springrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> findAll();
}
