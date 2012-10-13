<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1>Registration Succeeded.</h1>
<form:form method="POST" commandName="sellerSignUpDto" ACTION="/bigsale/admin.html">
    <table>
        <tr>
            <td>User ID :</td>
            <td>${sellerSignUpDto.sellerId}</td>
        </tr>

        <tr>
            <td>Full Name:</td>
            <td>${sellerSignUpDto.fullName}</td>
        </tr>

        <tr>
            <td>E-mail :</td>
            <td>${sellerSignUpDtor.email}</td>
        </tr>
        <tr>
            <td colspan="1">
                <input type="submit" value="OK">
            </td>
        </tr>
    </table>
</form:form>
