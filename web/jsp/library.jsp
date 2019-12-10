<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Library</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark rounded-top">
    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"  method="post">
        <li class="nav-item">
            <input type="hidden" name="command" value="to_main"/>
            <button type="submit" class="btn btn-dark">
                <label>Booking Club</label>
            </button>
        </li>
    </form>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        </ul>
        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"  method="post">
            <input type="hidden" name="command" value="logout"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                <label>
                    <fmt:message key="label.logOut"/>
                </label>
            </button>
        </form>
    </div>
</nav>

${user} welcome to BKLibrary

<form method="get" action="${pageContext.request.contextPath}/controller">
    <input class="btn btn-warning" type="submit" value="Активация/Дизактивация" name = "event"/>
    <input class="btn btn-danger" type="submit" value="Удаление" name = "event"/>
    <input class="btn btn-info" type="submit" value="Назначить Одменом/Снять Одменку" name = "event"/>
    <br><br>
    <div class="jumbotron">
        <div class="d-flex justify-content-around"><h1>Books</h1></div>
        <table class="table table-bordered table-hover table-sm">

            <thead class="thead-dark">
            <tr>
                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.name.library.book.name"/></div></th>
                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.name.library.author"/></div></th>
                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.name.library.book.count"/> </div></th>
            </tr>
            </thead>
            <tbody>
            <style>
                .big-checkbox  {width: 10px; height: 10px;}
            </style>
            <c:forEach items="${books}" var="book">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <tr>
                    <input type="hidden" name="command" value="to_book"/>
                    <input type="hidden" name="bookId" value="${book.id}"/>
                    <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${book.name}"/></span></h4></div></td>
                    <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${book.author}"/></span></h4></div></td>
                    <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${book.count}"/> </span></h4></div></td>
                    <th scope="row"><div class="d-flex justify-content-around"><input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="Подробнее" /></div></th>
                </tr>
            </form>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>

















<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</body>
</html>
