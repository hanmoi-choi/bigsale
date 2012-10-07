<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Seller Menu</h2>

<form method="POST" action="/bigsale/seller/addItem.html">
    <input type="submit" value="Add Item"/>
</form>

<form method="POST" action="/bigsale/seller/queryItem.html">
    <input type="submit" value="Query Item"/>
</form>

<form method="POST" action="/bigsale/seller/checkOrder.html">
    <input type="submit" value="Check Order"/>
</form>

<form method="POST" action="/bigsale/seller/logout.html">
    <input type="submit" value="Log Out"/>
</form>




