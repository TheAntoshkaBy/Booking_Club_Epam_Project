<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Grayscale - Start Bootstrap Theme</title>

    <style>
        <%@include file="../../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../../css/grayscale3.css"%>
        <%@include file="../../vendor/fontawesome-free/css/all.min.css"%>
        input {
            height: 20px;
        }

        i.fa {
            width: 26px;
            height: 26px;
            line-height: 26px;
            text-align: center;
            margin-right: -26px;
            position: relative;
            z-index: 1;
            float: left;
        }

        i.fa + input {
            padding-left: 26px;
        }
    </style>
    <link href="../../css/grayscale3.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
</head>

<body id="page-top">
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <form class="form-inline"  name="Simple" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="to_front_page"/>
            <button type="submit" class="btn btn-outline-secondary"><label><fmt:message key="submit.toFront"/></label></button>
        </form>
        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"  method="get">
            <input type="hidden" name="command" value="to_reading_plans"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                <label>
                    <fmt:message key="submit.reading.plan"/>
                </label>
            </button>
        </form>
        <c:if test="${login != null}">
            <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"  method="post">
                <input type="hidden" name="command" value="to_main"/>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                    <label>
                        <fmt:message key="submit.personal.page"/>
                    </label>
                </button>
            </form>
        </c:if>
        <c:if test="${login == null}">
            <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"  method="post">
                <input type="hidden" name="command" value="to_login"/>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                    <label>
                        <fmt:message key="submit.logIn"/>
                    </label>
                </button>
            </form>
        </c:if>
        <c:if test="${login != null}">
            <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="logout" />
                <button type="submit" class="btn btn-outline-secondary"><label><fmt:message key="label.logOut"/></label></button>
            </form>
        </c:if>
    </div>
</nav>


<section id="about" class="about-section text-center">

    <div class="masthead3">
        <div class="container-fluid  align-items-center">
            <div class="row justify-content-center ">
                <form method="get" action="${pageContext.request.contextPath}/controller">
                    <div class="jumbotron">
                        <div class="d-flex justify-content-around"><h2>Books</h2></div>
                        <table class="table table-bordered table-hover">

                            <thead class="thead-dark ">
                            <tr>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="label.login"/></div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="label.name"/></div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="label.surname"/> </div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="label.status"/> </div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="label.money"/> </div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="label.userRoleType"/> </div></th>
                            </tr>
                            </thead>
                            <tbody>
                            <style>
                                .big-checkbox  {width: 10px; height: 10px;}
                            </style>
                            <c:forEach items="${allUsers}" var="user">
                                <c:if test="${user.isActive}">
                                    <tr class="table-success">
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.login}"/></span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${user.name}"/></span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.surname}"/> </span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.status}"/> </span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.moneyBalance}"/> </span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.role}"/> </span></h4></div></td>
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="blocking_user"/>
                                            <input type="hidden" name="login" value="${user.login}"/>
                                            <th scope="row"><div class="d-flex justify-content-around"><input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="<fmt:message key="label.block"/>" /></div></th>
                                        </form>
                                    </tr>
                                        </c:if>
                                        <c:if test="${not user.isActive}">
                                        <tr class="table-success">
                                            <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.login}"/></span></h4></div></td>
                                            <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${user.name}"/></span></h4></div></td>
                                            <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.surname}"/> </span></h4></div></td>
                                            <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.status}"/> </span></h4></div></td>
                                            <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.moneyBalance}"/> </span></h4></div></td>
                                            <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"><c:out value="${user.role}"/> </span></h4></div></td>
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="do_user_active"/>
                                                <input type="hidden" name="login" value="${user.login}"/>
                                                <th scope="row"><div class="d-flex justify-content-around"><input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="<fmt:message key="label.unlock"/>" /></div></th>
                                            </form>
                                        </tr>
                                        </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
            <div class="row justify-content-center no-gutters mx-auto text-center">
                <h1 class="mx-auto my-0 text-uppercase" style="color: #1c7430"><fmt:message key="text.site.name"/></h1>
            </div>
            <br><br><br>
        </div>
    </div>

</section>



<ctg:end-page/>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript">
    <%@include file="../../vendor/jquery/jquery.min.js"%>
    <%@include file="../../vendor/bootstrap/js/bootstrap.bundle.min.js"%>
    <%@include file="../../vendor/jquery-easing/jquery.easing.min.js"%>
    <%@include file="../../js/grayscale.min.js"%>
</script>

</body>
</html>
