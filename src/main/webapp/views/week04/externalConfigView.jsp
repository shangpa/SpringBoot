<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>week04/externalConfigView.jsp</title>
</head>
<body>
    Server Port ${obj.serverPort}<br>
    Server Address ${obj.serverAddress}<br>
    Message ${obj.greeting}<br>
    =====================================<br>
    DataSource URL : ${obj.url}<br>
    DataSource userName : ${obj.userName}<br>
    DataSource password : ${obj.password}<br>
</body>
</html>