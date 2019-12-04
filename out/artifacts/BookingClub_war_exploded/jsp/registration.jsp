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
REGISTRATION

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="registration"/>
    <label for="login"><fmt:message key="label.login"/>:
        <input type="text" name="login" id="login" required/>
    </label>
    <br><br>
    <label for="password"><fmt:message key="label.password"/>:
        <input type="password" name="password" id="password"  required/>
    </label>
    <br><br>
    <label for="email"><fmt:message key="label.email"/>:
    <input type="text" name="email" id="email"
           pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}"  title="<fmt:message key="invalid.registration.email"/>" required/>
    </label>
    <br><br>
    <label><fmt:message key="label.name"/>:
        <input type="text" name="name" id="name"  required/>
    </label>
    <br><br>
    <label><fmt:message key="label.surname"/>:
        <input type="text" name="surname" id="surname"  required/>
    </label>
    <br>
    <br/>
    ${registrationError}
    <br/>
    <br/>
    <input type="submit" value="<fmt:message key="submit.save"/>" />
</form>
</body>
</html>