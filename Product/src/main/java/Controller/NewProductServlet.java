package Controller;

import Domain.Indicator;
import Domain.Product;
import Domain.ProductCategory;
import Repos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/newProduct")
public class NewProductServlet extends HttpServlet {
    private static final ProductRepos productRepos = new ProductReposImpl();
    private static final IndicatorRepos indicatorRepos = new IndicatorReposImpl();
    private static final ProductCategoryRepos productCategoryRepos = new ProductCategoryReposImpl();

    //todo ввести константы для сообщений
    String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("indicators", indicatorRepos.allIndicator());
            req.setAttribute("productCategories", productCategoryRepos.allProductCategory());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (errorMessage.equals("copy")) { // проверяем на копию
            req.setAttribute("error", "Такой продукт есть в базе");
        }
        if (errorMessage.equals("no parameter")) {
            req.setAttribute("error", "Не введенно название продукта или единица измерения");
        }

        errorMessage = "";

        req.getRequestDispatcher("WEB-INF/view/product/newProduct.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //todo возвращать в зависимости откуда пришли (из рецепта или из продуктов)
        if(req.getParameter("save") != null){
            if (req.getParameter("name") == null || req.getParameter("name").isEmpty()
                    || req.getParameter("indicator") == null || req.getParameter("indicator").isEmpty()
                    || req.getParameter("productCategory") == null || req.getParameter("productCategory").isEmpty()) {
                errorMessage = "no parameter";
                resp.sendRedirect("newProduct");
                return;
            }

            String name = new String(req.getParameter("name")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String indicatorId = new String(req.getParameter("indicator")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String productCategoryId = new String(req.getParameter("productCategory")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            try {
                if (isCopyProduct(name)) {
                    errorMessage = "copy";

                    resp.sendRedirect("newProduct");
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                Indicator indicator = indicatorRepos.getIndicatorById(Integer.parseInt(indicatorId));
                ProductCategory productCategory = productCategoryRepos.getProductCategoryById(Integer.parseInt(productCategoryId));

                productRepos.addNewProduct(new Product(name, indicator, productCategory));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            resp.sendRedirect("products");
        }
    }

    private boolean isCopyProduct(String name) throws SQLException {
        //todo добавить проверку на категорию и добавлять в сообщения
        Product product = productRepos.getProductByName(name);
        return product != null;
    }
}
