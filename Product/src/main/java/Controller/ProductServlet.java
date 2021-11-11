package Controller;

import Repos.ProductRepos;
import Repos.ProductReposImpl;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        productRepos = new ProductReposImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("products", productRepos.allProduct());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.getRequestDispatcher("WEB-INF/view/product/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        if (req.getParameter("remove") != null) {
            int id = Integer.parseInt(new String(req.getParameter("id")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

            //todo добавить подтверждение удаления продукта

            removeProduct(id);
            resp.sendRedirect("products");
        }
    }

    private void removeProduct(int id) {
        try {
            productRepos.removeProduct(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
