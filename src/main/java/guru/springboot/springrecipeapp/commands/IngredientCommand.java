package guru.springboot.springrecipeapp.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal quantity;
    private UnitOfMeasureCommand uom;
    private RecipeCommand recipe;
}
