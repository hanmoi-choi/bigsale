<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h2> Enter User Information</h2>
<form:form method="POST" commandName="userDto">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <table>
        <tr>
            <td>ID:</td>
            <td><form:input path="userId" size="20"/></td>
            <td><form:errors path="userId" cssClass="error" /></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><form:input path="fullName" size="30"/></td>
            <td><form:errors path="fullName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:errors path="isPasswordInputMatched" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password" size="40"/></td>
            <td><form:errors path="password" cssClass="error" /></td>

        </tr>
        <tr>
            <td>Confirm Password :</td>
            <td><form:password path="passwordConfirm" size="40"/></td>
            <td><form:errors path="passwordConfirm" cssClass="error" /></td>
        </tr>

        <tr>
            <td>E-Mail:</td>
            <td><form:input path="email" size="40"/></td>
            <td><form:errors path="email" cssClass="error" /></td>
        </tr>

        <tr>
            <td colspan="3">
                <input type="submit" value="Cancel" name="_cancel" />
                <input type="submit" value="Next" name="_target1"  />
                <input type="hidden" value="0" name="_page">
            </td>
        </tr>
        <tr>
            <td> 1 | 3</td>
        </tr>
    </table>
</form:form>

