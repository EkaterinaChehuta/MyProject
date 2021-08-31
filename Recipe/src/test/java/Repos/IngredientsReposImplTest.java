package Repos;

import Database.ConnectionConfig;
import Domain.*;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IngredientsReposImplTest {
    private static final int ID_INDICATOR = IndicatorTest.getTestId();
    private static final String NAME_INDICATOR = IndicatorTest.getTestName();
    private static final String VIEW_NAME_INDICATOR = IndicatorTest.getTestViewName();

    private static final int ID_PRODUCT = ProductTest.getTestId();
    private static final String NAME_PRODUCT = ProductTest.getTestName();

    private static final int ID_INGREDIENTS_NAME = IngredientsNameTest.getTestId();
    private static final String NAME_INGREDIENTS_NAME = IngredientsNameTest.getTestName();

    private static final int ID_INGREDIENT = IngredientsTest.getTestId();
    private static final int QUANTITY_INGREDIENT = IngredientsTest.getTestQuantity();


    private static final String INSERT_TEST_INDICATOR =
            "INSERT INTO indicator(id, name, view_name) VALUES(?, ?, ?)";
    private static final String DELETE_TEST_INDICATOR =
            "DELETE FROM indicator WHERE id=? AND name=? AND view_name=?";
    private static final String INSERT_TEST_PRODUCT =
            "INSERT INTO product(id, name, indicator_id) VALUES(?, ?, ?)";
    private static final String DELETE_TEST_PRODUCT =
            "DELETE FROM product WHERE id=? AND name=? AND indicator_id=?";
    private static final String INSERT_TEST_INGREDIENTS_NAME =
            "INSERT INTO ingredients_name(id, name) VALUES(?, ?)";
    private static final String DELETE_TEST_INGREDIENTS_NAME =
            "DELETE FROM ingredients_name WHERE id=? AND name=?";
    private static final String INSERT_TEST_INGREDIENTS =
            "INSERT INTO ingredients(id, product_id, quantity, ingredients_name_id) VALUES(?, ?, ?, ?)";
    private static final String DELETE_TEST_INGREDIENTS =
            "DELETE FROM ingredients WHERE id=? AND product_id=? AND quantity=? AND ingredients_name_id=?";

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
                .prepareStatement(INSERT_TEST_INGREDIENTS_NAME)) {
            preparedStatement.setInt(1, ID_INGREDIENTS_NAME);
            preparedStatement.setString(2, NAME_INGREDIENTS_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_TEST_INGREDIENTS)) {
            preparedStatement.setInt(1, ID_INGREDIENT);
            preparedStatement.setInt(2, ID_PRODUCT);
            preparedStatement.setInt(3, QUANTITY_INGREDIENT);
            preparedStatement.setInt(4, ID_INGREDIENTS_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterClass
    public static void startUpAfter() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_INGREDIENTS)) {
            preparedStatement.setInt(1, ID_INGREDIENT);
            preparedStatement.setInt(2, ID_PRODUCT);
            preparedStatement.setInt(3, QUANTITY_INGREDIENT);
            preparedStatement.setInt(4, ID_INGREDIENTS_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_INGREDIENTS_NAME)) {
            preparedStatement.setInt(1, ID_INGREDIENTS_NAME);
            preparedStatement.setString(2, NAME_INGREDIENTS_NAME);
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
    public void getIngredientsByIngredientsNameId() throws SQLException {
        List<Ingredients> ingredients = new IngredientsReposImpl().getIngredientsByIngredientsNameId(ID_INGREDIENTS_NAME);

        Assert.assertNotNull(ingredients);
    }

    @Test
    public void addIngredient() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement("SELECT * FROM ingredients WHERE id=? AND product_id=? AND quantity=? AND ingredients_name_id=?")) {
            preparedStatement.setInt(1, ID_INGREDIENT);
            preparedStatement.setInt(2, ID_PRODUCT);
            preparedStatement.setInt(3, QUANTITY_INGREDIENT);
            preparedStatement.setInt(4, ID_INGREDIENTS_NAME);

            ResultSet resultSet = preparedStatement.executeQuery();

            Assert.assertNotNull(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}