<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>
<%@taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="about_us.page" var="title"/>
<fmt:message key="about_us.address" var="address"/>
<fmt:message key="about_us.phones" var="call"/>
<fmt:message key="about_us.social" var="social"/>

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

    <link rel="stylesheet" href="${abs}/css/about_us.css">
    <title>${title}</title>
</head>
<body>

<div class="container-fluid" id="container-fluid">
    <div class="row">
        <h2 class="contact-title">${title}</h2>
    </div>
    <div class="row">
        <div class="col-lg-5 col-md-12">
            <div class="contact-map">
                <script type="text/javascript" charset="utf-8" async
                        src="https://api-maps.yandex.ru/services/constructor/1.0/js/?um=constructor%3A4f8d85e330c15bfc3925d7b7a52a502ef187ab01903eda5438ee783fc0236056&amp;width=100%&amp;height=400&amp;lang=ru_RU&amp;scroll=true"></script>
            </div>
        </div>
        <div class="col-md-12">
            <div class="contact-info">
                <p>${address}</p>
                <div class="contact-phone">
                    <p>${call}</p>
                    <div class="contact-phone__item"><a href="#">+375(11)111-11-11</a></div>
                    <div class="contact-phone__item"><a href="#">+375(22)222-22-22</a></div>
                </div>
                <div class="contact-social">
                    <p>${social}</p>
                    <a href="#" class="contact-social__item">
                        <img src="${pageContext.request.contextPath}/images/facebook.png" alt="">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-expand-lg fixed-bottom navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarSupportedContentf">
        <ctg:footertag/>
    </div>
</nav>

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