<%--
  Created by IntelliJ IDEA.
  User: nohri
  Date: 08.01.2020
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
</head>
<body>
<div class="bodyClass">
<form:form method="post" action="editPassword.html" modelAttribute="passModel">
    <table>
        <tr>
            <td><form:hidden path="appUser.id"/>
        </tr>

        <tr>
            <td><form:label path="oldPass">Old password</form:label></td>
            <td><form:password path="oldPass" /></td>
            <td><form:errors path="oldPass"/></td>
        </tr>
        <tr>
            <td><form:label path="newPass">New password</form:label></td>
            <td><form:password path="newPass" /></td>
            <td><form:errors path="newPass"/></td>
        </tr>

        <tr>
            <td colspan="2">

                <input type="submit" value="Edit password"/>

            </td>
        </tr>
    </table>



</form:form>
</div>
</body>
</html>
