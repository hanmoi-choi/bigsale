<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1>Registration Succeeded.</h1>
<form:form method="POST" commandName="user" ACTION="/bigsale/admin.html">
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
        <tr>
            <td colspan="1">
                <input type="submit" value="OK">
            </td>
        </tr>
    </table>
</form:form>
