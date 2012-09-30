<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Page</title>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>

<form:form method="POST" commandName="user">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <table>
        <tr>
            <td>ID:</td>
            <td><form:input path="userId" /></td>
            <td><form:errors path="userId" cssClass="error" /></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" /></td>
            <td><form:errors path="firstName" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" /></td>
            <td><form:errors path="lastName" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Confirm Password :</td>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="error" /></td>
        </tr>

        <tr>
            <td>E-Mail:</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" cssClass="error" /></td>
        </tr>

        <tr>
            <td colspan="3">
                <input type="submit" value="Previous" name="_target1" />
                <input type="submit" value="Finish" name="_finish" />
                <input type="submit" value="Cancel" name="_cancel" />
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td>Gender :</td>--%>
            <%--<td><form:radiobutton path="gender" value="M" label="M" /> <form:radiobutton--%>
                    <%--path="gender" value="F" label="F" /></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td>State :</td>--%>
            <%--<td><form:select path="state">--%>
                <%--<form:option value="0" label="Select" />--%>
                <%--<form:options items="${stateOnAustralia}" itemValue="countryId" itemLabel="countryName" />--%>
            <%--</form:select></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td></td>--%>
            <%--<td><form:checkbox path="mailingList"--%>
                               <%--label="Would you like to join our mailinglist?" /></td>--%>
        <%--</tr>--%>
    </table>
</form:form>

</body>
</html>
