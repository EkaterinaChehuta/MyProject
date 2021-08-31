package Repos;

import Domain.Product;
import Domain.ShoppingList;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingListRepos{
    List<ShoppingList> allShoppingList() throws SQLException;
    void addProductToList(ShoppingList shoppingList) throws SQLException;
    void removeProductToList(int id) throws SQLException;
    void saveChanges(int id, int quantity) throws SQLException;
    Product getProductByProductId(int id) throws SQLException;
}
