<%@page import="history.HistoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>

<body>
<%
	int id = Integer.parseInt(request.getParameter("id"));

	HistoryDAO historyDAO = new HistoryDAO();
	historyDAO.deleteHistory(id);
%>

<script type="text/javascript">
	location.href="history.jsp";
</script>
</body>
</html>