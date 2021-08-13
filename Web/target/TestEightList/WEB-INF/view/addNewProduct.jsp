<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<p>${error}</p>
<form action="/newProduct" method="post">
    <input type="text" name="name" placeholder="Введите развание">
    <select name="indicator">
        <option value="0" selected disabled>Выберите единицу измерения</option>
        <c:forEach var="indicator" items="${indicators}">
        <option value="${indicator.id}">${indicator.viewName}</option>
        </c:forEach>
    </select>
    <button type="submit">Сохранить</button>
</form>
<form action="/shoppingList" method="get">
    <button type="submit">Вернуться к списку</button>
</form>
</body>
</html>