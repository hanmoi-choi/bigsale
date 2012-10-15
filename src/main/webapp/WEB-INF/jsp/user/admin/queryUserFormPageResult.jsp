<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${empty userSearchResultList}">
			<span id="infomessage" class="errormessage">
				No Result
            </span>
</c:if>

<table>
    <tr class="searchResultTitleBlock">
        <td>User ID</td>
        <td>Full Name</td>
        <td>Email</td>
        <td>Type</td>
        <td>Level</td>
    </tr>


    <c:forEach var="user" items="${userSearchResultList}">
        <tr class="searchResultBlock">
            <td id="itemId">${user.userId}</td>
            <td>${user.fullName}</td>
            <td>${user.email}</td>
            <td>${user.type}</td>
            <td>${user.level}</td>
        </tr>
    </c:forEach>

</table>
