<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>HELLO</title>
  </head>
  <body>
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
  </body>
</html>
