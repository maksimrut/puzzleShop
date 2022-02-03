<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="update_password.page" var="title"/>
<fmt:message key="update_password.current_password" var="current_password"/>
<fmt:message key="update_password.new_password" var="new_password"/>
<fmt:message key="update_password.helper" var="helper"/>
<fmt:message key="registration.confirm_password" var="confirm_password"/>
<fmt:message key="update_password.button_update" var="update"/>
<fmt:message key="update_password.incorrect_psw_message" var="incorrect_psw_message"/>
<fmt:message key="update_password.invalid_psw_message" var="invalid_psw_message"/>
<fmt:message key="update_password.password_mismatch_message" var="password_mismatch_message"/>
<fmt:message key="update_password.successful_updating_message" var="successful_updating_message"/>

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

<div class="container" style="height: 66vh">
    <div class="row" style="justify-content: center">
        <form style="justify-content: center" action="${abs}/controller" method="post">
            <input type="hidden" name="command" value="update_user_password">
            <div class="form-group">
                <label for="old_psw">${current_password}</label>
                <input type="password" class="form-control" id="old_psw" aria-describedby="emailHelp"
                       name="old_password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
            </div>
            <c:if test="${password_change_result eq 'incorrect_message'}">
                <div><b>${incorrect_psw_message}</b></div>
            </c:if>
            <div class="form-group">
                <label for="new_psw">${new_password}</label>
                <input type="password" class="form-control" id="new_psw"
                       name="new_password" required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}">
                <small id="emailHelp" class="form-text text-muted">${helper}</small>
            </div>
            <c:if test="${password_change_result eq 'invalid_message'}">
                <div><b>${invalid_psw_message}</b></div>
            </c:if>
            <div class="form-group">
                <label for="psw_confirmation">${confirm_password}</label>
                <input type="password" class="form-control" id="psw_confirmation"
                       name="confirm_password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}">
            </div>
            <c:if test="${password_change_result eq 'password_mismatch'}">
                <div><b>${password_mismatch_message}</b></div>
            </c:if>
            <br/>
            <button type="submit" class="btn btn-primary"
                    style="border-color: black">${update}</button>
            <c:if test="${password_change_result eq 'true'}">
                <div style="color: darkgreen"><b>${successful_updating_message}</b></div>
            </c:if>
        </form>
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