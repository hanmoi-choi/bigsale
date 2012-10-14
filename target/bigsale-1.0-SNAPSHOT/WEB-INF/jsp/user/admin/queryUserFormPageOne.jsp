<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h3> Enter User ID</h3>
<form:form method="POST" commandName="userSearchDto">
    <table>
        <tr>
            <td>ID:</td>
            <td>
                <form:input path="userId" size="20"/>
            </td>
        </tr>

        <tr>
            <td colspan="3">
                <input type="submit" value="Cancel" name="_cancel"/>
                <input type="submit" value="Search" name="_search"/>
                <input type="submit" value="All Users" name="_allBuyers"/>
            </td>
        </tr>
    </table>
</form:form>

