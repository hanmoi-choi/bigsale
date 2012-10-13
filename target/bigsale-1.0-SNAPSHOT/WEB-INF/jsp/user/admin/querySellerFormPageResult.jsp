<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form:form method="POST" commandName="userList">
    <c:if test="${empty userList}">
			<span id="infomessage" class="errormessage">
				No Result
            </span>
    </c:if>

    <c:forEach var="user" items="${userList}">
        ${user.userId}
    </c:forEach>
</form:form>

