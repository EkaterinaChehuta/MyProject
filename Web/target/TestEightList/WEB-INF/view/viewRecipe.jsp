<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<table border="1">
    <tr>
        <th>
            <h1>Рецепт ${recipe.name}</h1>
        </th>
        <th>
            <h3>Приготовление</h3>
        </th>
    </tr>
    <tr>
        <td>jpg</td>
        <td rowspan="2">
            <p>${recipe.preparation}</p>
        </td>
    </tr>
    <tr>
        <td>
        <h3>Ингредиенты</h3>
        <c:forEach var="ingredient" items="${ingredients}">
            <li>${ingredient.product.name} ${ingredient.quantity} ${ingredient.product.indicator.viewName}</li>
        </c:forEach>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <form action="/recipesList" method="get">
                <button type="submit">Вернуться к списку</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>