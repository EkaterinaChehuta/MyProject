package Repos;

import Database.ConnectionConfig;
import Domain.IngredientsName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientsNameReposImpl implements IngredientsNameRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();

    private static final String GET_INGREDIENTS_NAME_BY_ID = "SELECT * FROM ingredients_name WHERE id=?";
    private static final String INSERT_INGREDIENTS_NAME = "INSERT INTO ingredients_name (name) VALUES (?)";

    @Override
    public IngredientsName getIngredientsNameById(int id) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(GET_INGREDIENTS_NAME_BY_ID);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");

            return new IngredientsName(id, name);
        }

        return null;
    }

    @Override
    public int addNewIngredientsName(String name) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement(INSERT_INGREDIENTS_NAME);

        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.getInt("id");
    }
}
