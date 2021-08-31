package Repos;

import Database.ConnectionConfig;
import Domain.Indicator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IndicatorReposImpl implements IndicatorRepos {
    private final ConnectionConfig connectionConfig = new ConnectionConfig();

    private static final String GET_INDICATOR = "SELECT * FROM indicator";
    private static final String GET_INDICATOR_BY_ID = "SELECT * FROM indicator WHERE id=?";

    @Override
    public List<Indicator> allIndicator() throws SQLException {
        ArrayList<Indicator> indicators = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(GET_INDICATOR);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String viewName = resultSet.getString("view_name");

            Indicator indicator = new Indicator(id, name, viewName);

            indicators.add(indicator);
        }

        return indicators;
    }

    @Override
    public Indicator getIndicatorById(int indicatorId) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_INDICATOR_BY_ID);

        preparedStatement.setInt(1, indicatorId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String viewName = resultSet.getString("view_name");

            return new Indicator(id, name, viewName);
        }

        return null;
    }
}
