<%@page import="bookmarkGroup.BookmarkGroupDAO"%>
<%@page import="bookmarkGroup.BookmarkGroupDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="../../resource/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	BookmarkGroupDTO bookmarkGroup = new BookmarkGroupDAO().getBookmarkGroup(id);
%>
	<h1>북마크 그룹 수정</h1>
	<div>
		<a href="../index.jsp">홈</a> | 
		<a href="../history.jsp">위치 히스토리 목록</a> | 
		<a href="../api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="../bookmark/list.jsp">북마크 보기</a> | 
		<a href="group.jsp">북마크 그룹 관리</a>
	</div>
	<form action="updateBookmarkGroupSubmit.jsp" method="post">
		<input type="hidden" name="id" value="<%=bookmarkGroup.getId() %>">
		<table>
			<colgroup>
				<col style="width: 20%;"/>
				<col style="width: 80%;"/>
			</colgroup>
			<tr>
				<th>북마크 이름</th>
				<td>
					<input type="text" name="name" value="<%=bookmarkGroup.getName() %>">
				</td>
			</tr>
			<tr>
				<th>순서</th>
				<td>
					<input type="text" name="no" value="<%=bookmarkGroup.getNo() %>">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="etc">
					<a href="group.jsp">돌아가기</a>
					<input type="submit" value="수정">
				</td>
			</tr>		
		</table>
	</form>
</body>
</html>