package Repos;

import Domain.ShoppingList;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingListRepos{
    List<ShoppingList> allShoppingList() throws SQLException;
    void addProductToList(ShoppingList shoppingList) throws SQLException;
    void removeProductToList(int id) throws SQLException;
    void saveQuantity(int id, int quantity) throws SQLException;
    void saveIsPurchased(int id, boolean isPurchased) throws SQLException;
    ShoppingList getProductFromList(int id) throws SQLException;
}
