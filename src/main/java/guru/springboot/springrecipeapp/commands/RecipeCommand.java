package guru.springboot.springrecipeapp.commands;

import guru.springboot.springrecipeapp.enums.Difficulity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

    @NotBlank
    @Size(min= 3, max = 255)
    private String description;

    @Min(1)
    @Max(999)
    @NotNull
    private Integer prepTime;

    @Min(1)
    @Max(999)
    @NotNull
    private Integer cookTime;

    @Min(1)
    @Max(999)
    @NotNull
    private Integer servings;
    private String source;

    @URL
    private String url;
    private String directions;
    private Set<CategoryCommand> categories;
    private Difficulity difficulity;
    private Set<IngredientCommand> ingredients;
    private byte[] image;
    private NotesCommand notes;

}
