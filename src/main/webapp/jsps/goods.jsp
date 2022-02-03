<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="goods.page.title" var="title"/>
<fmt:message key="goods.image" var="image"/>
<fmt:message key="goods.difficulty_level" var="difficulty_level"/>
<fmt:message key="goods.description" var="description"/>
<fmt:message key="goods.name" var="name"/>
<fmt:message key="goods.price" var="price"/>
<fmt:message key="goods.delete" var="delete"/>
<fmt:message key="goods.edit" var="edit"/>
<fmt:message key="goods.addToBasket" var="addToBasket"/>
<fmt:message key="goods.notAvailable" var="notAvailable"/>
<fmt:message key="goods.addNew" var="newOne"/>

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

<div class="container-fluid" id="container-fluid">

    <c:if test="${user.role=='ADMIN'}">
        <h4 align="center">
            <a href="${abs}/controller?command=go_to_add_puzzle"
               style="color: burlywood">${newOne}</a>
        </h4>
    </c:if>

    <table class="table table-striped" style="height: inherit">
        <thead>
        <tr>
            <th scope="col">${image}</th>
            <th scope="col">${name}</th>
            <th scope="col">${difficulty_level}</th>
            <th scope="col">${description}</th>
            <th scope="col">${price}</th>
            <c:if test="${user.role=='ADMIN'}">
                <th scope="col">${edit}</th>
                <th scope="col">${delete}</th>
            </c:if>
            <c:if test="${user.role=='CUSTOMER'}">
                <th scope="col">${addToBasket}</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="element" items="${requestScope.puzzles_list}">
            <tr>
                <td><img width="100" src="${abs}/${element.picturePath}"
                         alt="pic"></td>
                <td>${element.name}</td>
                <td>${element.difficultyLevel}</td>
                <td>${element.description}</td>
                <td>${element.price}</td>

                <c:if test="${user.role=='ADMIN'}">
                    <td>
                        <a href="${abs}/controller?command=go_to_edit_puzzle&puzzle_id=${element.id}&page=${requestScope.page}">&#9998;</a>
                    </td>
                    <td>
                        <a href="${abs}/controller?command=delete_puzzle&puzzle_id=${element.id}&page=${requestScope.page}"
                           style="color: crimson">${delete}</a>
                    </td>
                </c:if>

                <c:if test="${user.role=='CUSTOMER'}">
                    <td>
                        <a href="${abs}/controller?command=add_item_to_basket&puzzle_id=${element.id}">&#10010;</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>

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
