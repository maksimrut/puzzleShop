<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<%--<fmt:setLocale value="ru"/>--%>
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
<html>
<%--<%@include file="../header/header.jsp" %>--%>
<head>
<%--    <script src="${abs}/js/message.js"></script>--%>
    <link rel="stylesheet" href="${abs}/css/registration.css">
    <title>${registration_title}</title>
</head>
<body>
<h1>${registration_title}</h1>
<a href="${abs}/controller?command=change_locale&locale=en">English</a>
<a href="${abs}/controller?command=change_locale&locale=ru">Русский</a>
<div class="container-fluid" id="container-fluid">
    <form action="${abs}/controller" method="post">
        <input type="hidden" name="command" value="registration">
        <label for="login">${login}</label><br/>
        <input type="text" required id="login" name="login" value="${valid_login}"
               placeholder="${login_requirement}" pattern="[a-zA-Z]\w{3,15}">
        <c:choose>
            <c:when test="${invalid_login eq 'invalid_message'}"><div><b>${invalid_login_message}</b></div></c:when>
            <c:when test="${invalid_login eq 'not_unique_message'}"><div><b>${not_unique_login_message}</b></div></c:when>
        </c:choose>
        <br/><br/>
        <label for="password">${psw}</label><br/>
        <input type="text" required id="password" name="password" placeholder="${invalid_psw_message}"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,50}$">
        <c:if test="${invalid_passport eq 'invalid_message'}"><div><b>${invalid_psw_message}</b></div></c:if>
        <br/><br/>
        <label for="confirm-password">${confirm_psw}</label><br/>
        <input type="text" required id="confirm-password" name="confirm_password" placeholder="${confirm_psw}"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,50}$">
        <c:if test="${invalid_password eq 'password_mismatch'}"><div><b>${psw_mismatch_message}</b></div></c:if>
        <br/><br/>
        <label for="email-address">${email}</label><br/>
        <input type="email" required id="email-address" class="form-control" name="email_address" value="${valid_email}"
               placeholder="${email}">
        <c:choose>
            <c:when test="${invalid_email eq 'invalid_message'}"><div><b>${invalid_email_message}</b></div></c:when>
            <c:when test="${invalid_email eq 'not_unique_message'}"><div><b>${not_unique_email_message}</b></div></c:when>
        </c:choose>
        <br/><br/><br/>
        <input type="submit" id="sign_up" name="submit" value="${sign_up}"><br/>
    </form>
</div>
<%--<%@include file="../footer/footer.jsp" %>--%>
</body>
</html>