package Repos;

import Database.ConnectionConfig;
import Domain.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryReposImpl implements ProductCategoryRepos{
    private final ConnectionConfig connectionConfig = new ConnectionConfig();

    private static final String GET_PRODUCT_CATEGORY = "SELECT * FROM product_category";
    private static final String GET_PRODUCT_CATEGORY_BY_ID = "SELECT * FROM product_category WHERE id=?";

    @Override
    public ProductCategory getProductCategoryById(int id) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_PRODUCT_CATEGORY_BY_ID);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            String name = resultSet.getString("name");

            return new ProductCategory(id, name);
        }

        return null;
    }

    @Override
    public List<ProductCategory> allProductCategory() throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_PRODUCT_CATEGORY);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<ProductCategory> productCategories = new ArrayList<>();

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            ProductCategory productCategory = new ProductCategory(id, name);

            productCategories.add(productCategory);
        }

        return productCategories;
    }
}
