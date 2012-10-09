<%@ page isELIgnored="false" session="true"%>
<%@ taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>

<security:authorize ifAnyGranted="ROLE_ADMIN">
    <c:redirect url="/admin/welcome.html"/>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_SELLER">
    <c:redirect url="/seller/welcome.html"/>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_BUYER">
    <c:redirect url="/buyer/welcome.html"/>
</security:authorize>
