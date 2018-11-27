package guru.springboot.springrecipeapp.repositories;

import guru.springboot.springrecipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {


    Optional<Recipe> findByDescription(String description);
}
