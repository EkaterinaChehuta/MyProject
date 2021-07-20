package com.example.testEightList.Repos;

import com.example.testEightList.Config.ConnectionConfig;
import com.example.testEightList.domain.Indicator;
import com.example.testEightList.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReposImpl implements ProductRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();
    private final IndicatorRepos indicatorRepos = new IndicatorReposImpl();

    @Override
    public List<Product> allProduct() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int indicatorId = resultSet.getInt("indicator_id");

            Indicator indicator = indicatorRepos.getIndicatorById(indicatorId);

            Product product = new Product(id, name, indicator);

            products.add(product);

        }

        return products;
    }

    @Override
    public Product getProductByName(String searchName) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("SELECT * FROM product WHERE name=?");
        preparedStatement.setString(1, searchName);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int indicatorId = resultSet.getInt("indicator_id");

            Indicator indicator = indicatorRepos.getIndicatorById(indicatorId);

            return new Product(id, name, indicator);
        }

        return null;
    }

    @Override
    public Product getProductById(int searchId) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("SELECT * FROM product WHERE id=?");

        preparedStatement.setInt(1, searchId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int indicatorId = resultSet.getInt("indicator_id");

            Indicator indicator = indicatorRepos.getIndicatorById(indicatorId);

            return new Product(id, name, indicator);
        }

        return null;
    }

    @Override
    public void addNewProduct(Product product) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("INSERT INTO product (name, indicator_id) VALUES (?, ?)");

        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getIndicator().getId());

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
