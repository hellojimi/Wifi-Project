<%@page import="bookmarkGroup.BookmarkGroupDAO"%>
<%@page import="bookmarkGroup.BookmarkGroupDTO"%>
<%@page import="java.util.List"%>
<%@page import="wifi.WifiDTO"%>
<%@page import="wifi.WifiDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="../resource/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%
	String mgrNo = request.getParameter("mgrNo");
	WifiDTO wifi = new WifiDAO().getWifiInfo(mgrNo);
%>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="index.jsp">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark/list.jsp">북마크 보기</a> | 
		<a href="bookmarkGroup/group.jsp">북마크 그룹 관리</a>
	</div>
	<form id="bookmarkForm" name="bookmarkForm" action="bookmark/addBookmarkSubmit.jsp" method="post" onsubmit="return bookmarkCheck()">
		<input type="hidden" name="wifiName" value=<%=wifi.getX_SWIFI_MAIN_NM() %>>
		<select name="bookmarkBox" class="bookmarkBox">
			<option value="">북마크 그룹 이름 선택</option>
			<%
			List<BookmarkGroupDTO> bookmarkGroupList = new BookmarkGroupDAO().getBookmarkGroupList();
			if(bookmarkGroupList != null && !bookmarkGroupList.isEmpty()) {
				for(BookmarkGroupDTO bookmark : bookmarkGroupList) {
			%>
					<option value="<%=bookmark.getName() %>"><%=bookmark.getName() %></option>
			<%
				}
			}
			%>
		</select>
		<input type="submit" value="북마크 추가하기">
	</form>
	<table>
		<colgroup>
			<col style="width: 20%;"/>
			<col style="width: 80%;"/>
		</colgroup>
		<% 
			if(wifi != null) {
		%>
				<tr>
					<th>거리</th>
					<td><%=wifi.getDistance() %></td>
				</tr>
				<tr>
					<th>관리번호</th>
					<td><%=wifi.getX_SWIFI_MGR_NO() %></td>
				</tr>
				<tr>
					<th>자치구</th>
					<td><%=wifi.getX_SWIFI_WRDOFC() %></td>
				</tr>
				<tr>
					<th>와이파이명</th>
					<td><%=wifi.getX_SWIFI_MAIN_NM() %></td>
				</tr>
				<tr>
					<th>도로명주소</th>
					<td><%=wifi.getX_SWIFI_ADRES1() %></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td><%=wifi.getX_SWIFI_ADRES2() %></td>
				</tr>
				<tr>
					<th>설치위치</th>
					<td><%=wifi.getX_SWIFI_INSTL_FLOOR() %></td>
				</tr>
				<tr>
					<th>설치유형</th>
					<td><%=wifi.getX_SWIFI_INSTL_TY() %></td>
				</tr>
				<tr>
					<th>설치기관</th>
					<td><%=wifi.getX_SWIFI_INSTL_MBY() %></td>
				</tr>
				<tr>
					<th>서비스구분</th>
					<td><%=wifi.getX_SWIFI_SVC_SE() %></td>
				</tr>
				<tr>
					<th>망종류</th>
					<td><%=wifi.getX_SWIFI_CMCWR() %></td>
				</tr>
				<tr>
					<th>설치년도</th>
					<td><%=wifi.getX_SWIFI_CNSTC_YEAR() %></td>
				</tr>
				<tr>
					<th>실내외구분</th>
					<td><%=wifi.getX_SWIFI_INOUT_DOOR() %></td>
				</tr>
				<tr>
					<th>WIFI접속환경</th>
					<td><%=wifi.getX_SWIFI_REMARS3() %></td>
				</tr>
				<tr>
					<th>X좌표</th>
					<td><%=wifi.getLNT() %></td>
				</tr>
				<tr>
					<th>Y좌표</th>
					<td><%=wifi.getLAT() %></td>
				</tr>		
				<tr>
					<th>작업일자</th>
					<td><%=wifi.getWORK_DTTM() %></td>
				</tr>		
		<%
			}
		%>																												
	</table>
	
<script>
	function bookmarkCheck() {		
		if(document.querySelector('.bookmarkBox').value == "") {
			alert("북마크를 선택해주세요.");
			document.bookmarkForm.bookmarkBox.focus();
			return false;
		}
	}
</script>
</body>
</html>