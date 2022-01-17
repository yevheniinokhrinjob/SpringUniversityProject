<%--
  Created by IntelliJ IDEA.
  User: nohri
  Date: 05.01.2020
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
</head>
<body>
<div class="bodyClass">
<h3>Add new doctor</h3>
<form:form method="post" action="addNewDoctor.html" modelAttribute="doctor">
<table width=80% align="center">
    <tr>
        <td><form:hidden path="id"/>
    </tr>
    <tr>
        <td><form:hidden path="appUser.id"/>
    </tr>
    <tr>
        <td><form:label path="appUser.fName">First name:</form:label></td>
        <td>${doctor.appUser.fName}</td>
    </tr>
    <tr>
        <td><form:label path="appUser.lName">Last name name:</form:label></td>
        <td>${doctor.appUser.lName}</td>
    </tr>
    <tr>
        <td><form:label path="salary">Salary</form:label></td>
        <td><form:input path="salary"/></td>
    </tr>
    <tr>
        <td><form:label path="position">Position</form:label></td>
        <td><form:input path="position"/></td>
    </tr>
    <tr>
        <c:if test="${isSelected==true}">
        <td>  <input type="submit" value="Add doctor"/></td>
        </c:if>
    </tr>
</table>
</form:form>
<c:if  test="${!empty listUsers}">
    <table class="data" width=80% align="center">
        <tr>
            <th>Name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.fName} </td>
                <td>${user.lName} </td>
                <td>${user.email}</td>
                <td><a href="addDoctor.html?appUserId=${user.id}" style="color:#2e2f2f">add as a doctor</a></td>

            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</body>
</html>
