<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form:form method="POST" commandName="user">
    <%--<form:form commandName="address"/>--%>
    <table>
        <tr>
            <td>User ID :</td>
            <td>${user.userId}</td>
        </tr>

        <tr>
            <td>First Name :</td>
            <td>${user.firstName}</td>
        </tr>

        <tr>
            <td>Second Name :</td>
            <td>${user.lastName}</td>
        </tr>

        <tr>
            <td>E-mail :</td>
            <td>${user.email}</td>
        </tr>
    </table>
</form:form>

<form:form method="POST" commandName="address">
    <table>
        <tr>
            <td>
                <div>Address</div>
            </td>
        </tr>
        <tr>
            <td>Street :</td>
            <td>${address.street}</td>
        </tr>
        <tr>
            <td>City :</td>
            <td>${address.city}</td>
        </tr>
        <tr>
            <td>State :</td>
            <td>${address.state}</td>
        </tr>
        <tr>
            <td>Zip Code :</td>
            <td>${address.zipcode}</td>
        </tr>

            <%--<tr>--%>
            <%--<td>Address Street: </td>--%>
            <%--<td>${address.street}</td>--%>
            <%--</tr>--%>
        <tr>
            <td colspan="3">
                <input type="submit" value="Cancel" name="_cancel"/>
                <input type="submit" value="Prev" name="_target1"/>
                <input type="submit" value="Confirm" name="_finish"/>
                <input type="hidden" value="2" name="_page">
            </td>
        </tr>
    </table>
</form:form>

