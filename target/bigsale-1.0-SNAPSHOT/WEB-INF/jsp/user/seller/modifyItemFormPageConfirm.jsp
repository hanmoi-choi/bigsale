<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form:form method="POST" commandName="itemModifyDto">
    <table>
        <tr>
            <td>Item Name :</td>
            <td>${itemModifyDto.itemName}</td>
        </tr>

        <tr>
            <td>Price :</td>
            <td>${itemModifyDto.price}</td>
        </tr>

        <tr>
            <td>Amount of Stock :</td>
            <td>${itemModifyDto.stockQuantity}</td>
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

