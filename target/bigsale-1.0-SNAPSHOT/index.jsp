<%@ page isELIgnored="false" session="true"%>
<%@ taglib prefix='security'
           uri='http://www.springframework.org/security/tags'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<security:authorize ifAnyGranted="ROLE_ADMIN">
    <c:redirect url="/admin/welcome.html"/>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_SELLER">
    <c:redirect url="/seller/welcome.html"/>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_BUYER">
    <c:redirect url="/buyer/welcome.html"/>
</security:authorize>

<c:redirect url="/welcome.html"/>


