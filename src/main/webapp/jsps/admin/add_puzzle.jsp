<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="/localization/locale"/>

<fmt:message key="add_puzzle.page" var="title"/>
<fmt:message key="add_puzzle.button" var="add"/>
<fmt:message key="add_puzzle.path" var="image"/>
<fmt:message key="add_puzzle.name" var="name"/>
<fmt:message key="add.description" var="description"/>
<fmt:message key="add.difficulty_level" var="difficulty_level"/>
<fmt:message key="add.price" var="price"/>
<fmt:message key="add.price.helper" var="helperPrice"/>
<fmt:message key="input_data.invalid" var="invalid"/>

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
<div class="container">
    <form action="${abs}/controller" method="post">
        <input type="hidden" name="command" value="add_puzzle">
        <div class="form-group">
            <label for="exampleFormControlInput1">${image}</label>
            <input type="text" class="form-control" id="exampleFormControlInput1" name="picture_path"
                   value="${requestScope.picture_path['picture_path']}" pattern="[^<>]{0,150}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput2">${name}</label>
            <input type="text" class="form-control" id="exampleFormControlInput2" name="name"
                   required value="${requestScope.name['name']}" pattern="[^<>]{1,40}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput3">${difficulty_level}</label>
            <input type="text" class="form-control" id="exampleFormControlInput3" name="difficulty_level"
                   required value="${requestScope.difficulty_level['difficulty_level']}" pattern="[123]">
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput4">${price}</label>
            <input type="number" step="0.01" min="0" max="999.99" class="form-control" id="exampleFormControlInput4"
                   name="price"
                   value="${requestScope.price['price']}"
                   required pattern="^\\d{1,3}(\\.\\d{1,2})?$">
            <small id="passwordHelpBlockF" class="form-text text-muted" style="color: black">
                ${helperPrice}
            </small>
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">${description}</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description">
                ${requestScope.description['description']}
            </textarea>
        </div>

        <c:if test="${requestScope.errorMessage != null}">
            <div class="form-group form-check" style="color: red">
                <input type="hidden" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">
                        ${invalid}
                </label>
            </div>
        </c:if>

        <div class="form-group col-md-10" style="justify-content: center">
            <button type="submit" class="btn btn-primary"
                    style="background-color: burlywood; border-color: black; justify-content: center">${add}</button>
        </div>
    </form>
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
