<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="error500.page" var="title"/>
<fmt:message key="error500.request" var="request"/>
<fmt:message key="error500.servlet" var="servlet"/>
<fmt:message key="error500.statusCode" var="code"/>
<fmt:message key="error500.exception" var="exception"/>
<fmt:message key="error500.message" var="message"/>
<fmt:message key="error500.stackTrace" var="stackTrace"/>
<fmt:message key="error500.backHome" var="home"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript">
        window.history.forward();

        function noBack() {
            window.history.forward();
        }
    </script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <title>${title}</title>
</head>

<body>
<br/>
<div class="container" style="margin-left: 20px">
    ${request} ${pageContext.errorData.requestURI}<br/>
    <hr/>
    ${servlet} ${pageContext.errorData.servletName}<br/>
    <hr/>
    ${code} ${pageContext.errorData.statusCode}<br/>
    <hr/>
    ${exception} ${requestScope.exception}<br/>
    <hr/>
    ${message} ${requestScope.exception.message}<br/>
    <hr/>
    ${stackTrace}<br/>
    <c:forEach var="stackTraceElement" items="${requestScope.exception.stackTrace}">
        <c:out value="${stackTraceElement}"/><br/>
    </c:forEach><br/>
    <hr/>

    <a style="height: 25px; color: midnightblue" href="${abs}/index.jsp">${home}</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
        integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
        crossorigin="anonymous"></script>
</body>
</html>
