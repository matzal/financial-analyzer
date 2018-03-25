<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 24.03.18
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table">

    <tr>
        <td>Investment Code</td>
        <td>Investment number of use</td>
    </tr>


    <c:forEach var="entry" items="${numberOfInvestmentUse}">
        <tr>
            <td><c:out value="${entry.key}"/>
            <td><c:out value="${entry.value}"/>
        </tr>

    </c:forEach>
</table>
