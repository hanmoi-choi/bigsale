
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${empty ordersOnProcess && empty ordersDelivered}">
			<span id="infomessage" class="errormessage">
				No Result
            </span>
</c:if>

<c:if test="${not empty ordersOnProcess}">
<H2>Order On Process</H2>
<table>
    <tr class="searchResultTitleBlock">
        <td>Item Name</td>
        <td>Order Amount</td>
        <td>Delivery Status</td>
    </tr>

    <c:forEach var="order" items="${ordersOnProcess}">
        <tr class="searchResultBlock">
            <td>${order.itemName}</td>
            <td>${order.orderAmount}</td>
            <td>${order.deliveryStatus}</td>
        </tr>
    </c:forEach>
</table>
</c:if>

<c:if test="${not empty ordersDelivered}">
<H2>Order Delivered</H2>
<table>
    <tr class="searchResultTitleBlock">
        <td>Item Name</td>
        <td>Order Amount</td>
        <td>Delivery Status</td>
    </tr>

    <c:forEach var="user" items="${ordersDelivered}">
        <tr class="searchResultBlock">
            <td>${order.itemName}</td>
            <td>${order.orderAmount}</td>
            <td>${order.deliveryStatus}</td>
        </tr>
    </c:forEach>
</table>
</c:if>

