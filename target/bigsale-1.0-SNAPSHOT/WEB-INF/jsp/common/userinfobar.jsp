<%@ taglib prefix='security'
           uri='http://www.springframework.org/security/tags'%>

<span id="loginstatus">Logged in as: <security:authentication
		property="principal.username" /> </span>
<br />


