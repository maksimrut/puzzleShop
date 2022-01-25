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
<fmt:message key="goods.restore" var="restore"/>
<%--<fmt:message key="goods.deleted" var="deleted"/>--%>
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

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <%--    <script src="${abs}/js/message.js"></script>--%>
<%--    <link rel="stylesheet" href="${abs}/css/registration.css">--%>
    <title>${title}</title>
</head>
<body>



<%--<br/>--%>
<%--<br/>--%>
<%--<h1>${registration_title}</h1>--%>
<br/>
<div class="container-fluid" id="container-fluid">

    <table class="table table-striped" style="height: inherit">
        <thead>
        <tr>
            <th scope="col">${image}</th>
            <th scope="col">${name}</th>
            <th scope="col">${difficulty_level}</th>
            <th scope="col">${description}</th>
            <th scope="col">${price}</th>
            <c:if test="${sessionScope.userRole=='ADMIN'}">
                <th scope="col">${delete} / ${restore}</th>
                <th scope="col">${edit}</th>
            </c:if>
            <c:if test="${user.role=='CUSTOMER'}">
                <th scope="col">${addToBasket}</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="element" items="${requestScope.puzzles_list}">
            <tr>

<%--                <td><img width="100" src="${pageContext.request.contextPath}/uploadImage?imagePath=${element.picturePath}"--%>
<%--                         alt="pic"></td>--%>
                <td><img width="100" src="${abs}/${element.picturePath}"
                         alt="pic"></td>
                <td>${element.name}</td>
                <td>${element.difficultyLevel}</td>
                <td>${element.description}</td>
                <td>${element.price}</td>

                <c:if test="${user.role=='ADMIN'}">
                    <td>
                        <a href="${abs}/controller?command=delete_jewelry&puzzle_id=${element.id}&page=${requestScope.page}"
                           style="color: crimson">${delete}</a>
                    </td>
                    <td>
                        <a href="${abs}/controller?command=go_to_edit_jewelry&puzzle_id=${element.id}&page=${requestScope.page}">&#9998;</a>
                    </td>
                </c:if>

                <c:if test="${user.role=='CUSTOMER'}">
                    <td>
                        <a href="${abs}/controller?command=add_item_to_basket&puzzle_id=${element.id}">&#10010;</a>
<%--                              &page=${requestScope.page}">  --%>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

<%--    <div class="container">--%>
<%--        <div class="row" style="justify-content: center">--%>
<%--            <nav aria-label="Page navigation">--%>
<%--                <ul class="pagination">--%>
<%--                    <li class="page-item">--%>
<%--                        <c:choose>--%>
<%--                            <c:when test="${requestScope.page > 1}">--%>
<%--                                <a class="page-link"--%>
<%--                                   href="${pageContext.request.contextPath}/controller?command=show_all_jewelry&page=${requestScope.page-1}"--%>
<%--                                   aria-label="Previous">--%>
<%--                                    <span aria-hidden="true">&laquo;</span>--%>
<%--                                </a>--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                <a class="page-link"--%>
<%--                                   href="#"--%>
<%--                                   aria-label="Previous" hidden>--%>
<%--                                    <span aria-hidden="true">&laquo;</span>--%>
<%--                                </a>--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
<%--                    </li>--%>
<%--                    <li class="page-item"><span class="page-link">${requestScope.page}</span></li>--%>
<%--                    <li class="page-item">--%>
<%--                        <a class="page-link"--%>
<%--                           href="${pageContext.request.contextPath}/controller?command=show_all_jewelry&page=${requestScope.page+1}"--%>
<%--                           aria-label="Next">--%>
<%--                            <span aria-hidden="true">&raquo;</span>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
<%--        </div>--%>
<%--    </div>--%>

    <br/>
    <c:if test="${sessionScope.userRole=='ADMIN'}">
        <h3 align="center">
            <a href="#"
               style="color: darkgreen">${newOne}</a>
        </h3>
    </c:if>


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
