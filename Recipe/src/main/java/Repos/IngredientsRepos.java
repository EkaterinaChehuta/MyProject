package Repos;

import Domain.Ingredients;

import java.sql.SQLException;
import java.util.List;

public interface IngredientsRepos {
    List<Ingredients> getIngredientsListByIngredientsNameId(int id) throws SQLException;

    List<Ingredients> allIngredientsList() throws SQLException;

    void updateIngredients(int ingredientNameId, List<Ingredients> ingredients) throws SQLException;
}
