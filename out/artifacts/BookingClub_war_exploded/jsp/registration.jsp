<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <!-- Bootstrap core CSS -->
    <style>
        <%@include file="../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../css/grayscale.min.css"%>
        <%@include file="../vendor/fontawesome-free/css/all.min.css"%>
    </style>
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

</head>
<body id="page-top">
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="GET">
            <input type="hidden" name="command" value="to_front_page" />
            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="submit.toFront"/>" style="color: #9fcdff"/>
        </form>
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="GET">
            <input type="hidden" name="command" value="to_login" />
            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="submit.toLogin"/>" style="color: #9fcdff"/>
        </form>
    </div>
</nav>


<section id="about" class="about-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <div class="form-group row" >
                        <input type="hidden" name="command" value="registration"/>
                        <label class="col-sm-4 col-form-label float-sm-right" for="login" style="color: #9fcdff">
                            <fmt:message key="label.login"/>
                        </label>
                        <div class="col-sm-8 ">
                            <input type="text" name="login" id="login" class="float-sm-left"pattern="[A-Za-zА-Яа-я\d\-\_]{0,45}"
                                   maxlength="45"
                                   title="<fmt:message key="invalid.login"/>" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right"   for="password" style="color: #9fcdff">
                            <fmt:message key="label.password"/>
                        </label>
                        <div class="col-sm-8">
                            <input type="password" name="password" id="password" class="float-sm-left"
                                   pattern="(?=.*[A-ZА-Я])(?=.*[a-zа-я])(?=.*\d)([A-Za-zА-Яа-я\d]{8,45})"
                                   maxlength="45"
                                   title = "<fmt:message key="invalid.registration.password"/>"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right"   for="email" style="color: #9fcdff">
                            <fmt:message key="label.email"/>
                        </label>
                        <div class="col-sm-8">
                            <input  type="text" name="email" id="email" class="float-sm-left"
                                    pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}"
                                    maxlength="45"
                                    title = "<fmt:message key="invalid.registration.email"/>"
                                    required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right"   for="name" style="color: #9fcdff">
                            <fmt:message key="label.name"/>
                        </label>
                        <div class="col-sm-8">
                            <input type="text" name="name" id="name" class="float-sm-left"
                                   pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                   maxlength="45"
                                   title = "<fmt:message key="invalid.name"/>"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group row">
                    <label class="col-sm-4 col-form-label float-sm-right"   for="surname" style="color: #9fcdff">
                        <fmt:message key="label.surname"/>
                    </label>
                    <div class="col-sm-8">
                        <input type="text" name="surname" id="surname" class="float-sm-left"
                               pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                               maxlength="45"
                               title = "<fmt:message key="invalid.name"/>"
                               required/>
                    </div>
            </div>
                    <br>
                    <br>
                    ${loginError}
                    <br>
                    <br>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="submit.toRegistration"/>" style="color: #9fcdff"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<!-- Footer -->

<!-- Bootstrap core JavaScript -->
<!-- Bootstrap core JavaScript -->
<script type="text/javascript">
    <%@include file="../vendor/jquery/jquery.min.js"%>
    <%@include file="../vendor/bootstrap/js/bootstrap.bundle.min.js"%>
    <%@include file="../vendor/jquery-easing/jquery.easing.min.js"%>
    <%@include file="../js/grayscale.min.js"%>
</script>


</body>
</html>
