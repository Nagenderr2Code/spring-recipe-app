package guru.springboot.springrecipeapp.controllers;


import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.services.interfaces.IngredientService;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class IngredientController {

    RecipeService recipeService;

    IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model theModel) {

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        theModel.addAttribute("recipe", recipeCommand);
        return "/ingredients/view-ingredients";
    }


    @GetMapping("/{recipeId}/view-ingredients/{ingredientId}/view")
    public String viewIngredients(@PathVariable String recipeId, @PathVariable String ingredientId, Model theModel) {

        IngredientCommand ingredientCommand = ingredientService.findCommandById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        theModel.addAttribute("ingredient", ingredientCommand);
        return "/ingredients/view-ingredient";
    }
}
