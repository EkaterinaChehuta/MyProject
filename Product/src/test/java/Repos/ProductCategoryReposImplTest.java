package Repos;

import Database.ConnectionConfig;
import Domain.ProductCategory;
import Domain.ProductCategoryTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryReposImplTest {
    private static final int ID_PRODUCT_CATEGORY = ProductCategoryTest.getTestId();
    private static final String NAME_PRODUCT_CATEGORY = ProductCategoryTest.getTestName();

    private static final String INSERT_TEST_PRODUCT_CATEGORY =
            "INSERT INTO product_category(id, name) VALUES(?, ?)";
    private static final String DELETE_TEST_PRODUCT_CATEGORY =
            "DELETE FROM product_category WHERE id=? AND name=?";

    @BeforeClass
    public static void startUpBefore() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_TEST_PRODUCT_CATEGORY)) {
            preparedStatement.setInt(1, ID_PRODUCT_CATEGORY);
            preparedStatement.setString(2, NAME_PRODUCT_CATEGORY);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterClass
    public static void startUpAfter() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_PRODUCT_CATEGORY)) {
            preparedStatement.setInt(1, ID_PRODUCT_CATEGORY);
            preparedStatement.setString(2, NAME_PRODUCT_CATEGORY);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void getProductCategoryById() throws SQLException{
        ProductCategory productCategory = new ProductCategoryReposImpl().getProductCategoryById(ID_PRODUCT_CATEGORY);

        Assert.assertNotNull(productCategory);
        Assert.assertEquals(productCategory.getId(), ID_PRODUCT_CATEGORY);
    }

    @Test
    public void allProductCategory() throws SQLException{
        List<ProductCategory> productCategories = new ProductCategoryReposImpl().allProductCategory();

        Assert.assertNotNull(productCategories);
    }
}
