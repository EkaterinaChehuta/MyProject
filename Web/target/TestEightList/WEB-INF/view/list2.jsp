<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List;" %>

<script>
  var namesOut = new Array();

    function addNew() {
        namesOut.push(document.getElementById('name').value);
        html = '<ul>';
        namesOut.forEach(function(item, i, namesOut) {
          html += '<li>'+ item +'</li>';
          html += '<input type="hidden" id="names" value="' + item + '"name="names"/>';
        });
        html += '</ul>';
        document.getElementById('namesList').innerHTML = html;
    }
</script>

<html>
<body>
    <h3>Ингредиенты</h3>
        <form action="list" method="post">
            <div id="namesList"></div>
            <button type="submit" value="save">Сохранить</button>
        </form>
        <p>
            <select name="name" id="name">
                <option value="0" selected disabled>Выберите продукт</option>
                <c:forEach var="name" items="${names}">
                <option value="${name.id}">${name.name}</option>
                </c:forEach>
            </select>
              <%-- <input type="text" id="name" /> --%>
              <button onclick="addNew()">Добавить</button>
        </p>
</body>
</html>
