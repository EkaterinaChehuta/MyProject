<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<script type="text/javascript">
  var ingredientsList = new Array();

    function addNewIngredient() {
        ingredientsList.push(document.getElementById('product').value);
        html = '<ul>';
        ingredientsList.forEach(function(ingredient, i, ingredientsList) {
          html += '<div><li>' +  ingredient + '</li>';
          html += '<input type="hidden" id="ingredients" value="' + ingredient + '" name="ingredients" form="save"/>';
        });
        html += '</ul>';
        document.getElementById('ingredients').innerHTML = html;
    }
</script>

<html>
<body>
<table border="1">
    <tr>
        <th>
            <h3>Название</h3>
            <input type="text" name="name" form="save" />
        </th>
        <th>
            <h3>Приготовление</h3>
        </th>
    </tr>
    <tr>
        <td>jpg</td>
        <td rowspan="2">
            <textarea name="preparation" form="save"></textarea>
        </td>
    </tr>
    <tr>
        <td>
        <h3>Ингредиенты</h3>
        <div id="ingredients"></div>
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
          <form action="newRecipe" method="post" id="save">
            <button type="submit">Сохранить</button>
          </form>
        </td>
    </tr>
</table>
<form action="recipe" method="get">
   <button type="submit">Вернуться к списку рецептов</button>
</form>
</body>
</html>
