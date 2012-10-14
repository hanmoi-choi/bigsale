<%@ page isELIgnored="false" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <title>Login - Custom Authentication Sample App</title>

</head>

<body onload='document.loginForm.j_username.focus();'>

<form id="loginForm" name="loginForm" action="j_spring_security_check"
      method="post">
    <c:if test="${not empty param.authfailed}">
			<div id="infomessage" class="errormessage"> Login failed due
				to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>. </div>
    </c:if>
    <c:if test="${not empty param.newpassword}">
			<div id="infomessage" class="errormessage"> Login failed due
				to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>. </div>
    </c:if>
    <c:if test="${not empty param.acclocked}">
			<div id="infomessage" class="errormessage"> Login failed due
				to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>. </div>
    </c:if>
    <c:if test="${not empty param.accdisabled}">
			<div id="infomessage" class="errormessage"> Login failed due
				to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>. </div>
    </c:if>
    <c:if test="${not empty param.loggedout}">
			<div id="infomessage" class="successmessage"> You have been
				successfully logged out. </div>
    </c:if>
    <table>
        <tr>
            <td>Username</td>
            <td><input id="usernameField" type="text" name="j_username"
                       value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/>
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input id="passwordField" type="password" name="j_password"/>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="right"><input type="submit"
                                                 value="Login"/>
            </td>
        </tr>
    </table>
</form>

</body>

</html>
