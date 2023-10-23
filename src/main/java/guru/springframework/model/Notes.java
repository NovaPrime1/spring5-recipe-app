package guru.springframework.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    // No Cascading strategy because notes can be deleted but not the recipe
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
