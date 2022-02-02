<jsp:useBean id="user" scope="session" type="com.rutkouski.puzzleshop.model.entity.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="header.home" var="home"/>
<fmt:message key="header.goods" var="goods"/>
<fmt:message key="header.about" var="about"/>
<fmt:message key="header.logOut" var="logOut"/>
<fmt:message key="header.signIn" var="signIn"/>
<fmt:message key="header.signUp" var="signUp"/>
<fmt:message key="header.profile" var="profile"/>
<fmt:message key="header.authorization" var="authorization"/>
<fmt:message key="header.login" var="login"/>
<fmt:message key="header.password" var="password"/>
<fmt:message key="header.middle" var="middle"/>
<fmt:message key="header.difficult" var="difficult"/>
<fmt:message key="header.extreme" var="extreme"/>
<fmt:message key="header.puzzles" var="puzzles"/>
<fmt:message key="header.discount" var="discount"/>
<fmt:message key="admin_header.user_management" var="user_management"/>
<fmt:message key="admin_header.order_management" var="order_management"/>
<fmt:message key="customer.profile_my_orders" var="show_orders"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${abs}/controller?command=go_to_main">PuzzleSHOP</a>
    <img width="60" src="${abs}/images/logo.JPG" alt="shop logo">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${abs}/controller?command=go_to_main">${home}<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${abs}/controller?command=show_all_goods">${goods}</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    ${puzzles}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item"
                       href="${abs}/controller?command=show_puzzles_by_difficulty_level&puzzle_difficulty=1">${middle}</a>
                    <a class="dropdown-item"
                       href="${abs}/controller?command=show_puzzles_by_difficulty_level&puzzle_difficulty=2">${difficult}</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item"
                       href="${abs}/controller?command=show_puzzles_by_difficulty_level&puzzle_difficulty=3">${extreme}</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${abs}/controller?command=go_to_about_us">${about}</a>
            </li>
            <c:if test="${user.role eq 'ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${abs}/controller?command=user_management">${user_management}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${abs}/controller?command=order_management">${order_management}</a>
                </li>
            </c:if>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a class="nav-link" href="#">${user.login}</a></li>

            <c:choose>
                <c:when test="${user.role eq 'ADMIN' or user.role eq 'CUSTOMER'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${abs}/controller?command=go_to_profile">${profile}</a>
                    </li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${user.role eq 'CUSTOMER'}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="${abs}/controller?command=show_basket"><i style="font-size:24px"
                                                                           class="fa">&#xf291;</i></a>
                    </li>
                    <li><a class="nav-link" href="#">${discount} = ${user_discount}%</a></li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${abs}/controller?command=show_orders_for_customer">${show_orders}</a>
                    </li>
                </c:when>
            </c:choose>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="languageDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    RU/EN
                </a>
                <div class="dropdown-menu" aria-labelledby="languageDropdown">
                    <a class="dropdown-item" href="${abs}/controller?command=change_locale&locale=en">English</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${abs}/controller?command=change_locale&locale=ru">Русский</a>
                </div>
            </li>

            <c:choose>
                <c:when test="${user.role eq 'ADMIN' or user.role eq 'CUSTOMER'}">
                    <li><a class="nav-link" href="${abs}/controller?command=log_out"><span
                            class="glyphicon glyphicon-log-out"></span> ${logOut}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a class="nav-link" href="${abs}/controller?command=go_to_registration">${signUp}</a></li>
                    <li><a class="nav-link" href="#" data-toggle="modal" data-target="#login-modal">${signIn}</a></li>
                    <div id="login-modal" class="modal fade">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">${authorization}</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <br/>
                                    <form action="${abs}/controller" method="post" id="log-form" class="form-group">
                                        <input type="hidden" name="command" value="sign_in"/>
                                        <label for="user_name"><span class="glyphicon glyphicon-user"></span> ${login}
                                        </label>
                                        <input type="text" id="user_name" class="form-control" name="login"
                                               placeholder=${login}><br/>
                                        <label for="user-psw"><span
                                                class="glyphicon glyphicon-eye-open"></span> ${password}</label>
                                        <input type="password" id="user-psw" class="form-control" name="password"
                                               placeholder="${password}">
                                    </form>

                                </div>
                                <div class="modal-footer">
                                    <button type="submit" form="log-form" class="btn btn-secondary"
                                            data-bs-dismiss="modal">${signIn}</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </ul>

        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

    </div>
</nav>