<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
<div><h1>Список продуктов</h1></div>
    <table border="1">
        <tr>
            <th>Наименование</th>
            <th>Единица измерения</th>
            <th>Действие</th>
            </tr>
            <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.indicator.name}</td>
                <td>
                    <form action="products?action=remove&id=${product.id}" method="post">
                        <button type="submit">Удалить</button>
                    </form>
                    <form action="products" method="get">
                        <button type="submit" name="action" value="editProduct">Изменить</button>
                        <input type="hidden" value="${product.id}" name="id"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="products?action=addNewProduct">Добавить новый продукт</a>
</body>
</html>