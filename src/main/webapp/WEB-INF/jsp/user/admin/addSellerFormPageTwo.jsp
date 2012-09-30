<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Page</title>
</head>
<body>
<h2> Enter Address</h2>
<form:form method="POST" commandName="user">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <table>
        <tr>
            <td>Street:</td>
            <td><form:input path="address.street" /></td>
            <td><form:errors path="address.street" cssClass="error" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><form:input path="address.city" /></td>
            <td><form:errors path="address.city" cssClass="error" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><form:input path="address.state" /></td>
            <td><form:errors path="address.state" cssClass="error" /></td>
        </tr>
        <tr>
            <td>ZipCode:</td>
            <td><form:password path="address.zipcode" /></td>
            <td><form:errors path="address.zipcode" cssClass="error" /></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Cancel" name="_cancel" />
                <input type="submit" value="Prev" name="_target0" />
                <input type="submit" value="Next" name="_target2" />
                <input type="hidden" value="1" name="_page">
            </td>
        </tr>
        <tr>
            <td> 2 | 3</td>
        </tr>
    </table>
</form:form>

</body>
</html>
