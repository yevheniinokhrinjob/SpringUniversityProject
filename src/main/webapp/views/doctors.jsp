<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    <h3>Our doctors:</h3>
<c:if  test="${!empty listDoctors}">
    <table class="data" width=80% align="center">
        <tr>
            <th>Name</th>
            <th>Last name</th>
            <th>Position</th>
            <th>Email</th>
            <th>Phone</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${listDoctors}" var="doctor">
            <tr>
                <td>${doctor.appUser.fName} </td>
                <td>${doctor.appUser.lName} </td>
                <td>${doctor.position} </td>
                <td>${doctor.appUser.email}</td>
                <td>${doctor.appUser.phone}</td>

            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</body>
</html>

