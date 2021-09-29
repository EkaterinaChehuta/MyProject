package Controller;

import Repos.IndicatorRepos;
import Repos.IndicatorReposImpl;
import Repos.ProductRepos;
import Repos.ProductReposImpl;
import Domain.Indicator;
import Domain.Product;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductRepos productRepos;
    private IndicatorRepos indicatorRepos;
    //todo ввести константы для сообщений
    String errorMessage = "";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        productRepos = new ProductReposImpl();
        indicatorRepos = new IndicatorReposImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "viewProducts" : action) {
            case "addNewProduct":
                try {
                    req.setAttribute("indicators", indicatorRepos.allIndicator());
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
                break;

            case "editProduct":
                int id = Integer.parseInt(new String(req.getParameter("id")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

                try {
                    req.setAttribute("product", productRepos.getProductById(id));
                    req.setAttribute("indicators", indicatorRepos.allIndicator());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                req.getRequestDispatcher("WEB-INF/view/product/editProduct.jsp").forward(req, resp);
                break;

            case "viewProducts":
                try {
                    req.setAttribute("products", productRepos.allProduct());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                req.getRequestDispatcher("WEB-INF/view/product/products.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        int id;
        String name;
        String indicatorId;

        switch (action) {
            case "save":
                if (req.getParameter("name") == null || req.getParameter("name").isEmpty()
                        || req.getParameter("indicator") == null || req.getParameter("indicator").isEmpty()) {
                    errorMessage = "no parameter";
                    resp.sendRedirect("products?action=addNewProduct");
                    return;
                }

                name = new String(req.getParameter("name")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                indicatorId = new String(req.getParameter("indicator")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                try {
                    if (isCopyProduct(name)) {
                        errorMessage = "copy";

                        resp.sendRedirect("products?action=addNewProduct");
                        return;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    Indicator indicator = indicatorRepos.getIndicatorById(Integer.parseInt(indicatorId));

                    productRepos.addNewProduct(new Product(name, indicator));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                resp.sendRedirect("/shoppingList");
                break;

            case "remove":
                id = Integer.parseInt(new String(req.getParameter("id")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

                //todo добавить подтверждение удаления продукта

                removeProduct(id);
                resp.sendRedirect("products");
                break;

            case "edit":
                id = Integer.parseInt(new String(req.getParameter("id")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

                if (req.getParameter("name") != null && !req.getParameter("name").isEmpty()) {
                    name = new String(req.getParameter("name")
                            .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                    try {
                        if (isCopyProduct(name)) {
                            errorMessage = "copy";

                            resp.sendRedirect("products?action=editProduct$$id=" + id);
                            break;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    editNameProduct(id, name);
                }

                if (req.getParameter("indicator") != null && !req.getParameter("indicator").isEmpty()) {
                    indicatorId = new String(req.getParameter("indicator")
                            .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                    editIndicatorProduct(id, Integer.parseInt(indicatorId));
                }

                resp.sendRedirect("products");

                break;
        }
    }

    private void removeProduct(int id) {
        try {
            productRepos.removeProduct(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
