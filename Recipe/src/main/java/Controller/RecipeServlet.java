package Controller;

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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/recipesList")
public class RecipeServlet extends HttpServlet {
    private static final RecipeRepos recipeRepos = new RecipeReposImpl();
    private static final IngredientsRepos ingredientsRepos = new IngredientsReposImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Recipe> recipes = new ArrayList<>();
//        List<Ingredients> ingredientsList = new ArrayList<>();

        try {
            recipes = recipeRepos.allRecipes();
//            ingredientsList = ingredientsRepos.allIngredientsList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.setAttribute("recipes", recipes);
//        req.setAttribute("ingredients", ingredientsList);

        req.getRequestDispatcher("WEB-INF/view/recipes.jsp")
                .forward(req, resp);
    }
}
