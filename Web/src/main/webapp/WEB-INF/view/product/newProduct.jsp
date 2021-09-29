<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<p>${error}</p>
<form action="products?action=save" method="post">
    <input type="text" name="name" placeholder="Введите название">
    <select name="indicator">
        <option value="0" selected disabled>Выберите единицу измерения</option>
        <c:forEach var="indicator" items="${indicators}">
        <option value="${indicator.id}">${indicator.viewName}</option>
        </c:forEach>
    </select>
    <button type="submit">Сохранить</button>
</form>
<a href="shoppingList">Вернуться к списку покупок</a>
</body>
</html>