<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='security'
           uri='http://www.springframework.org/security/tags' %>
<c:if test="${empty itemList}">
			<span id="infomessage" class="errormessage">
				No Result
            </span>
</c:if>

<table>
    <tr class="searchResultTitleBlock">
        <td>Item ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Stock</td>
        <td>Discoutn Rate</td>
        <td>Description</td>
    </tr>
    <c:forEach var="item" items="${itemList}">
        <security:authorize ifAnyGranted="ROLE_BUYER">
            <c:url var="modifyUrl" value="/buyer/placeOrder.html">
                <c:param name="itemId" value="${item.itemId}"/>
            </c:url>
        </security:authorize>

        <security:authorize ifNotGranted="ROLE_BUYER">
            <c:url var="modifyUrl" value="denied.jsp"/>
        </security:authorize>
        <tr class="searchResultBlock">
            <form action="${modifyUrl}" method="post">
                <td id="itemId">${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.price}</td>
                <td>${item.stockQuantity}</td>
                <td>${item.discountRate}</td>
                <td>${item.description}</td>
                <td><input type="submit" value="Order"></td>
            </form>
        </tr>

    </c:forEach>
</table>


