<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<%@ page import="Domain.Ingredients" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

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
            <c:if test="${ingredient.ingredientsName.id == recipe.ingredientsName.id}">
                <line>${ingredient.product.name}</line>
                <line>${ingredient.quantity}</line>
                <line>${ingredient.product.indicator.viewName}</line></br>
            </c:if>
        </c:forEach>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <form action="editRecipe" method="get">
                <button type="submit">Редактировать рецепт</button>
                <input type="hidden" value="${recipe.id}" name="id"/>
            </form>
        </td>
    </tr>
</table>
<form action="recipe" method="get">
   <button type="submit">Вернуться к списку рецептов</button>
</form>
</body>
</html>
