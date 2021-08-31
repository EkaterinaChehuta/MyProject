package Repos;

import Domain.Ingredients;
import Domain.IngredientsName;
import Domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IngredientsRepos {
    List<Ingredients> getIngredientsByIngredientsNameId(int id) throws SQLException;
    void addIngredient(Product product, int quantity, IngredientsName ingredientsName) throws SQLException;
//    List<Ingredients> allIngredientsList() throws SQLException;
}
