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
	int bookmarkId = Integer.parseInt(request.getParameter("id"));
	
	BookmarkDAO bookmarkDAO = new BookmarkDAO();
	bookmarkDAO.deleteBookmark(bookmarkId);
%>

<script>
	alert("북마크 정보를 삭제하였습니다.");
	location.href="list.jsp";
</script>
</body>
</html>