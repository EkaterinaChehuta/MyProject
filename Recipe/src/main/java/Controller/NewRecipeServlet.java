package Controller;

import Domain.IngredientsName;
import Domain.Product;
import Domain.Recipe;
import Repos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/newRecipe")
public class NewRecipeServlet extends HttpServlet {
    private static final RecipeRepos recipeRepos = new RecipeReposImpl();
    private static final ProductRepos productRepos = new ProductReposImpl();
    private static final IngredientsNameRepos ingredientsNameRepos = new IngredientsNameReposImpl();
    private static final IngredientsRepos ingredientsRepos = new IngredientsReposImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("products", productRepos.allProduct());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.getRequestDispatcher("WEB-INF/view/newRecipe.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        if (req.getParameter("save") != null) {
//            String name = req.getParameter("nameRecipe");
//            IngredientsName ingredientsName = null;
//
//            try {
//                ingredientsNameRepos.addNewIngredientsName(name);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            Map<Product, Integer> ingredients = req.getParameter("ingredients");
//
//            for (Map.Entry<Product, Integer> ingredient : ingredients.entrySet()) {
//                try {
//                    ingredientsRepos.addIngredient(ingredient.getKey(), ingredient.getValue(), ingredientsName);
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//
//            String preparation = req.getParameter("preparation");
//
//            try {
//                recipeRepos.addRecipe(name, ingredientsName, preparation);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
}
