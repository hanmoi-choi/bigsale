<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2> Enter Address</h2>
<form:form method="POST" commandName="addressDto">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <table>
        <tr>
            <td>Street:</td>
            <td><form:input path="street" size="40"/></td>
            <td><form:errors path="street" cssClass="error" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><form:input path="city" size="10"/></td>
            <td><form:errors path="city" cssClass="error" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td>
                <form:select path="state">
                    <form:option value="NONE" label="--- Select ---" />
                    <form:options items="${addressDto.stateMap}" />
                </form:select>
            </td>
            <td><form:errors path="state" cssClass="error" /></td>
        </tr>
        <tr>
            <td>ZipCode:</td>
            <td><form:input path="zipcode" size="4"/></td>
            <td><form:errors path="zipcode" cssClass="error" /></td>
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


