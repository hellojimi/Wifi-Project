<%@page import="bookmarkGroup.BookmarkGroupDAO"%>
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
	request.setCharacterEncoding("UTF-8");
	
	int id = Integer.parseInt(request.getParameter("id"));
	
	BookmarkGroupDAO bookmarkGroupDAO = new BookmarkGroupDAO();
	bookmarkGroupDAO.deleteBookmarkGroup(id);
%>

<script>
	alert("북마크 그룹 정보를 삭제하였습니다.");
	location.href="group.jsp";
</script>
</body>
</html>