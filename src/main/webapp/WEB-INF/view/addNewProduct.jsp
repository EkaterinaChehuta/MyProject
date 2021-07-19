<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<form action="/newProduct" method="post">
    <input type="text" name="name" placeholder="Введите развание">
    <select name="indicator">
        <option value="0" selected disabled>Выберите единицу измерения</option>
        <option>кг</option>
        <option>л</option>
        <option>шт</option>
    </select>
    <button type="submit">Сохранить</button>
</form>
<form action="/shoppingList" method="get">
    <button type="submit">Вернуться к списку</button>
</form>
</body>
</html>