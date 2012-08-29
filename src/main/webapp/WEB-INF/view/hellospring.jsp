<%--
  Created by IntelliJ IDEA.
  User: hanmoi
  Date: 29/08/12
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.bigsale.spikes.HelloSpring" %>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html">
</head>
<body>

<%
    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(
            request.getSession().getServletContext());

    HelloSpring helloSpring = context.getBean(HelloSpring.class);
    out.println(helloSpring.sayHello("Hi"));
%>

</body>
</html>