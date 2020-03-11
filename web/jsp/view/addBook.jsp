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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Book</title>
    <link href="${pageContext.request.contextPath}/css/comment.css" rel="stylesheet">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <style>
        <%@include file="../../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../../css/grayscale.min.css"%>
        <%@include file="../../vendor/fontawesome-free/css/all.min.css"%>

        .field {clear:both; text-align:right; line-height:25px;}
        label {float:left; padding-right:10px;}
        .main {float:right}
    </style>

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
            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="submit.toFront"/>" style="color: #9fcdff"/>
        </form>
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="GET">
            <input type="hidden" name="command" value="to_library" />
            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="submit.library"/>" style="color: #9fcdff"/>
        </form>
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="logout" />
            <input type="submit" class="btn btn-outline-secondary" name="button" value="<fmt:message key="label.logOut"/>" style="color: #9fcdff"/>
        </form>
    </div>
</nav>


<section id="about" class="about-section text-center">
    <div class="container  align-items-center">
            <form class="form" action="${pageContext.request.contextPath}/controller" method="post">
                <div class="row">
                    <div class="col-lg-8">
                            <br>
                            ${ChangedSave}
                            <br>

                            <div class="row">
                                    <div class="col-lg-2 text-left">
                                        <label for="name" style="color: #9fcdff;"><fmt:message key="label.name"/>:</label>
                                    </div>
                                    <div class="col-lg-8 text-left">
                                        <input type="text" name="name" id="name" placeholder=""/>
                                    </div>
                                    <br>
                                    ${loginError}
                                    <br>
                            </div>
                        <br><br>
                            <div class="row">
                                    <br>
                                    <div class="col-lg-2 text-left">
                                        <label style="color: #9fcdff;text-align: right"><fmt:message key="submit.book.author"/>:</label>
                                    </div>
                                    <div class="col-lg-8 text-left">
                                        <input type="text" name="author" id="author" placeholder=""/>
                                    </div>
                                    <br>
                                    ${usernameError}
                                    <br>
                            </div>
                        <br><br>
                            <div class="row">
                            <br>
                            <div class="col-lg-2 text-left">
                                <label style="color: #9fcdff;text-align: right"><fmt:message key="submit.book.number"/>:</label>
                            </div>
                            <div class="col-lg-8 text-left">
                                <input type="number" name="count" id="count" placeholder=""/>
                            </div>
                            <br>
                            ${usernameError}
                            <br>
                        </div>
                            <br>
                            <br/>
                            ${registrationError}
                            <br/>
                            <br/>
                        </div>
                        <br>
                    </div>
                <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label for="description" style="color: #9fcdff;"><fmt:message key="submit.book.description"/> </label>
                                <textarea id="description" name="description" class="form-control"  rows="6" required="required" data-error="Please,leave us a message."></textarea>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
                <div class="row">
                <div class="col-lg-10 float-right">
                    <input type="hidden" name="command" value="add_book"/>
                    <button class="btn btn-outline-success my-2 pull-right" type="submit">
                        <fmt:message key="submit.save"/>
                    </button>
                </div>
                </div>
            </form>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
</section>



<ctg:end-page/>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript">
    <%@include file="../../vendor/jquery/jquery.min.js"%>
    <%@include file="../../vendor/bootstrap/js/bootstrap.bundle.min.js"%>
    <%@include file="../../vendor/jquery-easing/jquery.easing.min.js"%>
    <%@include file="../../js/grayscale.min.js"%>
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
</body>
</html>
