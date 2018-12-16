package guru.springboot.springrecipeapp.services.implementation;

import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @InjectMocks
    ImageServiceImpl imageService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    ConversionService conversionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveImageFile() throws IOException {

        Long id= 1L;
        MultipartFile multipartFile= new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring Framework".getBytes());

        Recipe recipe= new Recipe();
        recipe.setId(id);

        ArgumentCaptor<Recipe>  argumentCaptor= ArgumentCaptor.forClass(Recipe.class);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Byte[] bytes = new Byte[multipartFile.getBytes().length];
        int i = 0;
         for (byte b : multipartFile.getBytes()) {
         bytes[i++] = b;
         }

        when(conversionService.convert(multipartFile.getBytes(), Byte[].class)).thenReturn(bytes);

        imageService.saveImageFile(id, multipartFile);

        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);


    }
}