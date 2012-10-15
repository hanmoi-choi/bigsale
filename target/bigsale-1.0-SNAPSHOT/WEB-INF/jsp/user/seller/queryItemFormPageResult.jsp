<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${empty itemSearchResultList}">
			<span id="infomessage" class="errormessage">
				No Result
            </span>
</c:if>

<table>
    <tr class="searchResultTitleBlock">
        <td>Item Id</td>
        <td>Item Name</td>
        <td>Stock Amount</td>
        <td>Price</td>
        <td>Discount Rate</td>
    </tr>


    <c:forEach var="item" items="${itemSearchResultList}">
        <c:url var="modifyUrl" value="/seller/modifyItemInfo.html">
            <c:param name="itemId" value="${item.itemId}" />
        </c:url>
        <form method="POST" action="${modifyUrl}">
            <tr class="searchResultBlock">
                <td>${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.stockAmount}</td>
                <td>${item.price}</td>
                <td>${item.discountRate}</td>
                <td><input type="submit" value="Modify"/></td>
            </tr>
        </form>
    </c:forEach>

</table>

