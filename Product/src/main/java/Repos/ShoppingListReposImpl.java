package Repos;


import Database.ConnectionConfig;
import Domain.Product;
import Domain.ShoppingList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoppingListReposImpl implements ShoppingListRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();
    private final ProductRepos productRepos = new ProductReposImpl();

    public static final String GET_SHOPPING_LIST = "SELECT * FROM shopping_list";
    public static final String INSERT_SHOPPING_LIST = "INSERT INTO shopping_list (product_id) VALUES(?)";
    public static final String DELETE_SHOPPING_LIST = "DELETE FROM shopping_list WHERE id=?";
    public static final String UPDATE_SHOPPING_LIST = "UPDATE shopping_list SET quantity=? WHERE id=?";
    public static final String GET_SHOPPING_LIST_BY_PRODUCT_ID = "SELECT * FROM shopping_list WHERE product_id=?";

    @Override
    public List<ShoppingList> allShoppingList() throws SQLException {
        List<ShoppingList> productList = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_SHOPPING_LIST);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int productId = resultSet.getInt("product_id");
            int quantity = resultSet.getInt("quantity");
            boolean isPurchased = resultSet.getBoolean("is_purchased");

            Product product = productRepos.getProductById(productId);

            ShoppingList shoppingList = new ShoppingList(id, product, quantity, isPurchased);

            productList.add(shoppingList);
        }

        Collections.sort(productList, new Comparator<ShoppingList>() {
            @Override
            public int compare(ShoppingList o1, ShoppingList o2) {
                String s1 = Boolean.toString(o1.isPurchased());
                String s2 = Boolean.toString(o2.isPurchased());
                return s1.compareTo(s2);
            }
        });

        return productList;
    }

    @Override
    public void addProductToList(ShoppingList shoppingList) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(INSERT_SHOPPING_LIST);

        preparedStatement.setInt(1, shoppingList.getProduct().getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void removeProductToList(int id) throws SQLException {
        PreparedStatement preparedStatement =connectionConfig.getConnection()
                .prepareStatement(DELETE_SHOPPING_LIST);

        preparedStatement.setInt(1,id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void saveChanges(int id, int quantity) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(UPDATE_SHOPPING_LIST);

        preparedStatement.setInt(1, quantity);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_SHOPPING_LIST_BY_PRODUCT_ID);

        preparedStatement.setInt(1, productId);

        ResultSet resultSet = preparedStatement.executeQuery();

        Product product = null;

        while (resultSet.next()){
            product = productRepos.getProductById(productId);
        }

        return product;
    }
}
