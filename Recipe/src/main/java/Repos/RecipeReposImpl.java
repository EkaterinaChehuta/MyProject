package Repos;

import Database.ConnectionConfig;
import Domain.Ingredients;
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
    public static final IngredientsRepos ingredientsRepos = new IngredientsReposImpl();

    public static final String GET_RECIPE = "SELECT * FROM recipe";
    public static final String GET_RECIPE_BY_ID = "SELECT * FROM recipe WHERE id=?";
    public static final String GET_RECIPE_BY_NAME = "SELECT * FROM recipe WHERE name=?";
    public static final String UPDATE_RECIPE_NAME = "UPDATE recipe SET name=? WHERE id=?";
    public static final String UPDATE_RECIPE_PREPARATION = "UPDATE recipe SET preparation=? WHERE id=?";

    @Override
    public List<Recipe> allRecipes() throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(GET_RECIPE);

        while (resultSet.next()) {
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
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_RECIPE_BY_NAME);

        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            IngredientsName ingredientsName = ingredientsNameRepos.getIngredientsNameById(
                    Integer.parseInt(resultSet.getString("ingredients_name_id")));
            String preparation = resultSet.getString("preparation");

            return new Recipe(id, name, ingredientsName, preparation);
        }

        return null;
    }

    @Override
    public Recipe getRecipeById(int recipeId) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_RECIPE_BY_ID);

        preparedStatement.setInt(1, recipeId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            IngredientsName ingredientsName = ingredientsNameRepos
                    .getIngredientsNameById(Integer.parseInt(resultSet.getString("ingredients_name_id")));
            String preparation = resultSet.getString("preparation");

            return new Recipe(recipeId, name, ingredientsName, preparation);
        }

        return null;
    }

    @Override
    public void updateRecipeName(int id, String name) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(UPDATE_RECIPE_NAME);

        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateRecipePreparation(int id, String preparation) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(UPDATE_RECIPE_PREPARATION);

        preparedStatement.setString(1, preparation);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateRecipeIngredients(int ingredientsNameId, List<Ingredients> ingredients) throws SQLException {
        ingredientsRepos.updateIngredients(ingredientsNameId, ingredients);
    }
}
