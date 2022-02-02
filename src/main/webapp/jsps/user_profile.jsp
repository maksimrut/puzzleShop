<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="profile.page" var="title"/>
<fmt:message key="profile.name" var="name"/>
<fmt:message key="profile.login" var="login"/>
<fmt:message key="profile.email" var="email"/>
<fmt:message key="user.role" var="role"/>
<fmt:message key="user.phone_number" var="phone_number"/>
<fmt:message key="profile.discount" var="discount"/>
<fmt:message key="profile.update_profile" var="updateProfile"/>
<fmt:message key="profile.update_password" var="updatePassword"/>

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

    <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<br/>

<div class="container" style="height: 80vh; color: black; font-size: 18px">
    <div class="row">
        <dl class="row">
            <dt class="col-sm-3">${role}</dt>
            <dd class="col-sm-9" style="font-size: 20px">${user.role}</dd>

            <dt class="col-sm-3">${name}</dt>
            <dd class="col-sm-9">${user.firstName}</dd>

            <dt class="col-sm-3">${login}</dt>
            <dd class="col-sm-9">${user.login}</dd>

            <dt class="col-sm-3">${email}</dt>
            <dd class="col-sm-9">${user.email}</dd>

            <dt class="col-sm-3">${phone_number}</dt>
            <dd class="col-sm-9">${user.phoneNumber}</dd>

            <c:choose>
                <c:when test="${user.role eq 'CUSTOMER'}">
                    <dt class="col-sm-3">${discount}</dt>
                    <dd class="col-sm-9">${sessionScope.user_discount}%</dd>
                </c:when>
            </c:choose>

            <dt class="col-sm-3"></dt>
            <dd class="col-sm-9">
                <dl class="row">
                    <dt class="col-sm-4">&#9679; <a href="${abs}/controller?command=go_to_update_profile"
                                                    style="color: midnightblue; font-size: 18px">${updateProfile}</a>
                    </dt>
                </dl>
                <dl class="row">
                    <dt class="col-sm-4">&#9679; <a href="${abs}/controller?command=go_to_update_password"
                                                    style="color: midnightblue; font-size: 18px">${updatePassword}</a>
                    </dt>
                </dl>
            </dd>

        </dl>
    </div>
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
