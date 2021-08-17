<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<div><h1>Список рецептов</h1></div>
<div>
    <form action="/newRecipe" method="get">
        <button type="submit" name="new">Добавить рецепт</button>
    </form>
    <table border="1">
        <tr>
            <th>Наименование</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="recipe" items="${recipes}">
            <tr>
                <td>${recipe.name}</td>
                <td>
                    <form action="/viewRecipe" method="get">
                        <button type="submit" name="open">Открыть</button>
                        <input type="hidden" value="${recipe.id}" name="id"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>