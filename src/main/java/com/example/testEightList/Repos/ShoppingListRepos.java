package com.example.testEightList.Repos;

import com.example.testEightList.domain.Product;
import com.example.testEightList.domain.ShoppingList;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingListRepos{
    List<ShoppingList> allShoppingList() throws SQLException;
    void addProductToList(ShoppingList shoppingList) throws SQLException;
    void removeProductToList(int id) throws SQLException;
    void saveChanges(int id, int quantity) throws SQLException;
    Product getProductById(int id) throws SQLException;
}
