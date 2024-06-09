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
	<h1>북마크 그룹 추가</h1>
	<div>
		<a href="../index.jsp">홈</a> | 
		<a href="../history.jsp">위치 히스토리 목록</a> | 
		<a href="../api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="../bookmark/list.jsp">북마크 보기</a> | 
		<a href="group.jsp">북마크 그룹 관리</a>
	</div>
	<form action="addBookmarkGroupSubmit.jsp" method="post">
		<table>
			<colgroup>
				<col style="width: 20%;"/>
				<col style="width: 80%;"/>
			</colgroup>
			<tr>
				<th>북마크 이름</th>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<th>순서</th>
				<td>
					<input type="text" name="no">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="addBookmark">
					<input type="submit" value="추가">
				</td>
			</tr>		
		</table>
	</form>
</body>
</html>