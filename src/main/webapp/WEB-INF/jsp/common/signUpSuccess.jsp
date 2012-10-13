<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1>Registration Succeeded.</h1>
<form:form method="POST" commandName="userSignUpDto" ACTION="/bigsale/welcome.html">
    <table>
        <tr>
            <td>User ID :</td>
            <td>${userSignUpDto.userId}</td>
        </tr>

        <tr>
            <td>Full Name:</td>
            <td>${userSignUpDto.fullName}</td>
        </tr>

        <tr>
            <td>E-mail :</td>
            <td>${userSignUpDtor.email}</td>
        </tr>
        <tr>
            <td colspan="1">
                <input type="submit" value="OK">
            </td>
        </tr>
    </table>
</form:form>
