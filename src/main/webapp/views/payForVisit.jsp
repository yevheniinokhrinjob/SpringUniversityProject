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
<h3>Pay for your last visit</h3>
<form:form method="post" action="createNewVisit.html" modelAttribute="visit">
    <table>
        <tr>
            <td><form:label path="id">Visit id:</form:label></td>
            <td>${visit.id}</td>
        </tr>
        <tr>
            <td>Account number:</td>
            <td>12321312312312</td>
        </tr>
        <tr>
            <td><form:label path="doctor.appUser.fName">First name:</form:label></td>
            <td>${visit.doctor.appUser.fName}</td>
        </tr>
        <tr>
            <td><form:label path="doctor.appUser.lName">Last name name:</form:label></td>
            <td>${visit.doctor.appUser.lName}</td>
        </tr>

        <tr>
            <td><form:label  path="doctor.position">Position</form:label></td>
            <td>${visit.doctor.position}</td>
        </tr>
        <tr>
            <td><form:label path="date">Date:</form:label></td>
            <td>${stringDate}</td>
        </tr>
        <tr>
            <td><form:label path="price">Price</form:label></td>
            <td>${visit.price}</td>
        </tr>
<tr>
    <td><a href="/generatePdf-${visit.id}" style="color:#2e2f2f">Get pdf bill</a> </td>
</tr>

    </table>
</form:form>
</div>
</body>
</html>
