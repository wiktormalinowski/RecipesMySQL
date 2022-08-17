package pl.maliniak.recipesmysql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maliniak.recipesmysql.entities.Recipe;
import pl.maliniak.recipesmysql.repository.RecipeRepo;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepo.findRecipeById(id);
    }
    public void save(Recipe toSave) {
        recipeRepo.save(toSave);
    }

    public void delete(Recipe toDelete) {
        recipeRepo.delete(toDelete);
    }

    public Long count() {
        return recipeRepo.count();
    }

    public boolean exist(Long id) {
        return recipeRepo.existsById(id);
    }

    public List<Recipe> findRecipesByName(String value) {
        return recipeRepo.findByNameIgnoreCaseContainsOrderByDateDesc(value);
    }
    public List<Recipe> findRecipesByCategory(String category) {
        return recipeRepo.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }
//    public List<Recipe> findRecipesBy(String value) {
//        return recipeRepo.findBy(value);
//    }


}
