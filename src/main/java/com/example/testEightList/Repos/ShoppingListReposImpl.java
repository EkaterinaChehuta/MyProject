package com.example.testEightList.Repos;

import com.example.testEightList.Config.ConnectionConfig;
import com.example.testEightList.domain.Product;
import com.example.testEightList.domain.ShoppingList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListReposImpl implements ShoppingListRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();
    private final ProductRepos productRepos = new ProductReposImpl();

    @Override
    public List<ShoppingList> allShoppingList() throws SQLException {
        List<ShoppingList> productList = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shoppingList");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int productId = resultSet.getInt("product_id");
            int quantity = resultSet.getInt("quantity");

            Product product = productRepos.getProductById(productId);

            ShoppingList shoppingList = new ShoppingList(id, product, quantity);

            productList.add(shoppingList);
        }

        return productList;
    }

    @Override
    public void addProductToList(ShoppingList shoppingList) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("INSERT INTO shoppingList (product_id) VALUES(?)");

        preparedStatement.setInt(1, shoppingList.getProduct().getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteProductToList(int id) throws SQLException {
        PreparedStatement preparedStatement =connectionConfig.getConnection()
                .prepareStatement("DELETE FROM shoppingList WHERE id=?");

        preparedStatement.setInt(1,id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void saveChanges(int id, int quantity) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("UPDATE shoppingList SET quantity=? WHERE id=?");

        preparedStatement.setInt(1, quantity);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }
}
