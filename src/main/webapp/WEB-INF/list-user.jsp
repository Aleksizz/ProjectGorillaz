<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="parts/header.jsp" %>
<body>
<div class="container">

    <c:forEach var="user" items="${requestScope.users}">
        <img src="images/${user.image}" alt="images/${user.image}" width="100px">
        ${user.login}  <br>
        <a href="edit-user?id=${user.id}">Edit user</a>  <br>
        <a href="delete-user?id=${user.id}">Delete user</a>  <br> <br>
    </c:forEach>

</div>
<%@include file="parts/footer.jsp" %>
