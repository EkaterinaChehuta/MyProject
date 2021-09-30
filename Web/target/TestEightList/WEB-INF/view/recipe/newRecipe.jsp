<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<% Product[] ingredients = new Product[]{} %>

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
<<<<<<< HEAD:Web/src/main/webapp/WEB-INF/view/recipe/newRecipe.jsp
            <c:forEach var="ingredient" items="${ingredients}">
                <li>${ingredient.name}</li>
                <input type="text" name="quantity" />
            </c:forEach>
        </ul>
        <p>
        <form action="/newRecipe" method="post">
=======
            <%
                for(Product product: ingredients){
                    out.println("<li>" + product.name + "</li>");
                }
             %>
        </ul>
        <p>
>>>>>>> parent of 07f5c67 (Добавлены тесты для модулей Product и Recipe):Web/src/main/webapp/WEB-INF/view/newRecipe.jsp
            <select name="productId">
                <option value="0" selected disabled>Выберите продукт</option>
                <c:forEach var="product" items="${products}">
                <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
            <button type="submit" name="add">Добавить</button>
        </p>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="submit">Сохранить</button>
        </td>
    </tr>

</table>
</body>
</html>