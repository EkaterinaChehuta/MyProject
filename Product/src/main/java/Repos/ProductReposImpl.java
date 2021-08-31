package Repos;


import Database.ConnectionConfig;
import Domain.Indicator;
import Domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReposImpl implements ProductRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();
    private final IndicatorRepos indicatorRepos = new IndicatorReposImpl();

    private static final String GET_PRODUCT = "SELECT * FROM product";
    private static final String GET_PRODUCT_BY_NAME = "SELECT * FROM product WHERE name=?";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id=?";
    private static final String INSERT_PRODUCT = "INSERT INTO product (name, indicator_id) VALUES (?, ?)";

    @Override
    public List<Product> allProduct() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(GET_PRODUCT);

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
                .prepareStatement(GET_PRODUCT_BY_NAME);
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
                .prepareStatement(GET_PRODUCT_BY_ID);

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
                .prepareStatement(INSERT_PRODUCT);

        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getIndicator().getId());

        preparedStatement.executeUpdate();
    }
}
