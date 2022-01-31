<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="goods.image" var="image"/>
<fmt:message key="goods.difficulty_level" var="difficulty_level"/>
<fmt:message key="goods.description" var="description"/>
<fmt:message key="goods.name" var="name"/>
<fmt:message key="goods.price" var="price"/>
<fmt:message key="basket.quantity" var="quantity"/>
<fmt:message key="basket.addItem" var="addItem"/>
<fmt:message key="basket.removeItem" var="removeItem"/>
<fmt:message key="basket.empty" var="emptyBasket"/>
<fmt:message key="basket.totalCost" var="totalCost"/>
<fmt:message key="basket.makeOrder" var="makeOrder"/>
<fmt:message key="basket.page" var="title"/>

<!DOCTYPE html>
<html lang="en">
<%@include file="../fragment/header.jsp" %>

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
    <%--    <link rel="stylesheet" href="${abs}/css/registration.css">--%>
    <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<br/>
<div class="container-fluid" id="container-fluid">
    <c:choose>
        <c:when test="${empty sessionScope.basket}">
            <div class="row" style="justify-content: center">
                <h3 align="center">${emptyBasket}</h3>
            </div>
        </c:when>
        <c:otherwise>
            <table class="table table-striped" style="height: inherit">
                <thead>
                <tr>
                    <th scope="col">${image}</th>
                    <th scope="col">${name}</th>
                    <th scope="col">${difficulty_level}</th>
                    <th scope="col">${description}</th>
                    <th scope="col">${price}</th>
                    <th scope="col">${quantity}</th>
                    <th scope="col">${addItem}</th>
                    <th scope="col">${removeItem}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="element" items="${requestScope.basket_items_list}">
                    <tr>
                        <td><img width="100" src="${abs}/${element.picturePath}"
                                 alt="pic"></td>
                        <td>${element.name}</td>
                        <td>${element.difficultyLevel}</td>
                        <td>${element.description}</td>
                        <td>${element.price}</td>
                        <td>${sessionScope.basket.get(element.id)}</td>
                        <td>
                            <a href="${abs}/controller?command=recount_order_while_adding_item&puzzle_id=${element.id}">&#10010;</a>
                        </td>
                        <td>
                            <a href="${abs}/controller?command=recount_order_while_removing_item&puzzle_id=${element.id}">&#8212;</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    <hr/><br/>

    <c:if test="${sessionScope.basket != null && !empty sessionScope.basket}">
        <p align="center">${totalCost}: ${requestScope.total_cost} &euro;</p>
        <div class="container">
            <div class="row" style="justify-content: center">
                <form action="${abs}/controller" method="post">
                    <input type="hidden" name="command" value="create_order">
                    <input type="hidden" name="order_cost" value="${requestScope.total_cost}">
                    <div class="form-group col-md-2">
                        <button type="submit" class="btn btn-light" style="background-color: burlywood;
                         border-color: black">${makeOrder}</button>
                    </div>
                </form>
            </div>
        </div>
    </c:if>
    <br/><br/>

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

<%@include file="../fragment/footer.jsp" %>
</html>