<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hanmoi
  Date: 2/09/12
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLEncoder" %>
<%
    Cookie cookie = new Cookie("name", "Daniel");
    response.addCookie(cookie);
%>
<html>
<head>
    <title></title>

</head>
<body>
    Cookie Name: ${cookie.name.name}
    Cookie Value: ${cookie.name.value}
</body>
</html>