<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h3> Enter User Information</h3>
<form:form method="POST" commandName="itemAddDto">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <table>
        <tr>
            <td>Item Name:</td>
            <td><form:input path="itemName" size="40"/></td>
            <td><form:errors path="itemName" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Amount of Stock:</td>
            <td><form:input path="stockQuantity" size="5"/></td>
            <td><form:errors path="stockQuantity" cssClass="error"/></td>
        </tr>

        <tr>
            <td>Price( $ ):</td>
            <td><form:input path="price" size="40"/></td>
            <td><form:errors path="price" cssClass="error"/></td>

        </tr>
        <tr>
            <td>Discount Rate( % ) :</td>
            <td><form:input path="discountRate" size="40"/> </td>
            <td><form:errors path="discountRate" cssClass="error"/></td>
        </tr>

        <tr>
            <td>Description</td>
            <td><form:textarea path="description" title="Item Description" rows="5" cols="30"/></td>
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

