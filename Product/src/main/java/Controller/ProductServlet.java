package Controller;

import Repos.IndicatorRepos;
import Repos.IndicatorReposImpl;
import Repos.ProductRepos;
import Repos.ProductReposImpl;
import Domain.Indicator;
import Domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/newProduct")
public class ProductServlet extends HttpServlet {
    private final ProductRepos productRepos = new ProductReposImpl();
    private final IndicatorRepos indicatorRepos = new IndicatorReposImpl();
    boolean isCopy = false; //todo так не красиво

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("indicators", indicatorRepos.allIndicator());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (isCopy) { // проверяем это что то на копию
            req.setAttribute("error", "Такой продукт есть в базе");
        }

        isCopy = false;

        req.getRequestDispatcher("WEB-INF/view/addNewProduct.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = new String(req.getParameter("name")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String indicatorId = new String(req.getParameter("indicator")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        Product product = null;

        try {
            product = productRepos.getProductByName(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //проверить на копию (если копия, вывести сообщение и перенаправить на newProduct)
        if (product != null) {
            // что то говорящее что продукт есть в базе
            isCopy = true;

            resp.sendRedirect("/newProduct");
            return;
        }

        //сохранить в базу
        try {
            Indicator indicator = indicatorRepos.getIndicatorById(Integer.parseInt(indicatorId));

            productRepos.addNewProduct(new Product(name, indicator));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //редирект к списку
        resp.sendRedirect("/shoppingList");
    }
}
