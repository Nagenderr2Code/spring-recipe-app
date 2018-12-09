package guru.springboot.springrecipeapp;


import guru.springboot.springrecipeapp.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class RecipeAppConfig {


    DefaultConversionService conversionService;


    @Bean
    public DefaultConversionService addConversionService() {
        conversionService = new DefaultConversionService();
        conversionService.addConverter(new RecipeConverter());
        conversionService.addConverter(new DifficulityConverter.StringToEnumConverter<>());
        conversionService.addConverter(new CategoryConverter());
        conversionService.addConverter(new UnitOfMeasureConverter());
        conversionService.addConverter(new IngredientConverter());
        conversionService.addConverter(new NotesConverter());

        return conversionService;
    }
}
