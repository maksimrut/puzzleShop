<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="go_to_registration">
    <input type="text" name="number" value="1">
    <input type="submit" name="sub" value="registration">
</form>
</body>
</html>
