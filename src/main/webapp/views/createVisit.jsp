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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
<link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
<html>
<head>

    <script>
        $(function() {
            $("#datepicker").datepicker({

               minDate: new Date()
            });
        });
    </script>
</head>
<body>
<div class="bodyClass">
<h3>Create new visit</h3>
<form:form method="post" action="createVisit.html" modelAttribute="visit">
    <table width=80%>
        <tr>
            <td><form:hidden path="id"/>
            <td><form:hidden path="price"/>
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
            <td><form:input  path="doctor.position" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="date">Date</form:label></td>
            <td><input id="datepicker" name="date" type="text" value="${stringDate}"/></td>


        </tr>
        <tr>
            <c:if test="${isSelected==true}">
            <td>  <input type="submit" value="See available time"/></td>
            </c:if>
        </tr>
    </table>
</form:form>
<c:if  test="${!empty listDoctors}">
    <table class="data">
        <tr>
            <th>Name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${listDoctors}" var="doc">
            <tr>
                <td>${doc.appUser.fName} </td>
                <td>${doc.appUser.lName} </td>
                <td>${doc.position}</td>
                <td><a href="createVisit.html?doctorId=${doc.id}" style="color:#2e2f2f">select doctor</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</body>
</html>
