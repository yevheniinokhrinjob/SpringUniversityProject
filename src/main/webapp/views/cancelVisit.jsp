<%--
  Created by IntelliJ IDEA.
  User: nohri
  Date: 19.01.2020
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
</head>
<body>
<div class="bodyClass">
<form:form method="post" action="cancelVisit.html" modelAttribute="visit">
    <table>
    <tr>
    <td><form:hidden path="id"/>
    </tr>
    <tr>
    <td><form:label path="date">Date:</form:label></td>
    <td>${stringDate}</td>
    </tr>
    <tr>
    <td><form:label path="price">Price:</form:label></td>
    <td>${visit.price}</td>
    </tr>
    <tr>
    <td><form:label path="doctor.appUser.fName">Docs fName</form:label></td>
    <td>${visit.doctor.appUser.fName}</td>
    </tr>
    <tr>
    <td><form:label path="doctor.appUser.lName">Docs lName</form:label></td>
    <td>${visit.doctor.appUser.lName}</td>
    </tr>
    <tr>
    <td>  <input type="submit" value="Cancel visit"/></td>
    </tr>
    </table>
</form:form>
</div>
</body>
</html>