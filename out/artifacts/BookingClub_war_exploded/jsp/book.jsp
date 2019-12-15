<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <title>Book</title>
        <link href="${pageContext.request.contextPath}/css/comment.css" rel="stylesheet">
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    </head>
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
            <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"  method="post">
                <li class="nav-item">
                    <input type="hidden" name="command" value="to_library"/>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                        <label><fmt:message key="submit.library"/></label>
                    </button>
                </li>
            </form>
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
You choose - ${bookId}
<div class="container-fluid">
    <div class="row">
        <div class="col-5">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="previous_book"/>
                <input type="hidden" name="bookId" value="${bookId}"/>
                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="Предыдущая" />
            </form>
        </div>
        <div class="col-5">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="next_book"/>
                <input type="hidden" name="bookId" value="${bookId}"/>
                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="Следующая" />
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-2">
            <img src="img/book.jpg"  class="rounded float-left" alt="Me" width="300" height="400"/>
        </div>
        <div class="col-8">
            <p class="text-left">
                <label><fmt:message key="submit.book.name"/>:${name}</label>
                <br><br>
            </p>
            <p class="text-left">
                <label><fmt:message key="submit.book.author"/>:${author}</label>
                <br><br>
            </p>
            <p class="text-left">
                <label><fmt:message key="submit.book.description"/>:${description}</label>
                <br><br>
            </p>
            <p class="text-left">
                <label><fmt:message key="submit.book.count"/>:${count}</label>
                <br><br>
            </p>
            <c:if test="${bookName == null}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <br>
                    <input type="hidden" name="command" value="add_user_book"/>
                    <input type="hidden" name="bookId" value="${bookId}"/>
                    <input type="submit" value="<fmt:message key="label.add.book"/> ${book} ">
                    <br>
                        ${surnameError}
                    <br>
                </form>
            </c:if>
        </div>
    </div>
    <div class="comments">
        <h3 class="title-comments">Комментарии</h3>
    <c:forEach items="${comments}" var="comments">
                <ul class="media-list">
                    <li class="media">
                        <div class="media-body">
                            <div class="media-heading">
                                <div class="author"><c:out value="${comments.author}"/></div>
                                <div class="metadata">
                                    <span class="date"><c:out value="${comments.date}"/></span>
                                </div>
                            </div>
                            <div class="media-text text-justify"><c:out value="${comments.text}"/></div>
                        </div>
                    </li>
                </ul>
    </c:forEach>
    </div>

</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
