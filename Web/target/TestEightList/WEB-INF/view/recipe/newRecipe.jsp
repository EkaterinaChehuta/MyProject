<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<%
    List<Ingredients> ingredients = ${ingredients};
%>

<html>
<body>
<table border="1">
<form action="newRecipe?action=save" method="post">
    <tr>
        <th>
            <h3>Название</h3>
            <input type="text" name="name"/>
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
            <c:forEach var="ingredient" items="ingredients">
                <li>${ingredient.name}</li>
                <input type="text" name="quantity" />
            </c:forEach>
        </ul>
        <p>
            <select name="productId">
                <option value="0" selected disabled>Выберите продукт</option>
                <c:forEach var="product" items="${products}">
                <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
            <button type="submit">Добавить</button>
        </p>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="submit">Сохранить</button>
        </td>
    </tr>
</form>
</table>
</body>
</html>