package Repos;

import Domain.Recipe;

import java.sql.SQLException;
import java.util.List;

public interface RecipeRepos {
    List<Recipe> allRecipes() throws SQLException;
    Recipe getRecipeByName(String name) throws SQLException;
    Recipe getRecipeById(int id) throws SQLException;
}
