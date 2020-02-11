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
            <input type="hidden" name="command" value="to_registration" />
            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="submit.toRegistration"/>" style="color: #9fcdff"/>
        </form>
    </div>
</nav>


<section id="about" class="about-section text-center">

    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <div class="form-group row" >
                        <input type="hidden"  name="command" value="login"/>
                        <label class="col-sm-4 col-form-label float-sm-right" for="login" style="color: #9fcdff">
                            <fmt:message key="label.login"/>
                        </label>
                        <div class="col-sm-8 ">
                        <input type="text" name="login" id="login" class="float-sm-left">
                        </div>
                    </div>
                    <div class="form-group row">
                    <label class="col-sm-4 col-form-label float-sm-right"   for="password" style="color: #9fcdff">
                        <fmt:message key="label.password"/>
                    </label>
                        <div class="col-sm-8">
                        <input type="password" name="password" id="password" class="float-sm-left">
                        </div>
                    </div>
                    <br>
                    <br>
                    ${loginError}
                    <br>
                    <br>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="submit.logIn"/>" style="color: #9fcdff"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<section class="contact-section bg-black" id="contact">
    <div class="container">

        <div class="row">

            <div class="col-md-4 mb-3 mb-md-0">
                <div class="card py-4 h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-map-marked-alt text-primary mb-2"></i>
                        <h4 class="text-uppercase m-0">Address</h4>
                        <hr class="my-4">
                        <div class="small text-black-50">4923 Market Street, Orlando FL</div>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3 mb-md-0">
                <div class="card py-4 h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-envelope text-primary mb-2"></i>
                        <h4 class="text-uppercase m-0">Email</h4>
                        <hr class="my-4">
                        <div class="small text-black-50">
                            <a href="#">hello@yourdomain.com</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3 mb-md-0">
                <div class="card py-4 h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-mobile-alt text-primary mb-2"></i>
                        <h4 class="text-uppercase m-0">Phone</h4>
                        <hr class="my-4">
                        <div class="small text-black-50">+1 (555) 902-8832</div>
                    </div>
                </div>
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
