<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<form:form method="POST" commandName="userSignUpDto">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <table>
        <h3> Enter User Information</h3>
        <tr>
            <td><form:errors path="isIdDuplicated" cssClass="error"/></td>
        </tr>
        <tr>
            <td>ID:</td>
            <td><form:input path="userId" size="20"/></td>
            <td><form:errors path="userId" cssClass="error"/></td>
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
            <td>E-Mail:</td>
            <td><form:input path="email" size="40"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
    </table>
    <table>
        <H3>Address</H3>

        <tr>
            <td>Street:</td>
            <td><form:input path="street" size="40"/></td>
            <td><form:errors path="street" cssClass="error"/></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><form:input path="city" size="10"/></td>
            <td><form:errors path="city" cssClass="error"/></td>
        </tr>
        <tr>
            <td>State:</td>
            <td>
                <form:select path="state">
                    <form:option value="NONE" label="--- Select ---"/>
                    <form:options items="${userSignUpDto.stateMap}"/>
                </form:select>
            </td>
            <td><form:errors path="state" cssClass="error"/></td>
        </tr>
        <tr>
            <td>ZipCode:</td>
            <td><form:input path="zipcode" size="4"/></td>
            <td><form:errors path="zipcode" cssClass="error"/></td>
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

