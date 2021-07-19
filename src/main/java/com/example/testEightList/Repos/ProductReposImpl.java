package com.example.testEightList.Repos;

import com.example.testEightList.Config.ConnectionConfig;
import com.example.testEightList.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReposImpl implements ProductRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();

    @Override
    public List<Product> allProduct() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String indicator = resultSet.getString(3);
            Product product = new Product(id, name, indicator);

            products.add(product);

        }
        return products;
    }

    @Override
    public Product getProductByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection().prepareStatement("SELECT * FROM product WHERE name=?");
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            int prId = resultSet.getInt(1);
            String prName = resultSet.getString(2);
            String indicator = resultSet.getString(3);
            return new Product(prId, prName, indicator);
        }

        return null;
    }

    @Override
    public void addNewProduct(Product product) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection().prepareStatement("INSERT INTO product (name, indicator) Values (?, ?)");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getIndicator());

        preparedStatement.executeUpdate();
    }

    /*@Override
    public Product getProductById(int id) throws SQLException {
        Product product = null;

        PreparedStatement preparedStatement = connectionConfig.getConnection().prepareStatement("SELECT * FROM product WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int prId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String indicator = resultSet.getString(3);
            product = new Product(prId, name, indicator);
        }

        return product;
    }*/
}
