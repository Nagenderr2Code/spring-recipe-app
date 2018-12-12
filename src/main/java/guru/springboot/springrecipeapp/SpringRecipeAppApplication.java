package guru.springboot.springrecipeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RecipeAppConfig.class)
public class SpringRecipeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRecipeAppApplication.class, args);
    }
}
