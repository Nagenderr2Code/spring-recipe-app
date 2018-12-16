package guru.springboot.springrecipeapp.controllers;

import guru.springboot.springrecipeapp.domain.Recipe;
import guru.springboot.springrecipeapp.services.interfaces.ImageService;
import guru.springboot.springrecipeapp.services.interfaces.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    ImageService imageService;

    @InjectMocks
    ImageController imageController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void getImageForm() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("/recipe/image-upload"));
    }


    @Test
    public void handleImage() throws Exception {

        MockMultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt", "text/plain", "Spring framework".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/view-recipe"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }
}