<%@page import="wifi.WifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
	div {
		text-align: center;
	}
</style>
</head>

<body>
<%
	WifiService wifi = new WifiService();
	int size = wifi.insertWifi(1, 10);
%>
<div>
	<h1><%=size %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<a href="index.jsp">홈으로 가기</a>
</div>
</body>
</html>