package Controller;

import Database.ConnectionConfig;
import Domain.Ingredients;
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

@WebServlet("/viewRecipe")
public class ViewRecipeServlet extends HttpServlet {
    private static final RecipeRepos recipeRepos = new RecipeReposImpl();
    private static final IngredientsRepos ingredientsRepos = new IngredientsReposImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recipe recipe = new Recipe();
        List<Ingredients> ingredients = new ArrayList<>();
        String recipeId = req.getParameter("id");

        try {
            recipe = recipeRepos.getRecipeById(Integer.parseInt(recipeId));
            ingredients = ingredientsRepos.getIngredientsListByIngredientsNameId(recipe.getIngredientsName().getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.setAttribute("recipe", recipe);
        req.setAttribute("ingredients", ingredients);

        req.getRequestDispatcher("WEB-INF/view/viewRecipe.jsp").forward(req, resp);
    }
}
