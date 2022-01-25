<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="registration.title" var="registration_title"/>
<fmt:message key="registration.sign_up_1" var="sign_up"/>
<fmt:message key="registration.login" var="login"/>
<fmt:message key="registration.login_requirement" var="login_requirement"/>
<fmt:message key="registration.password" var="psw"/>
<fmt:message key="registration.confirm_password" var="confirm_psw"/>
<fmt:message key="registration.email" var="email"/>
<fmt:message key="registration.phone_number" var="phone_number"/>
<fmt:message key="registration.invalid_login" var="invalid_login_message"/>
<fmt:message key="registration.invalid_password" var="invalid_psw_message"/>
<fmt:message key="registration.invalid_email" var="invalid_email_message"/>
<fmt:message key="registration.invalid_phone_number" var="invalid_phone_number"/>
<fmt:message key="registration.not_unique_login" var="not_unique_login_message"/>
<fmt:message key="registration.password_mismatch" var="psw_mismatch_message"/>
<fmt:message key="registration.not_unique_email" var="not_unique_email_message"/>
<fmt:message key="registration.not_unique_mobile_number" var="not_unique_mobile_number_message"/>

<!DOCTYPE html>
<html lang="en">
<%@include file="fragment/header.jsp" %>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

<%--    <script src="${abs}/js/message.js"></script>--%>
    <link rel="stylesheet" href="${abs}/css/registration.css">
    <title>${registration_title}</title>
</head>
<body>
<br/>
<br/>
<h1>${registration_title}</h1>
<br/>
<div class="container-fluid" id="container-fluid">
    <form action="${abs}/controller" method="post">
        <input type="hidden" name="command" value="registration">
        <label for="login">${login}</label><br/>
        <input type="text" required id="login" class="form-control" name="login" value="${valid_login}"
               placeholder="${login_requirement}" pattern="[a-zA-Z]\w{3,15}">
        <c:choose>
            <c:when test="${invalid_login eq 'invalid_message'}"><div><b>${invalid_login_message}</b></div></c:when>
            <c:when test="${invalid_login eq 'not_unique_message'}"><div><b>${not_unique_login_message}</b></div></c:when>
        </c:choose>
        <br/>
        <label for="password">${psw}</label><br/>
        <input type="text" required id="password" class="form-control" name="password" placeholder="${invalid_psw_message}"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,50}$">
        <c:if test="${invalid_password eq 'invalid_message'}"><div><b>${invalid_psw_message}</b></div></c:if>
        <br/>
        <label for="confirm-password">${confirm_psw}</label><br/>
        <input type="text" required id="confirm-password" class="form-control" name="confirm_password" placeholder="${confirm_psw}"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,50}$">
        <c:if test="${invalid_password eq 'password_mismatch'}"><div><b>${psw_mismatch_message}</b></div></c:if>
        <br/>
        <label for="email-address">${email}</label><br/>
        <input type="email" required id="email-address" class="form-control" name="email_address" value="${valid_email}"
               placeholder="${email}">
        <c:choose>
            <c:when test="${invalid_email eq 'invalid_message'}"><div><b>${invalid_email_message}</b></div></c:when>
            <c:when test="${invalid_email eq 'not_unique_message'}"><div><b>${not_unique_email_message}</b></div></c:when>
        </c:choose>
        <br/><br/>
        <input type="submit" id="sign_up" class="form-control" name="submit" value="${sign_up}"><br/>
    </form>
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