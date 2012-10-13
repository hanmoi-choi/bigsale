<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form:form method="POST" commandName="userSignUpDto">
    <table>
        <tr>
            <td>User ID :</td>
            <td>${userSignUpDto.userId}</td>
        </tr>

        <tr>
            <td>Full Name :</td>
            <td>${userSignUpDto.fullName}</td>
        </tr>

        <tr>
            <td>E-mail :</td>
            <td>${userSignUpDto.email}</td>
        </tr>

        <tr>
            <td>
                <div>Address</div>
            </td>
        </tr>
        <tr>
            <td>Street :</td>
            <td>${userSignUpDto.street}</td>
        </tr>
        <tr>
            <td>City :</td>
            <td>${userSignUpDto.city}</td>
        </tr>
        <tr>
            <td>State :</td>
            <td>${userSignUpDto.state}</td>
        </tr>
        <tr>
            <td>Zip Code :</td>
            <td>${userSignUpDto.zipcode}</td>
        </tr>

        <tr>
            <td colspan="3">
                <input type="submit" value="Cancel" name="_cancel"/>
                <input type="submit" value="Prev" name="_target0"/>
                <input type="submit" value="Confirm" name="_finish"/>
                <input type="hidden" value="1" name="_page">
            </td>
        </tr>
    </table>
</form:form>

