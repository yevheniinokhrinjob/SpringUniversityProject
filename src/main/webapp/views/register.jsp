<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
</head>
<body>
<div class="bodyClass">
<h1><spring:message code="label.registration"/></h1>
<form:form method="post" action="register.html" modelAttribute="appUser">
    <table>
        <tr>
            <td><form:hidden path="id"/>
        </tr>
        <tr>
            <td><form:label path="fName"><spring:message code="label.firstName"/></form:label></td>
            <td><form:input path="fName" /></td>
            <td><form:errors path="fName"/></td>
        </tr>
        <tr>
            <td><form:label path="lName"><spring:message code="label.lastName"/></form:label></td>
            <td><form:input path="lName" /></td>
            <td><form:errors path="lName"/></td>
        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
            <td><form:input type="password" path="password" /></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="pesel"><spring:message code="label.pesel"/></form:label></td>
            <td><form:input path="pesel" /></td>
            <td><form:errors path="pesel"/></td>
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
            <td><form:errors path="address.city"/></td>
        </tr>
        <tr>
            <td><form:label path="address.street"><spring:message code="label.street"/></form:label></td>
            <td><form:input path="address.street" /></td>
            <td><form:errors path="address.street"/></td>
        </tr>
        <tr>
            <td><form:label path="address.houseNumber"><spring:message code="label.houseNumber"/></form:label></td>
            <td><form:input path="address.houseNumber" /></td>
            <td><form:errors path="address.houseNumber"/></td>
        </tr>
        <tr>
            <td colspan="2">

                    <input type="submit" value="<spring:message code="label.registerBut"/>"/>

            </td>
        </tr>
    </table>



</form:form>
</div>
</body>
</html>

