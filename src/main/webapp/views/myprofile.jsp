<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap-4.4.1-dist/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css" />" rel="stylesheet">
</head>
<body>
<div class="bodyClass">
<h1>Your profile</h1>

<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_DOCTOR')">
    <c:if test="${hasUnpaidVisit==false}">
    <a href="/createVisit.html" style="color: white">Create new visit</a>
    <br/>
</c:if>
    <c:if test="${hasUnpaidVisit==true}">
        <a href="/payForVisit.html" style="color: white">Pay for last visit</a>
        <br/>
    </c:if>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_DOCTOR')">
    <c:if test="${hasActualVisit==true}">
    <a href="/cancelVisit.html" style="color: white">Cancel visit</a>
    <br/>
    </c:if>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_DOCTOR')">
    <a href="/editAppUser.html" style="color: white">Edit profile data</a>
    <br/>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_DOCTOR')">
    <a href="/editPassword.html" style="color: white">Edit password</a>
    <br/>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_DOCTOR')">
    <a href="/visitList.html" style="color: white">Visit list</a>
    <br/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/addDoctor.html" style="color: white">Add new doctor</a>
    <br/>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/removeDoctor.html"style="color: white">Remove doctor</a>
    <br/>
</sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/acceptPayment.html"style="color: white">Accept Payment</a>
        <br/>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/appUserRest/admin"style="color: white">Get xml</a>
        <br/>
    </sec:authorize>
</div>
</body>

</html>

