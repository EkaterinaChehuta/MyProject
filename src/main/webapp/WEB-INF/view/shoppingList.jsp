<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<div><h1>Список продуктов</h1></div>
<div>
    <table>
        <tr>
            <th>Действие</th>
            <th>Наименование</th>
            <th>Кол-во</th>
            <th>Мера</th>
        </tr>
        <c:forEach var="shop" items="${shoppingList}">
        <tr>
            <td>
                <form>
                    <input type="checkbox">
                </form>
            </td>
            <td>${shop.product.name}</td>
            <td>
                <form>
                    <input type="text" name="quantity" placeholder="${shop.quantity}" />
                </form>
            </td>
            <td>${shop.product.indicator.viewName}</td>
        </tr>
        </c:forEach>
    </table>
</div>
<div>
    <form action="/shoppingList" method="post">
        <select name="productId">
            <option value="0" selected disabled>Выберите продукт</option>
            <c:forEach var="product" items="${products}">
            <option value="${product.id}">${product.name}</option>
            </c:forEach>
        </select>
        <button type="submit" name="addProductToList">Добавить в список</button>
    </form>
    <form action="/shoppingList" method="post">
            <button type="submit" name="save">Сохранить изменения в списке покупок</button>
        </form>
    <form action="/shoppingList" method="post">
        <button type="submit" name="addNewProduct">Добавить новый продукт</button>
    </form>
</div>
</body>
</html>