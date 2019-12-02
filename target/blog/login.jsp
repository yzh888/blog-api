<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录页</title>
</head>
<body>
<img src="${pageContext.request.contextPath}/api/code" alt="">
<form action="${pageContext.request.contextPath}/api/login" method="post">
    <label>
        <input type="text" name="code">
    </label>
    <input type="submit" value="登录">
</form>
</body>
</html>
