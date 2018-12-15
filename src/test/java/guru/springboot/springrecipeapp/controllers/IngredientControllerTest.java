package guru.springboot.springrecipeapp.controllers;

import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.services.interfaces.IngredientService;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import guru.springboot.springrecipeapp.services.interfaces.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {


    @InjectMocks
    IngredientController ingredientController;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    Model model;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void listIngredients() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        ingredientCommands.add(ingredientCommand);
        recipeCommand.setIngredients(ingredientCommands);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("/ingredients/view-ingredients"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void viewIngredients() throws Exception {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        when(ingredientService.findCommandById(anyLong(), anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/view-ingredients/1/view"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(view().name("/ingredients/view-ingredient"));

    }

    @Test
    public void updateIngredient() throws Exception {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        when(ingredientService.findCommandById(anyLong(), anyLong())).thenReturn(ingredientCommand);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(1L);

        Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet<>();
        unitOfMeasureCommands.add(unitOfMeasureCommand);

        when(unitOfMeasureService.findAll()).thenReturn(unitOfMeasureCommands);

        assertFalse(unitOfMeasureService.findAll().isEmpty());

        mockMvc.perform(get("/recipe/1/update-ingredients/1/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(view().name("/ingredients/update-ingredient"));

    }

    @Test
    public void saveOrUpdate() throws Exception {

        IngredientCommand command = new IngredientCommand();
        command.setId(1L);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        command.setRecipe(recipeCommand);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe/1/save-ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/view-ingredients/1/view"));
    }
}