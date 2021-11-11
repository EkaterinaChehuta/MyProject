package Repos;

import Domain.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryRepos {
    ProductCategory getProductCategoryById(int id) throws SQLException;
    List<ProductCategory> allProductCategory() throws SQLException;
}
