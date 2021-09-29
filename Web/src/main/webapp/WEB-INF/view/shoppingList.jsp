<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<div><h1>Список продуктов</h1></div>
<div>
    <table border="1">
        <tr>
            <th>Наличие</th>
            <th>Наименование</th>
            <th>Кол-во</th>
            <th>Мера</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="shop" items="${shoppingList}">
        <tr>
            <td>
                <form action="/shoppingList" method="post">
                    <input type="hidden" value="${shop.id}" name="id"/>
                    <input type="checkbox" name="isPurchased" value="${shop.id}"/>
                    <%-- как установить галочку если поле isPurchased == true? --%>
                </form>
            </td>
            <td>${shop.product.name}</td>
            <td>
                <form action="/shoppingList" method="post">
                    <button type="submit" name="save">Сохранить</button>
                    <input type="hidden" value="${shop.id}" name="id"/>
                    <input type="text" name="quantity" placeholder="${shop.quantity}"/>
                </form>
            </td>
            <td>${shop.product.indicator.viewName}</td>
            <td>
                <form action="/shoppingList" method="post">
                    <button type="submit" name="remove">Удалить</button>
                    <input type="hidden" value="${shop.id}" name="id"/>
                </form>
            </td>
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
    <div>
        <a href="products?action=addNewProduct">Добавить новый продукт</a>
        <a href="products?action=viewProducts">Перейти к списку продуктов</a>
    </div>
</div>
</body>
</html>
