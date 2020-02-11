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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Book</title>
    <link href="${pageContext.request.contextPath}/css/comment.css" rel="stylesheet">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <style>
        <%@include file="../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../css/grayscale3.css"%>
        <%@include file="../vendor/fontawesome-free/css/all.min.css"%>

        .field {clear:both; text-align:right; line-height:25px;}
        label {float:left; padding-right:10px;}
        .main {float:left}
        .book h1{
            font-family: 'Varela Round';
            font-size: 2.5rem;
            line-height: 2.5rem;
            letter-spacing: 0.8rem;
            background: -webkit-linear-gradient(rgba(255, 255, 255, 0.9), rgba(255, 255, 255, 0));
            -webkit-text-fill-color: transparent;
            -webkit-background-clip: text;
        }
        .book h4{
            font-family: 'Varela Round';
        }
    </style>
    <link href="../css/grayscale3.css" rel="stylesheet">
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
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="GET">
            <input type="hidden" name="command" value="to_library" />
            <button type="submit" class="btn btn-outline-secondary"><label><fmt:message key="submit.library"/></label></button>
        </form>
<c:if test="${login != null}">
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="logout" />
            <button type="submit" class="btn btn-outline-secondary"><label><fmt:message key="label.logOut"/></label></button>
        </form>
</c:if>
    </div>
</nav>


<section id="about" class="about-section text-center">
    <div class="container  align-items-center">
        <div class="row justify-content-center">
<c:if test="${ not empty bookSettings and bookSettings eq 'see' }">
    <div class="col-lg-4">
                    <img class="img-fluid"  src="img/book.png" alt="" style="border-radius: 30px" width="400" height="400" />
                </div>
    <div class="col-lg-8 text-center book">
                    <h1>${userName} ${surname}</h1>
                    <div class="row">
                        <div class="col-lg-6 text-right" style="color: rgba(138,136,137,0.99)">
                            <h4>
                                <br><br>
                                <fmt:message key="submit.book.name"/>
                                <br><br>
                                <fmt:message key="submit.book.author"/>
                                <br><br>
                                <fmt:message key="submit.book.count"/>
                                <br><br>
                            </h4>

                        </div>
                        <div class="col-lg-6 text-left" style="color: rgba(138,136,137,0.99)">
                            <h4>
                                <br><br>
                                    ${name}
                                <br><br>
                                    ${author}
                                <br><br>
                                    ${count}

                            </h4>
                            <div class="text-right">
                                <c:if test="${userBookId == null and login != null}">
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
                    </div>
                </div>

    <div class="col-lg-12">
        <div class="row book">

            <label style="color: rgba(138,136,137,0.99); text-align: left">
                <br><br>
            <h4 style="color: rgba(138,136,137,0.99)">
                <fmt:message key="submit.book.description"/>:
            </h4>
            </label>
        </div>
        <h4 style="color: rgba(138,136,137,0.99)">
                ${description}
        </h4>
    </div>

        <div class="col-6">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="previous_book"/>
                <input type="hidden" name="bookId" value="${bookId}"/>
                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="<fmt:message key="submit.previous.page"/>" />
            </form>
        </div>
        <div class="col-6">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="next_book"/>
                <input type="hidden" name="bookId" value="${bookId}"/>
                <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="<fmt:message key="submit.next.page"/>" />
            </form>
        </div>
        <c:if test="${not empty role and role eq 'ADMIN' and bookSettings eq 'see'}">
            <div class="col-12 fa-align-center">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="to_book_settings"/>
                    <input type="hidden" name="bookId" value="${bookId}"/>
                    <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="<fmt:message key="submit.book.settings"/>" />
                </form>
            </div>
        </c:if>
        <c:if test="${not empty role and role eq 'ADMIN' and bookSettings eq 'settings'}">
            <div class="col-12 fa-align-center">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="to_book"/>
                    <input type="hidden" name="bookId" value="${bookId}"/>
                    <input class="btn btn-outline-success my-2 my-sm-0" type="submit" name="id" value="<fmt:message key="submit.go.to.book"/>" />
                </form>
            </div>
        </c:if>


    <div class="row">
        <div>
            <div class="comments">
                <h3 class="title-comments" style="color: #1c7430">Комментарии</h3>
                <c:forEach items="${comments}" var="comments">
                    <ul class="media-list" style="color: #0f6674">
                        <li class="media" style="color: #0f6674">
                            <div class="media-body" style="color: #0f6674">
                                <div class="media-heading" >
                                    <div class="author" style="color: #9fcdff"><c:out value="${comments.author}"/></div>
                                    <div class="metadata">
                                        <span class="date" style="color: #9fcdff"><c:out value="${comments.date}"/></span>
                                    </div>
                                </div>
                                <div class="card" style="width: 60rem;">
                                    <div class="card-body">
                                        <h4 class="card-title " style="color: #1c7430; text-align: left"><c:out value="${comments.header}"/></h4>
                                        <p class="card-text" style="color: #1c7430; text-align: left"><c:out value="${comments.text}"/></p>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <br>
                </c:forEach>
            </div>
            <c:if test="${login == null}">

                <h5 class="text-center">
                    <label>
                        <fmt:message key="label.book.request.log.one"/>
                        <a href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key="label.login"/> </a>
                        <fmt:message key="label.book.request.log.two"/>
                    </label>
                </h5>
            </c:if>
        </div>
    </div>
    <br><br><br>
    <c:if test="${login != null}">

            <div class="container" >
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control"  name="commentHeader" id="commentHeader" placeholder="<fmt:message key="label.add.header.text"/>">
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" name="text" id="text"  rows="5" placeholder="<fmt:message key="label.add.comment.text"/>"></textarea>
                    </div>
                    <input type="hidden" name="command" value="add_book_comment"/>
                    <input type="hidden" name="bookId" value="${bookId}"/>
                    <input type="submit" value="<fmt:message key="label.add.comment"/> ${book} ">
                </form>
            </div>
        </c:if>
