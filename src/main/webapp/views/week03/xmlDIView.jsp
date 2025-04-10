<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>week03/xmlDIView.jsp</title>
</head>
<body>
xml 설정에 의해 생성된 객체 : ${obj}<br>
생성자를 통해 DI된 객체 : ${obj.sms}<br>
생성자를 통해 DI된 기본 데이터 : ${obj.num}<br>

setter 를 통해 di된 객체 : ${obj.unit}<br>
setter 를 통해 di된 기본 데이터 : ${obj.msg}<br>
</body>
</html>