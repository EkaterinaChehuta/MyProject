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

    public static final String GET_INGREDIENTS_BY_INGREDIENTS_NAME_ID = "SELECT * FROM ingredients WHERE ingredients_name_id = ?";


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

//    @Override
//    public List<Ingredients> allIngredientsList() throws SQLException {
//        ArrayList<Ingredients> ingredientsList = new ArrayList<>();
//
//        Statement statement = connectionConfig.getConnection().createStatement();
//
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM ingredients");
//
//        while (resultSet.next()){
//            Product product = productRepos.getProductById(resultSet.getInt("product_id"));
//            IngredientsName ingredientsName = ingredientsNameRepos.getIngredientsNameById(resultSet.getInt("ingredients_name_id"));
//
//            ingredientsList.add(new Ingredients(product, ingredientsName));
//        }
//
//        return ingredientsList;
//    }
}
