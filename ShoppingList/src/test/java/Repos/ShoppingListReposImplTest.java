package Repos;

import Database.ConnectionConfig;
import Domain.IndicatorTest;
import Domain.ProductTest;
import Domain.ShoppingList;
import Domain.ShoppingListTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShoppingListReposImplTest {
    private static final int ID_INDICATOR = IndicatorTest.getTestId();
    private static final String NAME_INDICATOR = IndicatorTest.getTestName();
    private static final String VIEW_NAME_INDICATOR = IndicatorTest.getTestViewName();

    private static final int ID_PRODUCT = ProductTest.getTestId();
    private static final String NAME_PRODUCT = ProductTest.getTestName();

    private static final int ID_SHOPPING_LIST = ShoppingListTest.getTestId();
    private static final int QUANTITY_SHOPPING_LIST = ShoppingListTest.getTestQuantity();
    private static final boolean IS_PURCHASED_SHOPPING_LIST = ShoppingListTest.isTestIsPurchased();

    private static final String INSERT_TEST_INDICATOR =
            "INSERT INTO indicator(id, name, view_name) VALUES(?, ?, ?)";
    private static final String DELETE_TEST_INDICATOR =
            "DELETE FROM indicator WHERE id=? AND name=? AND view_name=?";
    private static final String INSERT_TEST_PRODUCT =
            "INSERT INTO product(id, name, indicator_id) VALUES(?, ?, ?)";
    private static final String DELETE_TEST_PRODUCT =
            "DELETE FROM product WHERE id=? AND name=? AND indicator_id=?";
    private static final String INSERT_TEST_SHOPPING_LIST =
            "INSERT INTO shopping_list(id, product_id, quantity, is_purchased) VALUES(?, ?, ?, ?)";
    private static final String DELETE_TEST_SHOPPING_LIST =
            "DELETE FROM shopping_list WHERE id=? AND product_id=? AND quantity=? AND is_purchased=?";

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
                .prepareStatement(INSERT_TEST_PRODUCT)) {
            preparedStatement.setInt(1, ID_PRODUCT);
            preparedStatement.setString(2, NAME_PRODUCT);
            preparedStatement.setInt(3, ID_INDICATOR);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_TEST_SHOPPING_LIST)) {
            preparedStatement.setInt(1, ID_SHOPPING_LIST);
            preparedStatement.setInt(2, ID_PRODUCT);
            preparedStatement.setInt(3, QUANTITY_SHOPPING_LIST);
            preparedStatement.setBoolean(4, IS_PURCHASED_SHOPPING_LIST);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterClass
    public static void startUpAfter() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_SHOPPING_LIST)) {
            preparedStatement.setInt(1, ID_SHOPPING_LIST);
            preparedStatement.setInt(2, ID_PRODUCT);
            preparedStatement.setInt(3, QUANTITY_SHOPPING_LIST);
            preparedStatement.setBoolean(4, IS_PURCHASED_SHOPPING_LIST);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_PRODUCT)) {
            preparedStatement.setInt(1, ID_PRODUCT);
            preparedStatement.setString(2, NAME_PRODUCT);
            preparedStatement.setInt(3, ID_INDICATOR);
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
    }

    @Test
    public void allShoppingList() throws SQLException {
        List<ShoppingList> shoppingList = new ShoppingListReposImpl().allShoppingList();

        Assert.assertNotNull(shoppingList);
    }

    @Test
    public void addProductToList() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement("SELECT * FROM shopping_list WHERE product_id=?")) {
            preparedStatement.setInt(1, ID_PRODUCT);

            ResultSet resultSet = preparedStatement.executeQuery();

            Assert.assertNotNull(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    public void removeProductToList(){}

//    public void saveChanges

    @Test
    public void getProductByProductId(){
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement("SELECT * FROM shopping_list WHERE product_id=?")) {
            preparedStatement.setInt(1, ID_PRODUCT);

            ResultSet resultSet = preparedStatement.executeQuery();

            Assert.assertNotNull(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}