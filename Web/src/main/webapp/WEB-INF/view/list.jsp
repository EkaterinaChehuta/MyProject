<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<body>
    <h3>Ингредиенты</h3>
        <form action="list" method="post">
            <div id="ingredientList">

            </div>
            <button type="submit">Сохранить</button>
        </form>
        <p>
            <select name="productId" id="productId">
                <option value="0" selected disabled>Выберите продукт</option>
                <c:forEach var="product" items="${products}">
                <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
            <button onclick="addIngredient()" value="add">Добавить</button>
        </p>

<script>
    var productList = [
        <c:forEach items="${products}" var="product">
            {
           id: <c:out value="${product.id}" />,
           name: '<c:out value="${product.name}" />'
           },
        </c:forEach>
    ];

    var ingredientList = [];
    var index = 0;

    function addIngredient(){
        var ingredientId = document.getElementById("productId");

        ingredientList[index] = ingredientId.value;

        document.getElementById("ingredientList").innerHTML =
        '<ul name="ingredientList"><c:forEach var="ingredient" items="{ingredientList}"><li>${ingredient}</li></c:forEach></ul>';

        index++;
    }
</script>
</body>
</html>