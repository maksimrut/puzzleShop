<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR 404</title>
</head>
<body>
Request from = ${pageContext.errorData.requestURI} failed
<hr/>
Status = ${pageContext.errorData.statusCode}
<hr/>
Exception = ${pageContext.exception}
<hr/>
Servlet name = ${pageContext.errorData.servletName}
<hr/>
<a href="index.jsp">ToIndex</a>
</body>
</html>
