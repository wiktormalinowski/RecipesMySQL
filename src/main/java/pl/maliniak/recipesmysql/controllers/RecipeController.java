package pl.maliniak.recipesmysql.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.maliniak.recipesmysql.entities.Recipe;
import pl.maliniak.recipesmysql.services.RecipeService;
import pl.maliniak.recipesmysql.entities.UserDetailsImpl;
import pl.maliniak.recipesmysql.repository.UserRepo;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    UserRepo userRepo;


    @GetMapping(value = "/api/recipe/{id}")
    public ResponseEntity getRecipe(@PathVariable("id") Long id) {
        if (!recipeService.exist(id)) {
            return new ResponseEntity<>("404 (Not found)", HttpStatus.NOT_FOUND);
        }




        return new ResponseEntity<>(recipeService.findRecipeById(id), HttpStatus.OK);




    }
    @PostMapping(value = "/api/recipe/new")
    public ResponseEntity saveRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody Recipe recipe) {
        log.info("działa");
        if (recipe.getDirections().size() < 1 || recipe.getIngredients().size() < 1) {
            return new ResponseEntity<>("400 (Bad Request)", HttpStatus.BAD_REQUEST);
        }
        recipe.setUser(userRepo.findUserByEmail(userDetails.getUsername()));
        recipeService.save(recipe);
        return new ResponseEntity<>(Map.of("id", recipe.getId()), HttpStatus.OK);
    }

    @PutMapping(value = "/api/recipe/{id}")
    public ResponseEntity updateRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody Recipe recipe, @PathVariable Long id) {
        if (recipeService.count() < id || !recipeService.exist(id)) {
            return new ResponseEntity<>("404 (Not found)", HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(recipeService.findRecipeById(id).getUser().getEmail(), userDetails.getUsername())) {
            return new ResponseEntity<>("403 Forbidden", HttpStatus.FORBIDDEN);
        }
        Recipe oldRecipe = recipeService.findRecipeById(id);
        Long oldID = oldRecipe.getId();
        oldRecipe = recipe;
        oldRecipe.setId(oldID);
        oldRecipe.setUser(userRepo.findUserByEmail(userDetails.getUsername()));
        recipeService.save(oldRecipe);
        return new ResponseEntity<>("204 (No Content)", HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(value = "api/recipe/{id}")
    public ResponseEntity deleteRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id) {
        if (!recipeService.exist(id)) {
            return new ResponseEntity<>("404 (Not found)", HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(recipeService.findRecipeById(id).getUser().getEmail(), userDetails.getUsername())) {
            return new ResponseEntity<>("403 Forbidden", HttpStatus.FORBIDDEN);
        }
        recipeService.delete(recipeService.findRecipeById(id));
        return new ResponseEntity<>("204 (No Content)", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "api/recipe/search/", params = "name")
    public ResponseEntity findRecipeByName(@RequestParam("name") String name) {
        List<Recipe> foundRecipes = recipeService.findRecipesByName(name);
        return new ResponseEntity<>(foundRecipes, HttpStatus.OK);
    }
    @GetMapping(value = "api/recipe/search/", params = "category")
    public ResponseEntity findRecipeByCategory(@RequestParam("category") String category) {
        List<Recipe> foundRecipes = recipeService.findRecipesByCategory(category);
        return new ResponseEntity<>(foundRecipes, HttpStatus.OK);
    }


}
