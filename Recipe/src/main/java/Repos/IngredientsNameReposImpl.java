package Repos;

import Database.ConnectionConfig;
import Domain.IngredientsName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientsNameReposImpl implements IngredientsNameRepos {
    private static final ConnectionConfig connectionConfig = new ConnectionConfig();

    @Override
    public IngredientsName getIngredientsNameById(int id) throws SQLException {
        PreparedStatement preparedStatement = connectionConfig.getConnection()
                .prepareStatement("SELECT * FROM ingredients_name WHERE id=?");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");

            return new IngredientsName(id, name);
        }

        return null;
    }
}
