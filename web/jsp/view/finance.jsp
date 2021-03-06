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
        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"  method="post">
            <input type="hidden" name="command" value="to_library"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                <label>
                    <fmt:message key="submit.library"/>
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
                        <table class="table table-bordered table-hover table-sm">

                            <thead class="thead-dark">
                            <tr>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.finances.id"/></div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.finances.balance"/></div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.finances.type"/></div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.finances.author"/></div></th>
                                <th scope="col"><div class="d-flex justify-content-around"><fmt:message key="table.finances.date"/></div></th>
                            </tr>
                            </thead>
                            <tbody>
                            <style>
                                .big-checkbox  {width: 10px; height: 10px;}
                            </style>
                            <c:forEach items="${financeList}" var="finances">
                                    <tr>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${finances.idOperation}"/></span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${finances.balance}"/></span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${finances.type}"/></span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${finances.authorLogin}"/></span></h4></div></td>
                                        <td><div class="d-flex justify-content-around"><h4><span class="badge badge-outline-primary"> <c:out value="${finances.date}"/></span></h4></div></td>
                                    </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
            <div class="row justify-content-center no-gutters mx-auto text-center">
                <h1 class="mx-auto my-0 text-uppercase" style="color: #1c7430"><fmt:message key="text.site.name"/></h1>
                <br><br><br>
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
