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
<head> <title>App appUser page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
    <script src="https://www.google.com/recaptcha/api.js"> </script>
</head>
<body>
<div class="bodyClass">
<h1><spring:message code="label.registration"/></h1>
<form:form method="post" action="editAppUser.html" modelAttribute="appUser">
    <table>
        <tr>
            <td><form:hidden path="id"/>
        </tr>
        <tr>
            <td><form:hidden path="password"/>
        </tr>
        <tr>
            <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="phone"><spring:message code="label.telephone"/></form:label></td>
            <td><form:input path="phone" /></td>
            <td><form:errors path="phone"/></td>
        </tr>
        <tr>
            <td><form:label path="address.city"><spring:message code="label.city"/></form:label></td>
            <td><form:input path="address.city" /></td>
        </tr>
        <tr>
            <td><form:label path="address.street"><spring:message code="label.street"/></form:label></td>
            <td><form:input path="address.street" /></td>
        </tr>
        <tr>
            <td><form:label path="address.houseNumber"><spring:message code="label.houseNumber"/></form:label></td>
            <td><form:input path="address.houseNumber" /></td>
        </tr>
        <tr>
            <td colspan="3">
                <div class="g-recaptcha" data-sitekey="6LdSxNIUAAAAAI4TFIYi35iGamY6_mse2FlQWCPq"></div>
            </td>
        </tr>
        <tr>
            <td colspan="2">

                <input type="submit" value="Edit user"/>

            </td>
        </tr>
    </table>



</form:form>
</div>
</body>
</html>
