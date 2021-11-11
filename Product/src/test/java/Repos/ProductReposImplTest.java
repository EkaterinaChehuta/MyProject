package Repos;

import Database.ConnectionConfig;
import Domain.IndicatorTest;
import Domain.Product;
import Domain.ProductCategoryTest;
import Domain.ProductTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductReposImplTest {
    private static final int ID_INDICATOR = IndicatorTest.getTestId();
    private static final String NAME_INDICATOR = IndicatorTest.getTestName();
    private static final String VIEW_NAME_INDICATOR = IndicatorTest.getTestViewName();

    private static final int ID_PRODUCT_CATEGORY = ProductCategoryTest.getTestId();
    private static final String NAME_PRODUCT_CATEGORY = ProductCategoryTest.getTestName();

    private static final int ID_PRODUCT = ProductTest.getTestId();
    private static final String NAME_PRODUCT = ProductTest.getTestName();

    private static final String INSERT_TEST_INDICATOR =
            "INSERT INTO indicator(id, name, view_name) VALUES(?, ?, ?)";
    private static final String DELETE_TEST_INDICATOR =
            "DELETE FROM indicator WHERE id=? AND name=? AND view_name=?";
    private static final String INSERT_TEST_PRODUCT_CATEGORY =
            "INSERT INTO product_category(id, name) VALUES(?, ?)";
    private static final String DELETE_TEST_PRODUCT_CATEGORY =
            "DELETE FROM product_category WHERE id=? AND name=?";
    private static final String INSERT_TEST_PRODUCT =
            "INSERT INTO product(id, name, indicator_id, product_category_id) VALUES(?, ?, ?, ?)";
    private static final String DELETE_TEST_PRODUCT =
            "DELETE FROM product WHERE id=? AND name=? AND indicator_id=? AND product_category_id=?";

    @BeforeClass
    public static void startUpBefore() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_TEST_INDICATOR)) {
            preparedStatement.setInt(1, ID_INDICATOR);
            preparedStatement.setString(2, NAME_INDICATOR);
            preparedStatement.setString(3, VIEW_NAME_INDICATOR);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_TEST_PRODUCT_CATEGORY)) {
            preparedStatement.setInt(1, ID_PRODUCT_CATEGORY);
            preparedStatement.setString(2, NAME_PRODUCT_CATEGORY);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_TEST_PRODUCT)) {
            preparedStatement.setInt(1, ID_PRODUCT);
            preparedStatement.setString(2, NAME_PRODUCT);
            preparedStatement.setInt(3, ID_INDICATOR);
            preparedStatement.setInt(4, ID_PRODUCT_CATEGORY);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterClass
    public static void startUpAfter() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_PRODUCT)) {
            preparedStatement.setInt(1, ID_PRODUCT);
            preparedStatement.setString(2, NAME_PRODUCT);
            preparedStatement.setInt(3, ID_INDICATOR);
            preparedStatement.setInt(4, ID_PRODUCT_CATEGORY);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_INDICATOR)) {
            preparedStatement.setInt(1, ID_INDICATOR);
            preparedStatement.setString(2, NAME_INDICATOR);
            preparedStatement.setString(3, VIEW_NAME_INDICATOR);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
    public void allProduct() throws SQLException {
        List<Product> products = new ProductReposImpl().allProduct();

        Assert.assertNotNull(products);
    }

    @Test
    public void getProductByName() throws SQLException {
        Product product = new ProductReposImpl().getProductByName(NAME_PRODUCT);

        Assert.assertNotNull(product);
        Assert.assertEquals(product.getName(), NAME_PRODUCT);
    }

    @Test
    public void getProductById() throws SQLException {
        Product product = new ProductReposImpl().getProductById(ID_PRODUCT);

        Assert.assertNotNull(product);
        Assert.assertEquals(product.getId(), ID_PRODUCT);
    }

//    @Test
//    public void addNewProduct() {
//        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
//                .prepareStatement(INSERT_TEST_PRODUCT)) {
//            preparedStatement.setInt(1, ID_PRODUCT);
//            preparedStatement.setString(2, NAME_PRODUCT);
//            preparedStatement.setInt(3, ID_INDICATOR);
//            preparedStatement.setInt(4, ID_PRODUCT_CATEGORY);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            Assert.assertNotNull(resultSet);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
}