package guru.springboot.springrecipeapp.commands;

import guru.springboot.springrecipeapp.enums.Difficulity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<CategoryCommand> categories;
    private Difficulity difficulity;
    private Set<IngredientCommand> ingredients;
    private Byte[] image;
    private NotesCommand notes;

}
