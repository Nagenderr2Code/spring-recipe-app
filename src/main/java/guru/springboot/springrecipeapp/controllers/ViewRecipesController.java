package guru.springboot.springrecipeapp.controllers;

import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewRecipesController {

    private RecipeService recipeService;

    public ViewRecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/view-recipe/{id}")
    public String viewRecipe(@PathVariable String id, Model theModel) {

        Recipe recipe = recipeService.findById(new Long(id));

        theModel.addAttribute("recipe", recipe);

        return "/recipe/view-recipe";
    }
}
