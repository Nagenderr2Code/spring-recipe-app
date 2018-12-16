package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import guru.springboot.springrecipeapp.services.interfaces.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    RecipeRepository recipeRepository;

    ConversionService conversionService;

    public ImageServiceImpl(RecipeRepository recipeRepository, ConversionService conversionService) {
        this.recipeRepository = recipeRepository;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.info("recived file");
        try {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

            Recipe recipe = optionalRecipe.get();
            Byte[] bytes = new Byte[file.getBytes().length];

            bytes=conversionService.convert(file.getBytes(), Byte[].class);

            recipe.setImage(bytes);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error saving the Image..");
        }

    }
}
