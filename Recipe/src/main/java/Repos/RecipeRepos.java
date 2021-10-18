package Repos;

import Domain.Ingredients;
import Domain.IngredientsName;
import Domain.Recipe;

import java.sql.SQLException;
import java.util.List;

public interface RecipeRepos {
    List<Recipe> allRecipes() throws SQLException;

    Recipe getRecipeByName(String name) throws SQLException;

    Recipe getRecipeById(int id) throws SQLException;

    void updateRecipeName(int id, String name) throws SQLException;

    void updateRecipePreparation(int id, String preparation) throws SQLException;

    void updateRecipeIngredients(int ingredientsNameId, List<Ingredients> ingredients) throws SQLException;
}
