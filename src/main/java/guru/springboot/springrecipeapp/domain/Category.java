package guru.springboot.springrecipeapp.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"recipes"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();

}
