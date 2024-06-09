<%@page import="bookmark.BookmarkDAO"%>
<%@page import="bookmark.BookmarkDTO"%>
<%@page import="java.util.List"%>
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
	<h1>북마크 목록</h1>
	<div>
		<a href="../index.jsp">홈</a> | 
		<a href="../history.jsp">위치 히스토리 목록</a> | 
		<a href="../api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="list.jsp">북마크 보기</a> | 
		<a href="../bookmarkGroup/group.jsp">북마크 그룹 관리</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
		<%
		List<BookmarkDTO> bookmarkList = new BookmarkDAO().getBookmarkList();
		
		if(bookmarkList != null && !bookmarkList.isEmpty()) {
			for(BookmarkDTO bookmark : bookmarkList) {
		%>
				<tr>
					<td><%=bookmark.getBookmark_id() %></td>
					<td><%=bookmark.getBookmark_name() %></td>
					<td><%=bookmark.getWifi_name() %></td>
					<td><%=bookmark.getRegist_date() %></td>
					<td class="deleteLink">
						<a href="deleteBookmark.jsp?id=<%=bookmark.getBookmark_id() %>">삭제</a>
					</td>
				</tr>
		<%
				}
			} else {
		%>
				<tr>
					<td colspan="5" style="text-align: center; ">정보가 존재하지 않습니다.</td>
				</tr>
		<%
			}
		%>
		</tbody>
	</table>
</body>
</html>