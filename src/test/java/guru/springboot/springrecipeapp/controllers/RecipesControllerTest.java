package guru.springboot.springrecipeapp.controllers;

import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.exceptions.RecipeExceptionHandler;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class RecipesControllerTest {

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    @Mock
    Model model;

    @InjectMocks
    RecipesController viewRecipesController;

    private final Long id = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(viewRecipesController)
                .setControllerAdvice(RecipeExceptionHandler.class).build();
    }

    @Test
    public void viewRecipeTest() {
        try {
            Recipe recipe = new Recipe();
            recipe.setId(id);

            when(recipeService.findById(anyLong())).thenReturn(recipe);

            mockMvc.perform(get("/recipe/1/view-recipe"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/recipe/view-recipe"))
                    .andExpect(model().attribute("recipe", recipe));

            //verify(model, times(1)).addAttribute(eq);
        } catch (Exception e) {
            log.error("Exception Occured in viewRecipeTest" + e.getMessage());
        }
    }

    @Test
    public void updateRecipe() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(id);
        recipeCommand.setDescription("Test Description");

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update-recipe"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("/recipe/update-recipe"));
    }

    @Test
    public void saveOrupdate() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(id);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe/save-recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some String")
                .param("prepTime", "1")
                .param("cookTime", "1")
                .param("servings", "1"))

                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/view-recipe"));
    }

    @Test
    public void deleteRecipe() throws Exception {

        viewRecipesController.deleteRecipe("1");

        verify(recipeService, times(1)).deleteById(anyLong());

        mockMvc.perform(get("/recipe/1/delete-recipe"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }


    @Test
    public void exceptionRecipe404Test() throws Exception {

        RecipeCommand recipeCommand = null;

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update-recipe"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void exceptionRecipe400Test() throws Exception {

        mockMvc.perform(get("/recipe/qwqwq/update-recipe"))
                .andExpect(status().isBadRequest());
    }
}