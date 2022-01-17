<%--
  Created by IntelliJ IDEA.
  User: nohri
  Date: 19.01.2020
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
</head>
<body>
<div class="bodyClass">
<c:if  test="${!empty visitList}">
    <table class="data" width=60% align="center">
        <tr>
            <th>Client name</th>
            <th>Client last name</th>
            <th>Date</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${visitList}" var="visit">
            <tr>
                <td>${visit.client.fName} </td>
                <td>${visit.client.lName} </td>
                <td>${visit.date} </td>
                <td><a href="completeVisit.html?visitId=${visit.id}" style="color:#2e2f2f">Complete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</body>
</html>
