<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Buyer Menu</h2>

<form method="POST" action="/bigsale/buyer/checkOrder.html">
    <input type="submit" value="Check Order"/>
</form>

<form method="POST" action="/bigsale/buyer/logout.html">
    <input type="submit" value="Log Out"/>
</form>
