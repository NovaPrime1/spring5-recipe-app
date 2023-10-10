package guru.springframework.services;

import guru.springframework.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientService extends CrudRepository <Ingredient, Long> {
}
