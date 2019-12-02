<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图片上传页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/api/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="filename">
    <input type="submit" value="上传">
</form>
<p>${ msg}</p>
<p>${url}</p>
<div>
</div>
</body>
</html>