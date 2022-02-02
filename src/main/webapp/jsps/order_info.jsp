<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="order_info.page" var="title"/>
<fmt:message key="order_info.orderId" var="order_id"/>
<fmt:message key="order_info.date" var="date"/>
<fmt:message key="order_info.customer_info" var="customer_Info"/>
<fmt:message key="order_info.discount" var="discount"/>
<fmt:message key="order_info.content" var="content"/>
<fmt:message key="goods.image" var="image"/>
<fmt:message key="goods.difficulty_level" var="difficulty_level"/>
<fmt:message key="goods.description" var="description"/>
<fmt:message key="goods.name" var="name"/>
<fmt:message key="basket.quantity" var="quantity"/>
<fmt:message key="basket.pricePerUnit" var="unitPrice"/>
<fmt:message key="basket.totalCost" var="totalCost"/>

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

<div class="container" style="color: black; font-size: 18px;">
    <div class="row">
        <dl class="row">
            <c:if test="${user.role eq 'ADMIN'}">
                <dt class="col-sm-3">${order_id}</dt>
                <dd class="col-sm-9" style="font-size: 20px">${requestScope.order.id}</dd>
            </c:if>

            <dt class="col-sm-3">${date}</dt>
            <dd class="col-sm-9">${requestScope.order.date}</dd>

            <c:if test="${user.role eq 'ADMIN'}">
                <dt class="col-sm-3">${customer_Info}</dt>
                <dd class="col-sm-9">${requestScope.customer.firstName} ${requestScope.customer.phoneNumber} ${requestScope.customer.email}</dd>
            </c:if>

            <dt class="col-sm-3">${discount}</dt>
            <dd class="col-sm-9">${requestScope.discount} %</dd>

            <dt class="col-sm-3">${totalCost}</dt>
            <dd class="col-sm-9">${requestScope.order.totalPrice} &euro;</dd>

            <dt class="col-sm-3">${content}:</dt>

        </dl>
    </div>
</div>

<div class="container" style="height: 170vh">
    <div class="row">
        <table class="table table-striped" style="height: inherit">
            <thead>
            <tr>
                <th scope="col">${image}</th>
                <th scope="col">${name}</th>
                <th scope="col">${difficulty_level}</th>
                <th scope="col">${description}</th>
                <th scope="col">${quantity}</th>
                <th scope="col">${unitPrice}</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="element" items="${requestScope.puzzle_map}">
                <tr>
                    <td><img width="80" src="${abs}/${element.key.picturePath}"
                             alt="pic"></td>
                    <td>${element.key.name}</td>
                    <td>${element.key.difficultyLevel}</td>
                    <td>${element.key.description}</td>
                    <td>${element.value}</td>
                    <td>${element.key.price}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
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