</c:if>
<c:if test="${ not empty bookSettings and bookSettings eq 'settings' }">
    <div class="col-lg-4" style="color: red">
        <form class="form-inline" action="${pageContext.request.contextPath}/controller"  enctype="multipart/form-data" method="post">
            <input type="hidden" name="command" value="change_profile_image"/>
            <input type="file" name="image" accept="image/jpeg,image/png" class="btn btn-outline-secondary" required/>
            <button class="btn btn-outline-success my-0" type="submit">
                <label>
                    <fmt:message key="submit.save"/>
                </label>
            </button>
        </form>
    </div>
    <div class="col-lg-8">
        <div class="main">
            <br>
                ${ChangedSave}
            <br>


            <div class="field">
                <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                    <br>
                    <input type="hidden" name="command" value="change_book_name"/>
                    <input type="hidden" name="bookId" value="${bookId}"/>
                    <div class="col-lg-2 text-left">
                        <label for="name" style="color: #9fcdff;"><fmt:message key="label.name"/>:</label>
                    </div>
                    <div class="col-lg-8 text-left">
                        <input type="text" name="name" id="name" placeholder="${name}"/>
                    </div>
                    <div class="col-lg-2 float-right">
                        <button class="btn btn-outline-success my-2 pull-right" type="submit">
                            <label>
                                <fmt:message key="submit.save"/>
                            </label>
                        </button>
                    </div>
                    <br>
                        ${loginError}
                    <br>
                </form>
            </div>


            <div class="field">
                <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                    <br>
                    <input type="hidden" name="command" value="change_book_author"/>
                    <input type="hidden" name="bookId" value="${bookId}"/>
                    <div class="col-lg-2 text-left">
                        <label style="color: #9fcdff;text-align: right"><fmt:message key="submit.book.author"/>:</label>
                    </div>
                    <div class="col-lg-8 text-left">
                        <input type="text" name="author" id="author" placeholder="${author}"/>
                    </div>
                    <div class="col-lg-2 float-right">
                        <button class="btn btn-outline-success my-2 pull-right" type="submit">
                            <label>
                                <fmt:message key="submit.save"/>
                            </label>
                        </button>
                    </div>
                    <br>
                        ${usernameError}
                    <br>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-12">
        <div class="row">
            <label style="color: #9fcdff;text-align: right"><fmt:message key="submit.book.description"/>:</label>
        </div>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="change_book_description"/>
            <input type="hidden" name="bookId" value="${bookId}"/>
            <div class="row">
                <div class="col-md-10">
                    <div class="form-group">
                        <label for="description"><fmt:message key="submit.book.description"/> </label>
                        <textarea id="description" name="description" class="form-control"  rows="6" required="required" data-error="Please,leave us a message.">
                                ${description}
                        </textarea>
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="col-lg-10 float-right">
                    <button class="btn btn-outline-success my-2 pull-right" type="submit">
                        <fmt:message key="submit.save"/>
                    </button>
                </div>
            </div>
        </form>
    </div>

    <br>
    <br/>
    ${registrationError}
    <br/>
    <br/>
</c:if>
        </div>
        <br>

        </div>
</section>




<!-- Bootstrap core JavaScript -->
<script type="text/javascript">
    <%@include file="../vendor/jquery/jquery.min.js"%>
    <%@include file="../vendor/bootstrap/js/bootstrap.bundle.min.js"%>
    <%@include file="../vendor/jquery-easing/jquery.easing.min.js"%>
    <%@include file="../js/grayscale.min.js"%>
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
</body>
</html>
