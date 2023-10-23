package guru.springframework.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private BigDecimal amount;


    @OneToOne( fetch = FetchType.EAGER) // Handy to show the intent. Retrieved everytime
    // Here is the unidirectional relationship to Ingredient
    private UnitOfMeasure uom;


    @ManyToOne
    // No cascading type here. default action is none.
    private Recipe recipe;

    public Ingredient() {
    }

    // Added a helper constructor for this one so I can take in ingredient
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }


    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

}
