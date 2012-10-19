<%@ taglib prefix='security'
           uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="loginstatus">Logged in as: <security:authentication
		property="principal.username" /> </div>
<br />
<div>Level :<c:out value="${userLevel}"/></div>

