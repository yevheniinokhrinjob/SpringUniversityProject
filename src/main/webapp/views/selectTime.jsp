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
<h3>Select time</h3>
<form:form method="post" action="createNewVisit.html" modelAttribute="visit">
    <table>
        <tr>
            <td><form:hidden path="id"/>
        </tr>
        <tr>
            <td><form:hidden path="doctor.id"/>
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
            <td><input id="date" name="date" type="text" readonly value="${stringDate}"/></td>
        </tr>
        <tr>
            <td><form:label path="price">Price</form:label></td>
            <td>${visit.price}</td>
        </tr>
        <tr>
            <td><form:label path="time">Time</form:label></td>
            <td><form:select path="time" multiple="true">
                <form:options items="${timeList}"/>
            </form:select></td>



        </tr>
        <tr>
            <td>  <input type="submit" value="Create visit"/></td>
        </tr>
    </table>
</form:form>
</div>
</body>
</html>
