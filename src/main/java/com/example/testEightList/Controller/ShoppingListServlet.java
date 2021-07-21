package com.example.testEightList.Controller;

import com.example.testEightList.Repos.ProductRepos;
import com.example.testEightList.Repos.ProductReposImpl;
import com.example.testEightList.Repos.ShoppingListRepos;
import com.example.testEightList.Repos.ShoppingListReposImpl;
import com.example.testEightList.domain.Product;
import com.example.testEightList.domain.ShoppingList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/shoppingList") вместо web.xml
public class ShoppingListServlet extends HttpServlet {
    private final ProductRepos productRepos = new ProductReposImpl();
    private final ShoppingListRepos shoppingListRepos = new ShoppingListReposImpl();

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

        req.getRequestDispatcher("WEB-INF/view/shoppingList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("addNewProduct") != null) {
            resp.sendRedirect("/newProduct");
        } else if (req.getParameter("addProductToList") != null) {
            String productId = new String(req.getParameter("productId")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            Product product = null;

            try {
                product = productRepos.getProductById(Integer.parseInt(productId));
                shoppingListRepos.addProductToList(new ShoppingList(product));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            resp.sendRedirect("/shoppingList");
        } else if (req.getParameter("save") != null) {
            // обработать ложное нажатие
            String quantity = new String(req.getParameter("quantity")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String id = new String(req.getParameter("id")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            try {
                shoppingListRepos.saveChanges(Integer.parseInt(id), Integer.parseInt(quantity));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            resp.sendRedirect("/shoppingList");
        } else if (req.getParameter("delete") != null) {
            String id = new String(req.getParameter("id")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            try {
                shoppingListRepos.deleteProductToList(Integer.parseInt(id));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            resp.sendRedirect("/shoppingList");
        } else if(req.getParameter("isPurchased") != null){

        }
    }
}
