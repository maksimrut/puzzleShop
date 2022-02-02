<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="update_profile.page" var="title"/>
<fmt:message key="update_profile.login" var="login_title"/>
<fmt:message key="profile.name" var="name"/>
<fmt:message key="profile.email" var="email"/>
<fmt:message key="update_profile.name_helper" var="name_helper"/>
<fmt:message key="update_profile.phone_number" var="phone_number"/>
<fmt:message key="update_profile.button_update" var="button"/>
<fmt:message key="update_profile.incorrect_name_message" var="incorrect_name_message"/>
<fmt:message key="update_profile.incorrect_phone_message" var="incorrect_phone_message"/>
<fmt:message key="update_profile.successful_updating_message" var="successful_updating_message"/>

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

<div class="container" style="height: 75vh">
    <div class="row" style="justify-content: center">
        <form style="justify-content: center" action="${abs}/controller" method="post">
            <input type="hidden" name="command" value="update_user_profile">

            <div class="form-group">
                <label>${login_title} ${user.login}</label>
            </div>

            <div class="form-group">
                <label>${email}: ${user.email}</label>
            </div>

            <div class="form-group">
                <label for="prof_name">${name}</label>
                <input type="text" class="form-control" id="prof_name" aria-describedby="emailHelp"
                       name="first_name" value="${user_name}"
                       required pattern="^[A-ZА-Я]{1}[a-zа-я]{2,20}$">
                <small id="name_help" style="color: black" class="form-text text-muted">${name_helper}</small>
            </div>
            <c:if test="${name_change_result eq 'incorrect_message'}"><div><b>${incorrect_name_message}</b></div></c:if>
            <div class="form-group">
                <label for="exampleInputPassword1">${phone_number}</label>
                <input type="text" class="form-control" id="exampleInputPassword1"
                       name="phone" value="${user_phone_number}"
                       required pattern="^(25|29|33|44)\d{7}$">
            </div>
            <c:if test="${phone_change_result eq 'incorrect_message'}"><div><b>${incorrect_phone_message}</b></div></c:if>
            <br/>

            <button type="submit" class="btn btn-primary"
                    style="border-color: black">${button}</button>
            <c:if test="${profile_change_result eq 'valid_profile_data'}">
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
