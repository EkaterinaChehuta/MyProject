<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
  <h3>
    <a href="shoppingList">Список покупок</a>
    <a href="products?action=viewProducts">Список продуктов</a>
    <a href="recipe">Список рецептов</a>
  </h3>
  <form action="/newProduct" method="get">
      <button type="submit">Добавить новый продукт</button>
  </form>
  <form action="products?search=${productCategory.id}" method="get">
    <select name="productCategory">
      <option value="0" selected disabled>Выберите категорию продукта</option>
      <c:forEach var="productCategory" items="${productCategories}">
      <option value="${productCategory.id}">${productCategory.name}</option>
      </c:forEach>
    </select>
    <button type="submit">Фильтровать</button>
  </form>
    <table border="1">
        <tr>
            <th>Наименование</th>
            <th>Единица измерения</th>
            <th>Действие</th>
            </tr>
            <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.indicator.name}</td>
                <td>
                    <form action="products?id=${product.id}" method="post">
                        <button type="submit" name="remove">Удалить</button>
                    </form>
                    <form action="editProduct" method="get">
                        <button type="submit">Изменить</button>
                        <input type="hidden" value="${product.id}" name="id"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
