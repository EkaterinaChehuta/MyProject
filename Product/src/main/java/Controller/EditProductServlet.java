package Controller;

import Domain.Product;
import Repos.IndicatorRepos;
import Repos.IndicatorReposImpl;
import Repos.ProductRepos;
import Repos.ProductReposImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
    private static final ProductRepos productRepos = new ProductReposImpl();
    private static final IndicatorRepos indicatorRepos = new IndicatorReposImpl();
    //todo ввести константы для сообщений
    String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(new String(req.getParameter("id")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        try {
            req.setAttribute("product", productRepos.getProductById(id));
            req.setAttribute("indicators", indicatorRepos.allIndicator());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (errorMessage.equals("copy")) { // проверяем на копию
            req.setAttribute("error", "Такой продукт есть в базе");
        }

        errorMessage = "";

        req.getRequestDispatcher("WEB-INF/view/product/editProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(new String(req.getParameter("id")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        if (req.getParameter("name") != null && !req.getParameter("name").isEmpty()) {
            String name = new String(req.getParameter("name")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            try {
                if (isCopyProduct(name)) {
                    errorMessage = "copy";

                    resp.sendRedirect("/editProduct?id=" + id);
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            editNameProduct(id, name);
        }

        if (req.getParameter("indicator") != null && !req.getParameter("indicator").isEmpty()) {
            String indicatorId = new String(req.getParameter("indicator")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            editIndicatorProduct(id, Integer.parseInt(indicatorId));
        }

        resp.sendRedirect("/products");
    }

    //todo одинаковый метод в трех сервлетах
    private boolean isCopyProduct(String name) throws SQLException {
        Product product = productRepos.getProductByName(name);
        return product != null;
    }

    private void editNameProduct(int id, String name) {
        try {
            productRepos.updateProduct(id, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void editIndicatorProduct(int id, int indicatorId) {
        try {
            productRepos.updateProduct(id, indicatorId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
