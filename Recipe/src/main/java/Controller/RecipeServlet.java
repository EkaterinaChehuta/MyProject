package Controller;

import Domain.*;
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

@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {
    private static final RecipeRepos recipeRepos = new RecipeReposImpl();
    private static final IngredientsRepos ingredientsRepos = new IngredientsReposImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Recipe> recipes = new ArrayList<>();
        List<Ingredients> ingredientsList = new ArrayList<>();

        try {
            recipes = recipeRepos.allRecipes();
            ingredientsList = ingredientsRepos.allIngredientsList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.setAttribute("recipes", recipes);
        req.setAttribute("ingredients", ingredientsList);

        req.getRequestDispatcher("WEB-INF/view/recipe/recipes.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "remove":
                //todo добавить подтветждение удаления продукта
                try {
                    recipeRepos.deleteRecipe(Integer.parseInt(req.getParameter("id")));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                resp.sendRedirect("/recipe");
                break;
        }
    }
}
