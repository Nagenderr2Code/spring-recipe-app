package guru.springboot.springrecipeapp.controllers;

import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class ViewRecipesControllerTest {

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    @Mock
    Model model;

    @InjectMocks
    ViewRecipesController viewRecipesController;

    private Recipe recipe;

    private final Long id = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipe = new Recipe();
        recipe.setId(id);
        mockMvc = MockMvcBuilders.standaloneSetup(viewRecipesController).build();
    }

    @Test
    public void viewRecipeTest() {
        try {

            when(recipeService.findById(anyLong())).thenReturn(recipe);

            mockMvc.perform(get("/recipe/view-recipe/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("/recipe/view-recipe"))
                    .andExpect(model().attribute("recipe", recipe));

            //verify(model, times(1)).addAttribute(eq);
        } catch (Exception e) {
            log.error("Exception Occured in viewRecipeTest" + e.getMessage());
        }
    }
}