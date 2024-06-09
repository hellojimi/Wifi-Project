<%@page import="bookmark.BookmarkDAO"%>
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

	String wifiName = request.getParameter("wifiName");
	String bookmarkName = request.getParameter("bookmarkName");
	
	BookmarkDAO bookmarkDAO = new BookmarkDAO();
	bookmarkDAO.addBookmark(bookmarkName, wifiName);
%>

<script>
	alert("북마크 정보를 추가하였습니다.");
	location.href="list.jsp";
</script>
</body>
</html>