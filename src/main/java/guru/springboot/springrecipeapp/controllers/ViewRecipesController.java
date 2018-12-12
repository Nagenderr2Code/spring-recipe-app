package guru.springboot.springrecipeapp.controllers;

import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipe")
public class ViewRecipesController {

    private RecipeService recipeService;

    public ViewRecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("{id}/view-recipe")
    public String viewRecipe(@PathVariable String id, Model theModel) {

        Recipe recipe = recipeService.findById(new Long(id));

        theModel.addAttribute("recipe", recipe);

        return "/recipe/view-recipe";
    }

    @GetMapping("{id}/update-recipe")
    public String updateRecipe(@PathVariable String id, Model theModel) {

        RecipeCommand recipeCommand = recipeService.findCommandById(new Long(id));

        theModel.addAttribute("recipe", recipeCommand);

        return "/recipe/update-recipe";
    }

    @PostMapping("/save-recipe")
    public String saveOrupdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "/recipe/" + savedRecipeCommand.getId() + "redirect:/view-recipe";
    }
}
