package Repos;

import Database.ConnectionConfig;
import Domain.Indicator;
import Domain.IndicatorTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class IndicatorReposImplTest {
    private static final int TEST_ID = IndicatorTest.getTestId();
    private static final String TEST_NAME = IndicatorTest.getTestName();
    private static final String TEST_VIEW_NAME = IndicatorTest.getTestViewName();

    private static final String INSERT_TEST_INDICATOR =
            "INSERT INTO indicator(id, name, view_name) VALUES(?, ?, ?)";
    private static final String DELETE_TEST_INDICATOR =
            "DELETE FROM indicator WHERE id=? AND name=? AND view_name=?";

    @BeforeClass
    public static void startUpBefore() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(INSERT_TEST_INDICATOR)) {
            preparedStatement.setInt(1, TEST_ID);
            preparedStatement.setString(2, TEST_NAME);
            preparedStatement.setString(3, TEST_VIEW_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterClass
    public static void startUpAfter() {
        try (PreparedStatement preparedStatement = new ConnectionConfig().getConnection()
                .prepareStatement(DELETE_TEST_INDICATOR)) {
            preparedStatement.setInt(1, TEST_ID);
            preparedStatement.setString(2, TEST_NAME);
            preparedStatement.setString(3, TEST_VIEW_NAME);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void allIndicator() throws SQLException {
        List<Indicator> indicators = new IndicatorReposImpl().allIndicator();

        Assert.assertNotNull(indicators);
    }

    @Test
    public void getIndicatorById() throws SQLException {
        Indicator indicator = new IndicatorReposImpl().getIndicatorById(TEST_ID);

        Assert.assertNotNull(indicator);
        Assert.assertEquals(indicator.getId(), TEST_ID);
    }
}
