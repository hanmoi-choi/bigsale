<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<h3> Enter Order Information</h3>
<form:form method="POST" commandName="placeOrderDto">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <table>
        <tr>
            <td>Item Name:</td>
            <td><form:input path="itemName" size="20" disabled="true"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><form:input id="price" path="price" disabled="true"/></td>
        </tr>

        <tr>
            <td><form:errors path="isOrderAmountGreaterThanStock" cssClass="error"/></td>
        </tr>

        <tr>
            <td>Stock Amount:</td>
            <td><form:input path="stockQuantity" disabled="true"/></td>
        </tr>

        <tr>
            <td>Discount Rate:</td>
            <td><form:input id="discountRate" path="discountRate" disabled="true"/></td>
        </tr>

        <tr>
            <td>Order Amount:</td>
            <td><form:input id="orderAmount" path="orderAmount"/></td>
            <td><form:errors path="orderAmount" cssClass="error"/></td>
        </tr>

        <tr>
            <td>Total Price:</td>
            <td><form:input id="totalPirce" path="totalPrice" disabled="true"/></td>
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





<%--var price = $('#price').val();--%>
<%--var discountRate = $('#discountRate').val();--%>
<%--var orderAmount = $('#orderAmount').val();--%>
<%--var totalPrice = (price * orderAmount) - (price * discountRate);--%>
<%--alert(totalPrice);--%>
                <%--$('#totalPrice').val(totalPrice);--%>
