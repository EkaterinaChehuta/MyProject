<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<form action="/newRecipe" method="post">
<table border="1">
    <tr>
        <th>
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
        <li></li>
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