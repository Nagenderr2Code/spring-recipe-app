package guru.springboot.springrecipeapp.controllers;


import guru.springboot.springrecipeapp.services.interfaces.ImageService;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class ImageController {

    ImageService imageService;

    RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/image")
    public String getImageForm(@PathVariable String id, Model theModel) {

        theModel.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "/recipe/image-upload";
    }

    @PostMapping("/{id}/image")
    public String handleImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" +id + "/view-recipe";
    }


}
