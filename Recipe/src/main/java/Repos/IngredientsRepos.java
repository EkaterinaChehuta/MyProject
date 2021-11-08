package Repos;

import Domain.Ingredients;
import Domain.IngredientsName;
import Domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IngredientsRepos {
    List<Ingredients> getIngredientsListByIngredientsNameId(int id) throws SQLException;

    List<Ingredients> allIngredientsList() throws SQLException;

    void updateIngredients(int ingredientNameId, List<Ingredients> ingredients) throws SQLException;

    void addNewIngredient(Product product, IngredientsName ingredientsName, int quantity) throws SQLException;
}
