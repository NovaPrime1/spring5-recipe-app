package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>(); // From recipe to ingredient one of a two way relationship also initializing to a new hashset to avoid a NPE

    @Lob // Large object field - Binary object field
    private Byte[] image;

    @Enumerated(value = EnumType.STRING) // Overriding the default
    private Difficulty difficulty;

    // This is here because of the 1:1 relationship with Notes class.
    @OneToOne(cascade =  CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
      joinColumns = @JoinColumn(name ="recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();  // Same as above initializing to avoid a NPE ( little trick just in case you forget to set it.


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
