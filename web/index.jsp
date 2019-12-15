<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
      <title>HELLO</title>
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
  <form method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="locale"/>
    <input type="submit" value="LOCALE"/>
  </form>
  <form name="Simple" action="${pageContext.request.contextPath}/controller" method="GET">
      <input type="hidden" name="command" value="to_registration" />
    <input type="submit" name="button" value="<fmt:message key="submit.toRegistration"/>"/>
  </form>
  <form name="Simple" action="${pageContext.request.contextPath}/controller" method="GET">
      <input type="hidden" name="command" value="to_login" />
    <input type="submit" name="button" value="<fmt:message key="submit.toLogin"/>"/>
  </form>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
  </body>
</html>
