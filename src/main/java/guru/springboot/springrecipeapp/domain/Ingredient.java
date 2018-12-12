package guru.springboot.springrecipeapp.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal quantity, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.quantity = quantity;
        this.uom = uom;
        this.recipe = recipe;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal quantity;

    @OneToOne
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

}
