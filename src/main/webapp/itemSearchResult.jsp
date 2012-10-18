<%@ page isELIgnored="false" session="true"%>
<%@ taglib prefix='security'
           uri='http://www.springframework.org/security/tags'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<security:authorize ifAnyGranted="ROLE_BUYER">
    <c:redirect url="/buyer/placeOrder.html"/>
</security:authorize>




