package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService; // now you are calling the service as you are not call the repos directly.

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

//    private CategoryRepository categoryRepository;
//    private UnitOfMeasureRepository unitOfMeasureRepository;

//    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureRepository = unitOfMeasureRepository;
//    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
     // Note the string has to represent the set of things "recipes" that is noted in the HTML.
    model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
