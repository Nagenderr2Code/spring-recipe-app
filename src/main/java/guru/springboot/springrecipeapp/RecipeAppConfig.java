package guru.springboot.springrecipeapp;


import guru.springboot.springrecipeapp.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RecipeAppConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean(name = "conversionService")
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new IngredientConverter(conversionService));
        conversionService.addConverter(new NotesConverter(conversionService));
        conversionService.addConverter(new UnitOfMeasureConverter(conversionService));
        conversionService.addConverter(new CategoryConverter(conversionService));
        conversionService.addConverter(new RecipeConverter(conversionService));
        return conversionService;
    }
}
