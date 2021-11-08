<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<table border="1">
    <tr>
        <th>
            <h1>Рецепт ${recipe.name}</h1>
            <input type="text" name="name" placeholder="Введите новое название" form="save"/>
        </th>
        <th>
            <h3>Приготовление</h3>
        </th>
    </tr>
    <tr>
        <td>jpg</td>
        <td rowspan="2">
            <textarea name="preparation" form="save">${recipe.preparation}</textarea>
        </td>
    </tr>
    <tr>
        <td>
        <h3>Ингредиенты</h3>
        <ul>
            <c:forEach var="ingredient" items="${ingredients}">
                <li>${ingredient.product.name}
                  <input type="text" value="${ingredient.quantity}" />
                    ${ingredient.product.indicator.viewName}</li>
            </c:forEach>
        </ul>
        <p>
            <select name="product" id="product">
                <option value="0" selected disabled>Выберите продукт</option>
                <c:forEach var="product" items="${products}">
                <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
            <button onclick="addNewIngredient()">Добавить</button>
        </p>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
          <form action="editRecipe?action=save&id=${recipe.id}" method="post" id="save">
            <button type="submit">Сохранить изменения</button>
          </form>
        </td>
    </tr>
</table>
<form action="editRecipe" method="get">
    <button type="submit">Отменить изменения</button>
    <input type="hidden" value="${recipe.id}" name="id"/>
</form>
<form action="recipe" method="get">
   <button type="submit">Вернуться к списку рецептов</button>
</form>
</body>
</html>
