package guru.springboot.springrecipeapp.controllers;


import guru.springboot.springrecipeapp.domain.Category;
import guru.springboot.springrecipeapp.domain.UnitOfMeasure;
import guru.springboot.springrecipeapp.repositories.CategoryRepository;
import guru.springboot.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller

public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {

        Optional<Category> category = categoryRepository.findByDescription("Italian");
        System.out.println("category from H2.." + category.get().getId());

        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Tablespoon");
        System.out.println("Unit of Measure ID :" + uom.get().getId());
        return "index";
    }

}
