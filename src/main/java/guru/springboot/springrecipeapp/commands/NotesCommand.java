package guru.springboot.springrecipeapp.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NotesCommand {

    private Long id;
    private String recipeNotes;
    private RecipeCommand recipe;

}
