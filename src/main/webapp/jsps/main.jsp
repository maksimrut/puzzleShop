<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="main.title" var="title"/>
<fmt:message key="main.distribution" var="distribution"/>
<fmt:message key="main.greeting" var="greeting"/>
<fmt:message key="header.invalid_login_message" var="invalid_message"/>
<fmt:message key="header.valid_login_message" var="valid_message"/>

<!DOCTYPE html>
<html lang="en">
<%@include file="fragment/header.jsp" %>

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

    <%--    <script src="${abs}/js/message.js"></script>--%>
        <link rel="stylesheet" href="${abs}/css/main.css">
    <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<c:choose>
    <c:when test="${sign_in_result eq 'false'}"><div class="alert alert-warning" id="message"><b class="invalid_message">${invalid_message}</b></div></c:when>
    <c:when test="${sign_in_result eq 'true'}"><div class="alert alert-success" id="message"><b class="valid_message">${valid_message} ${user.login}</b></div></c:when>
</c:choose>
<h1>PuzzleSHOP</h1>
<br/>
<div class="navbar-light" id="main_text">
    <c:choose>
        <c:when test="${user.role eq 'GUEST'}">${greeting}</c:when>
        <c:otherwise>${distribution}</c:otherwise>
    </c:choose>
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

<%@include file="fragment/footer.jsp" %>
</html>