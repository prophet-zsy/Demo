<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/1/21
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="upload" method="post" enctype="multipart/form-data">
    姓名：<input type="text" name="name">
    文件：<input type="file" name="myFile">
    <input type="submit" value="上传">
</form>

</body>
</html>
