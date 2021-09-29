package Controller;

import Domain.Product;
import Repos.ProductRepos;
import Repos.ProductReposImpl;
import Repos.RecipeRepos;
import Repos.RecipeReposImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/newRecipe")
public class NewRecipeServlet extends HttpServlet {
    private static final RecipeRepos recipeRepos = new RecipeReposImpl();
    private static final ProductRepos productRepos = new ProductReposImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("products", productRepos.allProduct());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.getRequestDispatcher("WEB-INF/view/newRecipe.jsp").forward(req, resp);
    }
}
