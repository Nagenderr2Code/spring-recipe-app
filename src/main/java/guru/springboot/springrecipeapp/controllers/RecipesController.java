package guru.springboot.springrecipeapp.controllers;

import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.exceptions.NotFoundException;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipesController {

    private RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/{id}/view-recipe")
    public String viewRecipe(@PathVariable String id, Model theModel) {

        Recipe recipe = recipeService.findById(new Long(id));

        theModel.addAttribute("recipe", recipe);

        return "/recipe/view-recipe";
    }

    @GetMapping("/{id}/update-recipe")
    public String updateRecipe(@PathVariable String id, Model theModel) throws Exception {

        RecipeCommand recipeCommand = recipeService.findCommandById(new Long(id));

        if (recipeCommand == null) {
            throw new NotFoundException("Recipe Response is null.." + id);
        }
        theModel.addAttribute("recipe", recipeCommand);

        return "/recipe/update-recipe";
    }

    @PostMapping("/save-recipe")
    public String saveOrupdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult theResult) {

        if(theResult.hasErrors()){
            return "/recipe/update-recipe";
        }
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/view-recipe";
    }

    @GetMapping("/{id}/delete-recipe")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
