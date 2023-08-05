<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/1/21
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
<hr>
<form action="login" method="post">
    用户名：<input type="text" name="name">
    密码：<input type="text" name="password">
    <input type="submit" value="登录">
</form>
</body>
</html>
