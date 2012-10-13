<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="POST" commandName="itemList">
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
            <td>Description</td>
        </tr>
        <c:forEach var="item" items="${itemList}">
            <tr class="searchResultBlock">
                <td id="itemId">${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.price}</td>
                <td>${item.stockQuantity}</td>
                <td>${item.description}</td>
            </tr>

        </c:forEach>
    </table>

</form:form>
