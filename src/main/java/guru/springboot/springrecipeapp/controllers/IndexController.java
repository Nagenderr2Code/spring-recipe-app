package guru.springboot.springrecipeapp.controllers;


import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model theModel) {

        theModel.addAttribute("recipes", recipeService.findAll());
        return "index";
    }

}
