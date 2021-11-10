<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
    <p>${error}</p>
    <form action="editProduct?id=${product.id}" method="post">
        <input type="text" name="name" placeholder="${product.name}">
        <select name="indicator" >
            <option value="0" selected disabled>${product.indicator.name}</option>
            <c:forEach var="indicator" items="${indicators}">
            <option value="${indicator.id}">${indicator.viewName}</option>
            </c:forEach>
        </select>
        <button type="submit">Сохранить</button>
    </form>
    <form action="products" method="get">
        <button type="submit">Вернуться к списку продуктов</button>
    </form>
</body>
</html>
