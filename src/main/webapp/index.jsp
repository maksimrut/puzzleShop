<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html>
<html>
<head>
    <title>Index page</title>
</head>
<body>
    <jsp:forward page="/controller?command=go_to_main"></jsp:forward>
</body>
</html>