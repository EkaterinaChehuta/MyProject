<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<table border="1">
    <tr>
        <th>
            <h3>Название</h3>
            <input type="text" name="nameRecipe"/>
        </th>
        <th>
            <h3>Приготовление</h3>
        </th>
    </tr>
    <tr>
        <td>jpg</td>
        <td rowspan="2">
            <textarea name="preparation"></textarea>
        </td>
    </tr>
    <tr>
        <td>
        <h3>Ингредиенты</h3>
        <ul>
            <c:forEach var="ingredient" items="${ingredients}">
                <li>${ingredient.name}</li>
                <input type="text" name="quantity" />
                <li>${ingredient.indicator.viewName}</li>
            </c:forEach>
        </ul>
        <p>
        <form action="/newIngredient" method="post">
            <select name="productId">
                <option value="0" selected disabled>Выберите продукт</option>
                <c:forEach var="product" items="${products}">
                <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
            <button type="submit" name="add">Добавить</button>
        </form>
        </p>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="submit" name="save">Сохранить</button>
        </td>
    </tr>
</table>
</body>
</html>