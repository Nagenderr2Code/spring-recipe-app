package guru.springboot.springrecipeapp.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal quantity;
    private UnitOfMeasureCommand uom;
    private RecipeCommand recipe;
}
