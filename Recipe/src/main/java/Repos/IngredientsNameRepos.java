package Repos;

import Domain.Ingredients;
import Domain.IngredientsName;

import java.sql.SQLException;
import java.util.List;

public interface IngredientsNameRepos {
    IngredientsName getIngredientsNameById(int id) throws SQLException;
    IngredientsName getIngredientsNameByName(String name) throws SQLException;
    void addNewIngredientsName(IngredientsName ingredientsName) throws SQLException;
}
