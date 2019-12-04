<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>BookingClub</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="login"/>
    <label for="login"><fmt:message key="label.login"/>
        <input type="text" name="login" id="login">
    </label>
    <br><br>
    <label for="password"><fmt:message key="label.password"/>
        <input type="password" name="password" id="password">
    </label>
    <br>
    <br>
    ${loginError}
    <br>
    <br>
    <input type="submit" value="<fmt:message key="submit.logIn"/>">
</form>
</body>
</html>
