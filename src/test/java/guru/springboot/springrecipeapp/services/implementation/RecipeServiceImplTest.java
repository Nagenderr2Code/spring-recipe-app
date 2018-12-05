package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    private RecipeService recipeService;
    private final Long id= 1L;
    HashSet recipeSet = new HashSet();
    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);

        recipe = new Recipe();
        recipe.setId(id);
        recipeSet.add(recipe);
    }

    @Test
    public void findAll() {

        when(recipeRepository.findAll()).thenReturn(recipeSet);

        Set<Recipe> recipes = recipeService.findAll();

        verify(recipeRepository, times(1)).findAll();

        assertEquals(recipes.size(), 1);
    }

    @Test
    public void findById(){

        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        Recipe recipe = recipeService.findById(id);

        verify(recipeRepository, times(1)).findById(anyLong());

        assertEquals(id, recipe.getId());
    }
}