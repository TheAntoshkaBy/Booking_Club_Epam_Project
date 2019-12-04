<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Title</title>
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
                    <input type="hidden" name="command" value="settings"/>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                        <label><fmt:message key="submit.Settings.profile"/></label>
                    </button>
                </li>
            </form>
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
    WELCOME ${user}
    <div class="container-fluid">
        <div class="row">
            <div class="col-2">
                <img src="img/me.jpg"  class="rounded float-left" alt="Me" width="300" height="400"/>
            </div>
            <c:if test="${ not empty type and type eq 'see' }">
            <div class="col-9">
                <p class="text-left">
                    <label><fmt:message key="label.name"/>:${user}</label>
                    <br><br>
                </p>
                <p class="text-left">
                    <label><fmt:message key="label.surname"/>:${surname}</label>
                    <br><br>
                </p>
                <p class="text-left">
                    <label><fmt:message key="label.email"/>:${email}</label>
                    <br><br>
                </p>
                <p class="text-left">
                    <label><fmt:message key="label.login"/>:${login}</label>
                    <br><br>
                </p>
                <p class="text-left">
                    <label><fmt:message key="label.status"/>:${status}</label>
                    <br><br>
                </p>
                <p class="text-left">
                    <label><fmt:message key="label.role"/>:${role}</label>
                    <br><br>
                </p>
                <p class="text-left">
                <label><fmt:message key="label.money.balance"/>:${money}</label>
                <br><br>
                </p>
                <p class="text-left">
                    <label><fmt:message key="label.book"/>:${book}</label>
                    <br><br>
                </p>
            </div>
            </c:if>
            <c:if test="${not empty type and type eq 'change'}">
            <div class="col-9">
                <br>
                    ${ChangedSave}
                <br>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <br>
                <input type="hidden" name="command" value="change_profile_login"/>
                <label for="login"><fmt:message key="label.login"/>:
                    <input type="text" name="login" id="login" placeholder="${login}"/>
                </label>
                <input type="submit" value="<fmt:message key="submit.save"/>" >
                <br>
                    ${loginError}
                <br>
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <br>
                <input type="hidden" name="command" value="change_profile_name"/>
                <label><fmt:message key="label.name"/>:
                    <input type="text" name="name" id="name" placeholder="${name}"/>
                </label>
                <input type="submit" value="<fmt:message key="submit.save"/> ">
                <br>
                    ${usernameError}
                <br>
            </form>
         <form action="${pageContext.request.contextPath}/controller" method="post">
                <br>
                <input type="hidden" name="command" value="change_profile_surname"/>
                <label><fmt:message key="label.surname"/>:
                    <input type="text" name="surname" id="surname" placeholder="${surname}" />
                </label>
                <input type="submit" value="<fmt:message key="submit.save"/> ">
                <br>
                ${surnameError}
                <br>
            </form>
            </div>
            <br>
                <br/>
                    ${registrationError}
                <br/>
                <br/>
            </c:if>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
