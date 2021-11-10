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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

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

        req.getRequestDispatcher("WEB-INF/view/recipe/newRecipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = new String(req.getParameter("name")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String[] ingredients = req.getParameterValues("ingredients");
        String preparation = new String(req.getParameter("preparation")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        try {
            ingredientsNameRepos.addNewIngredientsName(new IngredientsName(name));

            IngredientsName ingredientsName = ingredientsNameRepos.getIngredientsNameByName(name);

            int i = 0;

            for (String ingredient : ingredients) {
                String ingredient2 = new String(ingredient.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                int quantity = 1;
                Product product = productRepos.getProductById(Integer.parseInt(ingredient2));
                ingredientsRepos.addNewIngredient(product, ingredientsName, quantity);
                i++;
            }

            recipeRepos.addNewRecipe(new Recipe(name, ingredientsName, preparation));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.sendRedirect("/recipe");
    }
}
