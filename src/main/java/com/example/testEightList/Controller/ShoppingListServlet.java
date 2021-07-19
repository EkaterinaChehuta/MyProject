package com.example.testEightList.Controller;

import com.example.testEightList.Repos.ProductRepos;
import com.example.testEightList.Repos.ProductReposImpl;
import com.example.testEightList.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/shoppingList") вместо web.xml
public class ShoppingListServlet extends HttpServlet {
    private ProductRepos productRepos = new ProductReposImpl();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        List<Product> products = null;
        try {
            products = productRepos.allProduct();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.setAttribute("products", products);

        req.getRequestDispatcher("WEB-INF/view/shoppingList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //сохранить изменения если такие были

        //переадресация к другому сервлету "ProductServlet"
        resp.sendRedirect("/newProduct");
    }
}
