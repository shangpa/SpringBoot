<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>ȸ������</title>
</head>
<body>
<h2>ȸ�� ���� �Է�</h2>
<form action="/regist/step3" method="post">
    <!-- name="email" : ������ �� �����͸� ������ �� ����� �Ķ���� �̸� -->
    <!-- id="email" : HTML ����� ���� �ĺ����̸�, JavaScript�� Css���� ������ �� ��� -->
    <p>
        <input type="hidden" name="view" value="registerStep3">
        <label>�̸���:<br>
            <input type="text" name="email" id="email" value="${registerRequest.email}" onfocus="this.value = '';">
        </label>
    </p>
    <p>
        <label>�̸�:<br>
            <input type="text" name="name" id="name" value="${registerRequest.name}" onfocus="this.value = '';">
        </label>
    </p>
    <p>
        <label>��й�ȣ:<br>
            <input type="password" name="password" id="password" value="${registerRequest.password}" onfocus="this.value = '';">
        </label>
    </p>
    <p>
        <label>��й�ȣ:<br>
            <input type="password" name="confirmPassword" id="confirmPassword" value="${registerRequest.confirmPassword}" onfocus="this.value = '';">
        </label>
    </p>

    <input type="submit" value="���� �Ϸ�"/>
</form>

</body>
</html>