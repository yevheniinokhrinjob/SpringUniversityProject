<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="header">

    <table align="right">
        <tr>
            <td>
    <span style="float: right">
        <sec:authorize access="isAnonymous()">
            <a href="/login.html"><spring:message code="label.login"/></a>

        </sec:authorize>
<c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="javascript:formSubmit()"> Logout</a>
</c:if>
                    <br/>
        <a href="/register.html"><spring:message code="label.register"/></a>
    </span>
            </td>
        </tr>
        <tr>
            <td>
    <span style="float: right">
    	<a style="background-color: white" href="?lang=pl">pl</a> | <a style="background-color: white" href="?lang=en">en</a> | <a style="background-color: white" href="?lang=ru">ru</a>
	</span>
            </td>
        </tr>
    </table>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <!-- csrf for log out-->
    <form action="/logout" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>