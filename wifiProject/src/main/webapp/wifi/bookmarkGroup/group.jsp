<%@page import="java.util.List"%>
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
	<h1>북마크 그룹</h1>
	<div>
		<a href="../index.jsp">홈</a> | 
		<a href="../history.jsp">위치 히스토리 목록</a> | 
		<a href="../api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="../bookmark/list.jsp">북마크 보기</a> | 
		<a href="group.jsp">북마크 그룹 관리</a>
	</div>
	<div class="addBookmarkGroupBnt">
		<button onclick="location.href='addBookmarkGroup.jsp'">북마크 그룹 이름 추가</button>
	</div>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
		<%
		List<BookmarkGroupDTO> bookmarkGroupList = new BookmarkGroupDAO().getBookmarkGroupList();
		
		if(bookmarkGroupList != null && !bookmarkGroupList.isEmpty()) {
			for(BookmarkGroupDTO bookmarkGroup : bookmarkGroupList) {
		%>
				<tr>
					<td><%=bookmarkGroup.getId() %></td>
					<td><%=bookmarkGroup.getName() %></td>
					<td><%=bookmarkGroup.getNo() %></td>
					<td><%=bookmarkGroup.getRegist_date() %></td>
					<td><%=bookmarkGroup.getUpdate_date() %></td>
					<td class="deleteLink">
						<a href="updateBookmarkGroup.jsp?id=<%=bookmarkGroup.getId() %>">수정</a>
						<a href="deleteBookmarkGroup.jsp?id=<%=bookmarkGroup.getId() %>">삭제</a>
					</td>
				</tr>
		<%
				}
			} else {
		%>
				<tr>
					<td colspan="6" style="text-align: center; ">정보가 존재하지 않습니다.</td>
				</tr>
		<%
			}
		%>
		</tbody>
	</table>
</body>
</html>