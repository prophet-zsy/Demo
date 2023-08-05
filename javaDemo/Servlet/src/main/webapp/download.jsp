<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/1/21
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--通过a标签下载图片不行--%>
<a href="1.jpg" download>下载图片</a>
<a href="1.jpg" download="download">下载图片</a>
<a href="1.jpg" download>下载图片</a>
<%--通过a标签下载文件可以的--%>
<a href="text.txt" download>下载txt</a>
<a href="text.txt" download="download">下载txt</a>
<a href="text.txt" download>下载txt</a>
</body>
</html>
