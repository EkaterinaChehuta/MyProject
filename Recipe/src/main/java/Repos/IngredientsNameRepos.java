package Repos;

import Domain.IngredientsName;

import java.sql.SQLException;

public interface IngredientsNameRepos {
    IngredientsName getIngredientsNameById(int id) throws SQLException;
}