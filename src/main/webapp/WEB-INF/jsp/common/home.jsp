<%@ page isELIgnored="false" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>


<c:redirect url="/welcome.html"/>
<%--<html>--%>

<%--<head>--%>

<%--<title>Home - Custom Authentication Sample App</title>--%>

<%--</head>--%>

<%--<body>--%>

	<%--<%@ include file="/WEB-INF/jsp/common/userinfobar.jsp"%>--%>
	<%--<%@ include file="/WEB-INF/jsp/common/navigation.jsp"%>--%>

	<%--<h1>Home Page</h1>--%>

<%--</body>--%>

<%--</html>--%>
<security:authorize ifAnyGranted="ROLE_ADMIN">
    <c:redirect url="/buyer/welcome.html"/>
</security:authorize>
<security:authorize ifAnyGranted="ROLE_USER">
    <c:redirect url="/admin/welcome.html"/>
</security:authorize>
