package guru.springboot.springrecipeapp.controllers;


import guru.springboot.springrecipeapp.commands.IngredientCommand;
import guru.springboot.springrecipeapp.commands.RecipeCommand;
import guru.springboot.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springboot.springrecipeapp.services.interfaces.IngredientService;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import guru.springboot.springrecipeapp.services.interfaces.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/recipe")
@Slf4j
public class IngredientController {

    RecipeService recipeService;

    IngredientService ingredientService;

    UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping("/{recipeId}/update-ingredients/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model theModel) {


        IngredientCommand ingredientCommand = ingredientService.findCommandById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        theModel.addAttribute("ingredient", ingredientCommand);

        Set<UnitOfMeasureCommand>  unitOfMeasureCommands= unitOfMeasureService.findAll();

        theModel.addAttribute("uomList",unitOfMeasureCommands );

        return "/ingredients/update-ingredient";
    }

    @PostMapping("/{recipeId}/save-ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command, @PathVariable String recipeId){

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(Long.valueOf(recipeId));
        command.setRecipe(recipeCommand);
        IngredientCommand ingredientCommand = ingredientService.saveIngredientCommand(command);

        String savedRecipeId = ingredientCommand.getRecipe().getId().toString();
        String ingredientId = ingredientCommand.getId().toString();

        log.info("Ingredient saved..recipeId :" + recipeId + "IngridentId.." + ingredientId);

        return "redirect:/recipe/" + savedRecipeId + "/view-ingredients/" + ingredientId + "/view";
    }

    @GetMapping("/{recipeId}/new-ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model theModel ){

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipe(recipeCommand);
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        theModel.addAttribute("ingredient", ingredientCommand);

        Set<UnitOfMeasureCommand>  unitOfMeasureCommands= unitOfMeasureService.findAll();

        theModel.addAttribute("uomList",unitOfMeasureCommands );

        return "/ingredients/update-ingredient";
    }

    @GetMapping("/{recipeId}/delete-ingredient/{ingredientId}/delete")
    public String deleteRecipe(@PathVariable String recipeId, @PathVariable String ingredientId){
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
