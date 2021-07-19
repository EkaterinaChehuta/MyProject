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
        <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <form>
                    <input type="checkbox">
                </form>
            </td>
            <td>${product.name}</td>
            <td>
                <form>
                    <input type="text" name="quantity" />
                </form>
            </td>
            <td>${product.indicator}</td>
        </tr>
        </c:forEach>
    </table>
</div>
<div>
    <form>
        <select>
            <option>name</option>
        </select>
        <button type="submit" name="add">Добавить в список</button>
    </form>
    <form action="/shoppingList" method="post">
        <button type="submit">Добавить новый продукт</button>
    </form>
</div>
</body>
</html>