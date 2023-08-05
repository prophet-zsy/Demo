<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

<%-- 引入JQ和Bootstrap --%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.js" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
<%--    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">--%>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<%--    <script src="js/jquery/2.0.0/jquery.min.js"></script>--%>
<%--    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>--%>
    <link href="css/style.css" rel="stylesheet">

    <title>学生管理页面 - 首页</title>

    <script>
        $(function () {
            $("ul.pagination li.disabled a").click(function () {
                return false;
            });
        });
    </script>
</head>

<body>

<div class="listDIV">
    <table class="table table-striped table-bordered table-hover table-condensed">

        <caption>学生列表 - 共${pageInfo.totalCount}人</caption>
        <thead>
        <tr class="success">
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>出生日期</th>

            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${students}" var="s" varStatus="status">
            <tr>
                <td>${s.studentId}</td>
                <td>${s.name}</td>
                <td>${s.age}</td>
                <td>${s.sex}</td>
                <td>${s.birthday}</td>

                <td><a href="/StudentManagerSystem/edit?id=${s.id}"><span class="glyphicon glyphicon-edit"></span> </a></td>
                <td><a href="/StudentManagerSystem/delete?id=${s.id}"><span class="glyphicon glyphicon-trash"></span> </a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<nav class="pageDIV">
    <ul class="pagination">
        <li <c:if test="${!pageInfo.hasPrevious}">class="disabled"</c:if>>
            <a href="?pageInfo.start=0">
                <span>«</span>
            </a>
        </li>

        <li <c:if test="${!pageInfo.hasPrevious}">class="disabled"</c:if>>
            <a href="?pageInfo.start=${pageInfo.start-pageInfo.count}">
                <span>‹</span>
            </a>
        </li>

        <c:forEach begin="0" end="${pageInfo.last - 1}" varStatus="status">

            <c:if test="${status.count*pageInfo.count-pageInfo.start<=30 && status.count*pageInfo.count-pageInfo.start>=-10}">
                <li <c:if test="${status.index*pageInfo.count==pageInfo.start}">class="disabled"</c:if>>
                    <a
                            href="?pageInfo.start=${status.index*pageInfo.count}"
                            <c:if test="${status.index*pageInfo.count==pageInfo.start}">class="current"</c:if>
                    >${status.count}</a>
                </li>
            </c:if>
        </c:forEach>

        <li <c:if test="${!pageInfo.hasNext}">class="disabled"</c:if>>
            <a href="?pageInfo.start=${pageInfo.start+pageInfo.count}">
                <span>›</span>
            </a>
        </li>
        <li <c:if test="${!pageInfo.hasNext}">class="disabled"</c:if>>
            <a href="?pageInfo.start=${pageInfo.last}">
                <span>»</span>
            </a>
        </li>
    </ul>
</nav>

<div class="addDIV">

    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">增加学生</h3>
        </div>
        <div class="panel-body">

            <form method="get" action="/StudentManagerSystem/add" role="form">
                <table class="addTable">
                    <tr>
                        <td>学号：</td>
                        <td><input type="text" name="studentId" id="studentId" placeholder="请在这里输入学号"></td>
                    </tr>
                    <tr>
                        <td>姓名：</td>
                        <td><input type="text" name="name" id="name" placeholder="请在这里输入名字"></td>
                    </tr>
                    <tr>
                        <td>年龄：</td>
                        <td><input type="text" name="age" id="age" placeholder="请在这里输入年龄"></td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td><input type="radio" class="radio radio-inline" name="sex" value="男"> 男
                            <input type="radio" class="radio radio-inline" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td>出生日期：</td>
                        <td><input type="date" name="birthday" id="birthday" placeholder="请在这里输入出生日期"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>

                    </tr>

                </table>
            </form>
        </div>
    </div>

</div>

</body>
</html>