package Repos;

import Database.ConnectionConfig;
import Domain.IngredientsName;
import Domain.IngredientsNameTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientsNameReposImplTest {
    private static final int ID_INGREDIENTS_NAME = IngredientsNameTest.getTestId();
    private static final String NAME_INGREDIENTS_NAME = IngredientsNameTest.getTestName();

    private static final String INSERT_INGREDIENTS_NAME =
            "INSERT INTO ingredients_name(id, name) VALUES(?, ?)";
    private static final String DELETE_INGREDIENTS_NAME =
            "DELETE FROM ingredients_name WHERE id=? AND name=?";

    @BeforeClass
    public static void startUpBefore() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_INGREDIENTS_NAME)) {
            preparedStatement.setInt(1, ID_INGREDIENTS_NAME);
            preparedStatement.setString(2, NAME_INGREDIENTS_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterClass
    public static void startUpAfter() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_INGREDIENTS_NAME)) {
            preparedStatement.setInt(1, ID_INGREDIENTS_NAME);
            preparedStatement.setString(2, NAME_INGREDIENTS_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void getIngredientsNameById() throws SQLException {
        IngredientsName ingredientsName = new IngredientsNameReposImpl().getIngredientsNameById(ID_INGREDIENTS_NAME);

        Assert.assertEquals(ingredientsName.getId(), ID_INGREDIENTS_NAME);
    }

    @Test
    public void addNewIngredientsName() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement("SELECT * FROM ingredients_name WHERE id=? AND name=?")) {
            preparedStatement.setInt(1, ID_INGREDIENTS_NAME);
            preparedStatement.setString(2, NAME_INGREDIENTS_NAME);

            ResultSet resultSet = preparedStatement.executeQuery();

            Assert.assertNotNull(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}