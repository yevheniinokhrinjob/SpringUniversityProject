<%--
  Created by IntelliJ IDEA.
  User: nohri
  Date: 27.01.2020
  Time: 20:59
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

    <c:if  test="${!empty unpaidList}">
        <table class="data" width=60% align="center">
            <tr>
                <td style="font-weight: bold" align="center">id</td>
                <td style="font-weight: bold" align="center">date</td>
                <td style="font-weight: bold" align="center">price</td>
                <td></td>
            </tr>
            <c:forEach items="${unpaidList}" var="unpaid">
                <tr>
                    <td align="center">${unpaid.id} </td>
                    <td align="center">${unpaid.date} </td>
                    <td align="center">${unpaid.price} </td>
                    <td align="center"><a href="paid/${unpaid.id}.html" style="color:#2e2f2f">Paid</a></td>

                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
