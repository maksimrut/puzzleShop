<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="user_management.page" var="title"/>
<fmt:message key="user_management.create_admin" var="create_admin"/>
<fmt:message key="user_management.user_id" var="id"/>
<fmt:message key="user_management.user_login" var="login"/>
<fmt:message key="user_management.user_name" var="name"/>
<fmt:message key="user_management.user_phone_number" var="phone_number"/>
<fmt:message key="user_management.user_email" var="email"/>
<fmt:message key="user_management.user_role" var="role"/>
<fmt:message key="user_management.user_status" var="status"/>
<fmt:message key="user_management.user_block_unblock" var="block_unblock"/>
<fmt:message key="user_management.user_delete" var="delete"/>
<fmt:message key="user_management.user_block_user" var="block_user"/>
<fmt:message key="user_management.user_unblock_user" var="unblock_user"/>
<fmt:message key="user_management.user_change_role" var="change_role"/>

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

<div class="container" style="height: 250vh">
    <div class="row">
        <table class="table table-striped" style="height: inherit">
            <thead>
            <tr>
                <th scope="col">${id}</th>
                <th scope="col">${login}</th>
                <th scope="col">${name}</th>
                <th scope="col">${phone_number}</th>
                <th scope="col">${email}</th>
                <th scope="col">${role}</th>
                <th scope="col">${status}</th>
                <th scope="col">${block_unblock}</th>
                <th scope="col">${change_role}</th>
                <th scope="col">${delete}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${requestScope.users}">
                <tr>
                    <td>${element.id}</td>
                    <td>${element.login}</td>
                    <td>${element.firstName}</td>
                    <td>${element.phoneNumber}</td>
                    <td>${element.email}</td>
                    <td>${element.role}</td>
                    <td>${element.status}</td>
                    <td>
                        <c:choose>
                            <c:when test="${element.id == user.id}">-</c:when>
                            <c:when test="${element.status == 'ACTIVE'}">
                                <a href="${abs}/controller?command=block_user&user_id=${element.id}"
                                   style="color: crimson">
                                        ${block_user}
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${abs}/controller?command=unblock_user&user_id=${element.id}"
                                   style="color: darkslateblue">
                                        ${unblock_user}
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${abs}/controller?command=change_user_role&user_id=${element.id}&user_role=${element.role}"
                           style="color: darkgreen">
                                ${change_role}
                        </a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${element.id == user.id}">-</c:when>
                            <c:otherwise>
                                <a href="${abs}/controller?command=delete_user&user_id=${element.id}"
                                   style="color: darkslateblue">
                                        ${delete}
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
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

<%@include file="../fragment/footer.jsp" %>
</html>