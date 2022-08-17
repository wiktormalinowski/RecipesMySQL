package pl.maliniak.recipesmysql;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {

    Recipe findRecipeById(Long id);

    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(String name);

//    List<Recipe> findBy(String value);


}
