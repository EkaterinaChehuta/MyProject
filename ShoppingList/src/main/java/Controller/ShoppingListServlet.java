package Controller;

import Domain.*;
import Repos.*;
import org.graalvm.compiler.serviceprovider.IsolateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/shoppingList")
public class ShoppingListServlet extends HttpServlet {
    private static final ProductRepos productRepos = new ProductReposImpl();
    private static final ShoppingListRepos shoppingListRepos = new ShoppingListReposImpl();
    private static final RecipeRepos recipeRepos = new RecipeReposImpl();
    private static final IngredientsRepos ingredientsRepos = new IngredientsReposImpl();
    private static final IngredientsNameRepos ingredientsNameRepos = new IngredientsNameReposImpl();


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        List<Product> products = null;
        List<ShoppingList> shoppingList = null;

        try {
            products = productRepos.allProduct();
            shoppingList = shoppingListRepos.allShoppingList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        req.setAttribute("products", products);
        req.setAttribute("shoppingList", shoppingList);

        req.getRequestDispatcher("WEB-INF/view/shoppingList/shoppingList.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("addProductToList") != null) {
            addProductToList(req);
            resp.sendRedirect("/shoppingList");
        }

        if (req.getParameter("save") != null) {
            saveQuantity(req);
            resp.sendRedirect("/shoppingList");
        }

        if (req.getParameter("remove") != null) {
            removeProductFromList(req);
            resp.sendRedirect("/shoppingList");
        }

        if (req.getParameter("isPurchased") != null) {
            isPurchased(req);
            resp.sendRedirect("/shoppingList");
        }

        if (req.getParameter("addProductsToList") != null) {
            addProductsToList(req);
            resp.sendRedirect("/recipe");
        }
    }

    private void addProductsToList(HttpServletRequest req) {
        try {
            Recipe recipe = recipeRepos.getRecipeById(Integer.parseInt(req.getParameter("id")));

            IngredientsName ingredientsName = ingredientsNameRepos.getIngredientsNameByName(recipe.getName());

            List<Ingredients> ingredients = ingredientsRepos
                    .getIngredientsListByIngredientsNameId(ingredientsName.getId());

            for (Ingredients ingredient : ingredients) {
                Product product = ingredient.getProduct();
                int quantity = ingredient.getQuantity();

                ShoppingList productFromList = shoppingListRepos.getProductFromList(product.getId());

                if (productFromList != null) {
                    quantity += productFromList.getQuantity();
                    shoppingListRepos.saveQuantity(productFromList.getId(), quantity);
                } else {
                    ShoppingList shoppingList = new ShoppingList(product, quantity, false);
                    shoppingListRepos.addProductToList(shoppingList);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addProductToList(HttpServletRequest req) {
        if (req.getParameter("productId") != null) {
            String productId = new String(req.getParameter("productId")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            ShoppingList shoppingList = null;

            try {
                shoppingList = shoppingListRepos.getProductFromList(Integer.parseInt(productId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (shoppingList == null) {
                try {
                    Product product = productRepos.getProductById(Integer.parseInt(productId));
                    int quantity = 0;
                    boolean isPurchased = false;
                    shoppingListRepos.addProductToList(new ShoppingList(product, quantity, isPurchased));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                //todo вывести сообщение что продукт есть в списке
            }
        }
    }

    private void saveQuantity(HttpServletRequest req) {
        if (!req.getParameter("quantity").isEmpty()) {
            String quantity = new String(req.getParameter("quantity")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String id = new String(req.getParameter("id")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            try {
                shoppingListRepos.saveQuantity(Integer.parseInt(id), Integer.parseInt(quantity));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void removeProductFromList(HttpServletRequest req) {
        String id = new String(req.getParameter("id")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        try {
            shoppingListRepos.removeProductToList(Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void isPurchased(HttpServletRequest req) {
        String id = new String(req.getParameter("id")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        boolean purchased = req.getParameter("purchased") != null;

        try {
            shoppingListRepos.saveIsPurchased(Integer.parseInt(id), purchased);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
