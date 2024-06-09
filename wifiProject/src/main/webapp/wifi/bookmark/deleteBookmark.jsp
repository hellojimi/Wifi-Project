<%@page import="bookmark.BookmarkDAO"%>
<%@page import="bookmark.BookmarkDTO"%>
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
	int bookmarkId = Integer.parseInt(request.getParameter("id"));
	
	BookmarkDTO bookmark = new BookmarkDAO().getBookmark(bookmarkId);
%>
	<h1>북마크 삭제</h1>
	<div>
		<a href="../index.jsp">홈</a> | 
		<a href="../history.jsp">위치 히스토리 목록</a> | 
		<a href="../api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="list.jsp">북마크 보기</a> | 
		<a href="../bookmarkGroup/group.jsp">북마크 그룹 관리</a>
	</div>
	<div class="askDelete">
		북마크 그룹 이름을 삭제하시겠습니까?
	</div>
	<form action="deleteBookmarkSubmit.jsp" method="post">
		<input type="hidden" name="id" value="<%=bookmark.getBookmark_id() %>">
		<table>
			<colgroup>
				<col style="width: 20%;"/>
				<col style="width: 80%;"/>
			</colgroup>
			<tr>
				<th>북마크 이름</th>
				<td><%=bookmark.getBookmark_name() %></td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td><%=bookmark.getWifi_name() %></td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td><%=bookmark.getRegist_date() %></td>
			</tr>
			<tr>
				<td colspan="3" class="etc">
					<a href="list.jsp">돌아가기</a>
					<input type="submit" value="삭제">
				</td>
			</tr>		
		</table>
	</form>
</body>
</html>