<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h3> Enter User Information</h3>
<form:form method="POST" commandName="sellerSignUpDto">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <table>
        <tr>
            <td><form:errors path="isIdDuplicated" cssClass="error"/></td>
        </tr>
        <tr>
            <td>ID:</td>
            <td><form:input path="sellerId" size="20"/></td>
            <td><form:errors path="sellerId" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Full Name:</td>
            <td><form:input path="fullName" size="30"/></td>
            <td><form:errors path="fullName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:errors path="isPasswordInputMatched" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password" size="40"/></td>
            <td><form:errors path="password" cssClass="error"/></td>

        </tr>
        <tr>
            <td>Confirm Password :</td>
            <td><form:password path="passwordConfirm" size="40"/></td>
            <td><form:errors path="passwordConfirm" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:errors path="isEmailDuplicated" cssClass="error"/></td>
        </tr>
        <tr>
            <td>E-Mail:</td>
            <td><form:input path="email" size="40"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <input type="submit" value="Cancel" name="_cancel"/>
                <input type="submit" value="Next" name="_target1"/>
                <input type="hidden" value="0" name="_page">
            </td>
        </tr>
    </table>
</form:form>

