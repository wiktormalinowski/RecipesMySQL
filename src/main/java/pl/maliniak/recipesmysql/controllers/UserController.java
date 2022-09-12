package pl.maliniak.recipesmysql.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.maliniak.recipesmysql.entities.Recipe;
import pl.maliniak.recipesmysql.entities.UserDetailsImpl;
import pl.maliniak.recipesmysql.security.EncoderConfig;
import pl.maliniak.recipesmysql.entities.User;
import pl.maliniak.recipesmysql.repository.UserRepo;
import pl.maliniak.recipesmysql.services.RecipeService;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    UserRepo userRepo;
    RecipeService recipeService;
    @Autowired
    EncoderConfig encoder;

    public UserController(UserRepo userRepo, RecipeService recipeService) {
        this.userRepo = userRepo;
        this.recipeService = recipeService;
    }

    @PostMapping("/api/register")
    public ResponseEntity registerUser(@RequestBody @Valid User user) {
        if (userRepo.findUserByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>("nie mozna rejestrrnąc", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(encoder.passwordEncoder().encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return new ResponseEntity<>("good", HttpStatus.OK);
    }

    @PostMapping("/api/setAsFavourite/{id}")
    public ResponseEntity<String> setAsFavourite(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("id") Long id) {
        if (userRepo.findUserByEmail(user.getUsername()) == null) {
            return new ResponseEntity<>("tylko dla zarejestrowanych", HttpStatus.BAD_REQUEST);
        }
        if (!recipeService.exist(id)) {
            return new ResponseEntity<>("404 (Not found)", HttpStatus.NOT_FOUND);
        }

        Recipe recipe = recipeService.findRecipeById(id);
        List<Recipe> favRecipes = userRepo.findUserByEmail(user.getUsername()).getFavouriteRecipes();
        User userToReplace = userRepo.findUserByEmail(user.getUsername());

        if (favRecipes.contains(recipe)) {
            return new ResponseEntity<>("Juz oznaczone jako ulubione", HttpStatus.BAD_REQUEST);
        }
        userToReplace.getFavouriteRecipes().add(recipe);
        userRepo.save(userToReplace);


        return new ResponseEntity<>("hula", HttpStatus.OK);

    }

    @GetMapping("/api/showFavourites")
    public ResponseEntity showFavourites(@AuthenticationPrincipal UserDetailsImpl user) {
        if (userRepo.findUserByEmail(user.getUsername()) == null) {
            return new ResponseEntity<>("tylko dla zarejestrowanych", HttpStatus.BAD_REQUEST);
        }
        List<Recipe> favRecipes = userRepo.findUserByEmail(user.getUsername()).getFavouriteRecipes();
        return new ResponseEntity(favRecipes, HttpStatus.OK);


    }


}
