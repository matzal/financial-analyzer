<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="fin-app" var="finAppLanguage"/>

<h4>
    <fmt:message key="${singleDateDayMessage}" bundle="${finAppLanguage}"/><c:out value="${sessionScope.date}"/>
    <fmt:message key="${singleDateDataMessage}" bundle="${finAppLanguage}"/> ${sessionScope.code}
</h4>

<c:choose>
    <c:when test="${dateError != null}">
        <h5 style="color: red;">${dateError}</h5>
    </c:when>
    <c:otherwise>
        <jsp:include page="single-date-table.jsp"/>
    </c:otherwise>
</c:choose>

