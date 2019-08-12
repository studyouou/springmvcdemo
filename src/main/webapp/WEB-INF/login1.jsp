<%--
  Created by IntelliJ IDEA.
  User: 欧根
  Date: 2019/7/19
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/sessions/user" method="post" enctype="multipart/form-data">
        phone : <input type="text" name="phone">
        name : <input type="text" name="name"></br>
        age : <input type="text" name="age"></br>
        file : <input type="file" name="multipartFile">
        <input type="submit" value="提交">
    </form>
    <form action="/upload" method="post" enctype="multipart/form-data">
        file : <input type="file" name="file">
        <input type="submit" value="提交">
    </br></br></br>
        <a href="/download">点击下载文件</a>
    </form>


</body>
</html>
