<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="${pageContext.request.contextPath}/localization/locale" var="lang"/>

<fmt:message key="registration.title" var="registration_title"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${registration_title}</h1>
<fmt:message key="registration.title" bundle="${lang}"/>

<div align="center" class="align-top">
    <div class="col-md-6">
        <div class="display-3">SIGN-IN</div>
    </div>
</div>
<div align="center" class="align-middle">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="text" id="command" name="command" hidden="hidden" required="required" value="login"/>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="loginOrEmail" class="form-label" >Enter login</label>
            <input type="text" class="form-control" id="loginOrEmail" name="login" value="${login_form_data_map.form_login}" required="required"/>
        </div>
        <div class="mb-3 w-25" style="margin-top: 40px">
            <label for="password" class="form-label">Enter password</label>
            <input type="password" class="form-control" id="password" name="password" value="123" required="required"/>
        </div>
        <button type="submit " class="btn btn-outline-success btn-lg" style="margin-top: 40px">login2</button>
    </form>
</div>
<div align="center" style="margin-top: 160px">
    <div style="color: red; font-weight: bolder; font-style: italic">${errorAuthMessage}</div>
    <a href="/pages/registration.jsp" class="text-black">У вас нет акканта? Зарегистрироваться</a>
    <br/>
    <a href="/index.jsp" class="text-black">Back to main page</a>
    <br/>
</div>

</body>
</html>
