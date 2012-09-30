<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hanmoi
  Date: 29/09/12
  Time: 3:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
</head>
<body>
<form:form method="post" action="signup.html">
    <input type="submit" value="Sign Up"/>
</form:form>

<form:form method="post" action="login.html">
    <table>
        <tr>
            <td><form:label path="id" >ID</form:label></td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td><form:label path="Password">Password</form:label></td>
            <td><form:input path="password" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Log in"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>