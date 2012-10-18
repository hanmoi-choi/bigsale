<%@ taglib prefix='security'
           uri='http://www.springframework.org/security/tags'%>

<div id="loginstatus">Logged in as: <security:authentication
		property="principal.username" /> </div>
<br />
<div>Level : ${userLevel}</div>

