package Repos;

import Database.ConnectionConfig;
import Domain.IngredientsName;
import Domain.Ingredients;
import Domain.Product;
import Domain.Recipe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientsReposImpl implements IngredientsRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();
    private static final IngredientsNameRepos ingredientsNameRepos = new IngredientsNameReposImpl();
    private static final ProductRepos productRepos = new ProductReposImpl();

    private static final String GET_INGREDIENTS_BY_INGREDIENTS_NAME_ID = "SELECT * FROM ingredients WHERE ingredients_name_id = ?";
    private static final String GET_INGREDIENTS = "SELECT * FROM ingredients";
    private static final String INSERT_INGREDIENT = "INSERT INTO ingredients(ingredients_name_id, product_id, quantity) VALUES(?, ?, ?)";

    @Override
    public List<Ingredients> getIngredientsListByIngredientsNameId(int searchId) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_INGREDIENTS_BY_INGREDIENTS_NAME_ID);

        preparedStatement.setInt(1, searchId);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Ingredients> ingredientsList = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            Product product = productRepos.getProductById(resultSet.getInt("product_id"));
            int quantity = resultSet.getInt("quantity");
            IngredientsName ingredientsName = ingredientsNameRepos.getIngredientsNameById(resultSet.getInt("ingredients_name_id"));

            ingredientsList.add(new Ingredients(id, product, quantity, ingredientsName));
        }

        return ingredientsList;
    }

    @Override
    public List<Ingredients> allIngredientsList() throws SQLException {
        ArrayList<Ingredients> ingredientsList = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(GET_INGREDIENTS);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            Product product = productRepos.getProductById(resultSet.getInt("product_id"));
            IngredientsName ingredientsName = ingredientsNameRepos.getIngredientsNameById(resultSet.getInt("ingredients_name_id"));
            int quantity = resultSet.getInt("quantity");

            ingredientsList.add(new Ingredients(id, product, quantity, ingredientsName));
        }

        return ingredientsList;
    }

    @Override
    public void updateIngredients(int ingredientNameId, List<Ingredients> newIngredients) throws SQLException {
        List<Ingredients> currentIngredients = getIngredientsListByIngredientsNameId(ingredientNameId);

        currentIngredients.retainAll(newIngredients);
    }

    @Override
    public void addNewIngredient(Product product, IngredientsName ingredientsName, int quantity) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(INSERT_INGREDIENT);

        preparedStatement.setInt(1, ingredientsName.getId());
        preparedStatement.setInt(2, product.getId());
        preparedStatement.setInt(3, quantity);

        preparedStatement.executeUpdate();
    }
}
