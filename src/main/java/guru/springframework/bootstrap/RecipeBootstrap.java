package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener <ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());

    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(3);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found - Each");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found - tablespoon");
        }
        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found - Teaspoon");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found - cup");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if(!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found - pinch");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");

        if(!ounceUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found - ounce");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found - pint");
        }

        UnitOfMeasure eachUom = tablespoonUomOptional.get();
        UnitOfMeasure tableSpoonUoM = tablespoonUomOptional.get();
        UnitOfMeasure teaSpoonUoM = tablespoonUomOptional.get();
        UnitOfMeasure cupUoM = tablespoonUomOptional.get();
        UnitOfMeasure pinchUoM = tablespoonUomOptional.get();
        UnitOfMeasure ounceUoM = tablespoonUomOptional.get();
        UnitOfMeasure pintUoM = tablespoonUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americaCatagory = americanCategoryOptional.get();
        Category mexicanCatagory =mexicanCategoryOptional.get();


        Recipe burgerRecipe = new Recipe();
        burgerRecipe.setDescription("American Burger");
        burgerRecipe.setPrepTime(5);
        burgerRecipe.setCookTime(12);
        burgerRecipe.setDifficulty(Difficulty.EASY);
        burgerRecipe.setDirections("1 Cut some onion slices, cut tomato slices. Get some ground beef. Pat beef to a flat patty with a hands after washing: Add season salt, cook for 12 minutes and make a sandwich");
//                                   "2 Cover and cook for 12 minutes: Flip burger on the other side to allow it to cook on both sides It is ready when the patty turn brown from a pick color on both sides\n" +
//                                   "3 After cook take and let it cool for 1 minute. Get a bun and add mustard, mayo, onion, lettuces and optional tomato, put burger between bun with other item and it is ready to server.");

        Notes burgerNotes = new Notes();
        burgerNotes.setRecipeNotes(" For a quick burger, cook beef patty as above and just add cheese, mustard and mayo and ready to serve");
        burgerNotes.setRecipe(burgerRecipe);
        burgerRecipe.setNotes(burgerNotes);

        burgerRecipe.getIngredients().add(new Ingredient("fresh onion", new BigDecimal(.1), pinchUoM, burgerRecipe));
        burgerRecipe.getIngredients().add(new Ingredient("lettuces ", new BigDecimal(.1), eachUom, burgerRecipe));
        burgerRecipe.getIngredients().add(new Ingredient("tomato ", new BigDecimal(.25), eachUom, burgerRecipe));
        burgerRecipe.getIngredients().add(new Ingredient("beef patty ", new BigDecimal(.25), teaSpoonUoM, burgerRecipe));
        burgerRecipe.getIngredients().add(new Ingredient("Mayo ", new BigDecimal(.05), teaSpoonUoM, burgerRecipe));

        burgerRecipe.getCategories().add(americaCatagory);

        recipes.add(burgerRecipe);

        return recipes;

    }

}
