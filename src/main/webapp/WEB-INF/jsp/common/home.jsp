<%@ page isELIgnored="false" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
