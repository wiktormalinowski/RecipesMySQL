package pl.maliniak.recipesmysql.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.maliniak.recipesmysql.entities.Recipe;
import pl.maliniak.recipesmysql.entities.User;
import pl.maliniak.recipesmysql.entities.UserDetailsImpl;
import pl.maliniak.recipesmysql.services.RecipeService;
import pl.maliniak.recipesmysql.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    private final UserService userService;


    @GetMapping(value = "/api/recipe/{id}")
    public ResponseEntity getRecipe(@PathVariable("id") Long id) {
        if (!recipeService.exist(id)) {
            return new ResponseEntity<>("404 (Not found)", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipeService.findRecipeById(id), HttpStatus.OK);

    }

    @PostMapping(value = "/api/recipe/new")
    public ResponseEntity saveRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @Valid @RequestBody Recipe recipe) {
        if (recipe.getDirections().isEmpty() || recipe.getIngredients().isEmpty()) {
            return new ResponseEntity<>("400 (Bad Request)", HttpStatus.BAD_REQUEST);
        }
        recipe.setUser(userService.findUserByEmail(userDetails.getUsername()));
        recipeService.save(recipe);
        return new ResponseEntity<>(Map.of("id: ", recipe.getId()), HttpStatus.OK);
    }

    @PutMapping(value = "/api/recipe/{id}")
    public ResponseEntity<String> updateRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @Valid @RequestBody Recipe recipe, @PathVariable Long id) {

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
        oldRecipe.setUser(userService.findUserByEmail(userDetails.getUsername()));
        recipeService.save(oldRecipe);
        return new ResponseEntity<>("204 (No Content)", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @PathVariable("id") Long id) {
        if (!recipeService.exist(id)) {
            return new ResponseEntity<>("404 (Not found)", HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(recipeService.findRecipeById(id).getUser().getEmail(), userDetails.getUsername())) {
            return new ResponseEntity<>("403 Forbidden", HttpStatus.FORBIDDEN);
        }
        recipeService.delete(recipeService.findRecipeById(id));
        return new ResponseEntity<>("204 (No Content)", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "api/recipe/search", params = "name")
    public ResponseEntity<List<Recipe>> findRecipeByName(@RequestParam("name") String name) {
        List<Recipe> foundRecipes = recipeService.findRecipesByName(name);
        return new ResponseEntity<>(foundRecipes, HttpStatus.OK);
    }

    @GetMapping(value = "api/recipe/search", params = "category")
    public ResponseEntity<List<Recipe>> findRecipeByCategory(@RequestParam("category") String category) {
        List<Recipe> foundRecipes = recipeService.findRecipesByCategory(category);
        return new ResponseEntity<>(foundRecipes, HttpStatus.OK);
    }


}
