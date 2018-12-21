package guru.springboot.springrecipeapp.converter;

import com.google.common.collect.ImmutableSet;
import guru.springboot.springrecipeapp.commands.CategoryCommand;
import guru.springboot.springrecipeapp.domain.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class CategoryConverter implements GenericConverter {


    ConversionService conversionService;

    public CategoryConverter() {
    }

    public CategoryConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(CategoryCommand.class, Category.class),
                new ConvertiblePair(Category.class, CategoryCommand.class)
        };
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object source, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {

        if (source == null) {
            throw new RuntimeException("Source can not be null for the conversion.");
        }

        if (source instanceof Category) {
            log.info("Converting the from category to categoryCommand..");
            Category category = (Category) source;
            CategoryCommand categoryCommand= convertToCategoryCommand(category);
            return categoryCommand;
        } else if (source instanceof CategoryCommand) {
            log.info("Converting the from categoryCommand to category..");
            CategoryCommand categoryCommand = (CategoryCommand) source;
            return convertToCategory(categoryCommand);
        } else {
            throw new IllegalArgumentException("Source can only be one of these type.."
                    + Category.class.getName() + " || "
                    + CategoryCommand.class.getName());
        }
    }

    public CategoryCommand convertToCategoryCommand(Category category) {
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        /**Set<RecipeCommand> recipeCommands = new HashSet<>();
        if(category.getRecipes() != null && !category.getRecipes().isEmpty()) {
            category.getRecipes().forEach(recipe -> {
                recipeCommands.add(conversionService.convert(recipe, RecipeCommand.class));
            });
        }

        categoryCommand.setRecipes(recipeCommands);*/
        return categoryCommand;
    }

    public Category convertToCategory(CategoryCommand categoryCommand) {
        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());

        /**Set<Recipe> recipes = new HashSet<>();
        if(categoryCommand.getRecipes() != null && !categoryCommand.getRecipes().isEmpty()) {
            categoryCommand.getRecipes().forEach(recipeCommand -> {
                recipes.add(conversionService.convert(recipeCommand, Recipe.class));
            });
         category.setRecipes(recipes);
         }*/


        return category;
    }
}
