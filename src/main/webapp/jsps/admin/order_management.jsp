<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="orders.page" var="title"/>
<fmt:message key="orders.date" var="date"/>
<fmt:message key="orders.status" var="status"/>
<fmt:message key="orders.info" var="info"/>
<fmt:message key="orders.open" var="open"/>
<fmt:message key="orders.id" var="id"/>
<fmt:message key="orders.customer_id" var="customer_id"/>
<fmt:message key="orders.completed" var="completed"/>
<fmt:message key="admin.cancel_restore_order" var="cancel_restore"/>
<fmt:message key="admin.cancel_order_button" var="button_cancel"/>
<fmt:message key="admin.in_process_order_button" var="button_in_process"/>
<fmt:message key="admin.complete_button" var="button_complete"/>
<fmt:message key="basket.totalCost" var="totalCost"/>

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

    <title>${title}</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<br/>

<div class="sign" style="height: 100vh">
    <div class="container" style="height: 110vh">
        <div class="row">
            <table class="table table-striped" style="height: inherit">
                <thead>
                <tr>
                    <th scope="col">${id}</th>
                    <th scope="col">${date}</th>
                    <th scope="col">${totalCost}</th>
                    <th scope="col">${customer_id}</th>
                    <th scope="col">${status}</th>
                    <th scope="col">${cancel_restore}</th>
                    <th scope="col">${completed}</th>
                    <th scope="col">${info}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="element" items="${requestScope.orders}">
                    <tr>
                        <td>${element.id}</td>
                        <td>${element.date}</td>
                        <td>${element.totalPrice}</td>
                        <td>${element.customerId}</td>
                        <td>${element.status}</td>

                        <td>
                            <c:choose>
                                <c:when test="${element.status == 'IN_PROCESS'}">
                                    <form action="${abs}/controller" method="post">
                                        <input type="hidden" name="command" value="cancel_order">
                                        <input type="hidden" name="order_id" value="${element.id}">
                                        <button type="submit" class="btn btn-primary">${button_cancel}</button>
                                    </form>
                                </c:when>
                                <c:when test="${element.status == 'CANCELLED'}">
                                    <form action="${abs}/controller" method="post">
                                        <input type="hidden" name="command" value="update_order_status_to_in_process">
                                        <input type="hidden" name="order_id" value="${element.id}">
                                        <button type="submit" class="btn btn-primary">${button_in_process}</button>
                                    </form>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${element.status == 'IN_PROCESS'}">
                                    <form action="${abs}/controller" method="post">
                                        <input type="hidden" name="command" value="complete_order">
                                        <input type="hidden" name="order_id" value="${element.id}">
                                        <button type="submit" class="btn btn-primary">${button_complete}</button>
                                    </form>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <a href="${abs}/controller?command=open_order_info&order_id=${element.id}"
                               style="color: darkgreen">${open}
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
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

<%@include file="../fragment/footer.jsp" %>
</html>