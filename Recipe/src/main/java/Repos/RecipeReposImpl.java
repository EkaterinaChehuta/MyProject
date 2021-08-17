package Repos;

import Database.ConnectionConfig;
import Domain.IngredientsName;
import Domain.Recipe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeReposImpl implements RecipeRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();
    private static final IngredientsNameRepos ingredientsNameRepos = new IngredientsNameReposImpl();

    @Override
    public List<Recipe> allRecipes() throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM recipe");

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String recipeName = resultSet.getString("name");
            int ingredientsNameId = resultSet.getInt("ingredients_name_id");
            String preparation = resultSet.getString("preparation");

            IngredientsName ingredientsName = ingredientsNameRepos.getIngredientsNameById(ingredientsNameId);

            Recipe recipe = new Recipe(id, recipeName, ingredientsName, preparation);

            recipes.add(recipe);
        }

        return recipes;
    }

    @Override
    public Recipe getRecipeByName(String name) throws SQLException {
        return null;
    }

    @Override
    public Recipe getRecipeById(int recipeId) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("SELECT * FROM recipe WHERE id=?");

        preparedStatement.setInt(1, recipeId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            String name = resultSet.getString("name");
            IngredientsName ingredientsName = ingredientsNameRepos
                    .getIngredientsNameById(Integer.parseInt(resultSet.getString("ingredients_name_id")));
            String preparation = resultSet.getString("preparation");

            return new Recipe(recipeId, name, ingredientsName, preparation);
        }

        return null;
    }
}
