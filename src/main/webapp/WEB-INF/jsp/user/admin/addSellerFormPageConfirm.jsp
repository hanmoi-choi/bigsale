<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Page</title>
</head>
<body>
<form:form method="POST" commandName="user">
<%--<form:form commandName="address"/>--%>
    <table>
        <tr>
            <td>User ID :</td>
            <td>${user.userId}</td>
        </tr>

        <tr>
            <td>First Name :</td>
            <td>${user.firstName}</td>
        </tr>

        <tr>
            <td>Second Name :</td>
            <td>${user.lastName}</td>
        </tr>

        <tr>
            <td>E-mail :</td>
            <td>${user.email}</td>
        </tr>

        <tr><td><div>Address</div></td></tr>
        <tr>
            <td>Street :</td>
            <td>${user.address.street}</td>
        </tr>
        <tr>
            <td>City :</td>
            <td>${user.address.city}</td>
        </tr>
        <tr>
            <td>State :</td>
            <td>${user.address.state}</td>
        </tr>
        <tr>
            <td>Zip Code :</td>
            <td>${user.address.zipcode}</td>
        </tr>

        <%--<tr>--%>
            <%--<td>Address Street: </td>--%>
            <%--<td>${address.street}</td>--%>
        <%--</tr>--%>
        <tr>
            <td colspan="3">
                <input type="submit" value="Cancel" name="_cancel" />
                <input type="submit" value="Prev" name="_target1" />
                <input type="submit" value="Confirm" name="_finish" />
                <input type="hidden" value="2" name="_page">
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
