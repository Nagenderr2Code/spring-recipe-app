package guru.springboot.springrecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class CategoryCommand {

    private Long id;
    private String description;
    private Set<RecipeCommand> recipes;
}
