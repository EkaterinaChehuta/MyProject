package com.example.testEightList.Controller;

import com.example.testEightList.Repos.ProductRepos;
import com.example.testEightList.Repos.ProductReposImpl;
import com.example.testEightList.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class ProductServlet extends HttpServlet {
    private ProductRepos productRepos = new ProductReposImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/addNewProduct.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = new String(req.getParameter("name")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String indicator = new String(req.getParameter("indicator")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        Product product = null;

        try {
            product = productRepos.getProductByName(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //проверить на копию (если копия, вывести сообщение и перенаправить на newProduct)
        if(product != null){
//            req.setAttribute("error", "Такой продукт есть в базе"); http://java-online.ru/jsp-jstl.xhtml
            resp.sendRedirect("/newProduct");
            return;
        }

        //сохранить в базу
        try {
            productRepos.addNewProduct(new Product(name, indicator));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //редирект к списку
        resp.sendRedirect("/shoppingList");
    }
}
