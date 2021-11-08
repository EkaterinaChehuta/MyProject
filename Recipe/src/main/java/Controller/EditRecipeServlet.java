package Controller;

import Domain.Ingredients;
import Domain.Recipe;
import Repos.IngredientsRepos;
import Repos.IngredientsReposImpl;
import Repos.RecipeRepos;
import Repos.RecipeReposImpl;

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

@WebServlet("/editRecipe")
public class EditRecipeServlet extends HttpServlet {
    public static final RecipeRepos recipeRepos = new RecipeReposImpl();
    public static final IngredientsRepos ingredientsRepos = new IngredientsReposImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recipe recipe = new Recipe();
        List<Ingredients> ingredients = new ArrayList<>();
        String id = req.getParameter("id");

        try {
            recipe = recipeRepos.getRecipeById(Integer.parseInt(id));
            ingredients = ingredientsRepos.getIngredientsListByIngredientsNameId(recipe.getIngredientsName().getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.setAttribute("recipe", recipe);
        req.setAttribute("ingredients", ingredients);

        req.getRequestDispatcher("WEB-INF/view/recipe/editRecipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        switch (action) {
            case "save":
                try {
                    if (req.getParameter("name") != null && !req.getParameter("name").isEmpty()) {
                        String name = new String(req.getParameter("name")
                                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                        if (recipeRepos.getRecipeByName(name) != null) {
                            break;
                        }

                        recipeRepos.updateRecipeName(Integer.parseInt(id), name);
                    }

                    if (req.getParameter("preparation") != null && !req.getParameter("preparation").isEmpty()) {
                        String preparation = new String(req.getParameter("preparation")
                                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                        recipeRepos.updateRecipePreparation(Integer.parseInt(id), preparation);
                    }

                    // как получить список из формы
                    // картинка

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                resp.sendRedirect("/recipe");

                break;
        }
    }
}
