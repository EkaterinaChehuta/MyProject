package com.example.testEightList.Repos;

import com.example.testEightList.Config.ConnectionConfig;
import com.example.testEightList.domain.Indicator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IndicatorReposImpl implements IndicatorRepos {
    private final ConnectionConfig connectionConfig = new ConnectionConfig();

    @Override
    public List<Indicator> allIndicator() throws SQLException {
        ArrayList<Indicator> indicators = new ArrayList<>();

        Statement statement = connectionConfig.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM indicator");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String viewName = resultSet.getString("viewName");

            Indicator indicator = new Indicator(id, name, viewName);

            indicators.add(indicator);
        }

        return indicators;
    }

    @Override
    public Indicator getIndicatorById(int indicatorId) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("SELECT * FROM indicator WHERE id=?");

        preparedStatement.setInt(1, indicatorId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String viewName = resultSet.getString("viewName");

            return new Indicator(id, name, viewName);
        }

        return null;
    }
}
