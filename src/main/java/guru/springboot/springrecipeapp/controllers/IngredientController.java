package guru.springboot.springrecipeapp.controllers;


import guru.springboot.springrecipeapp.commands.RecipeCommand;
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

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model theModel) {

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        theModel.addAttribute("recipe", recipeCommand);
        return "/ingredients/view-ingredients";
    }
}
