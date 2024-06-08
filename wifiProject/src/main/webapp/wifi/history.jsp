<%@page import="java.util.ArrayList"%>
<%@page import="history.HistoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="history.HistoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%
	float lat = request.getParameter("lat") == null ? 0 : Float.parseFloat(request.getParameter("lat"));
	float lnt = request.getParameter("lnt") == null ? 0 : Float.parseFloat(request.getParameter("lnt"));

	HistoryDAO historyDAO = new HistoryDAO();
	List<HistoryDTO> historyList = new ArrayList();
	
	if(lat != 0 && lnt != 0) {
		historyDAO.addHistory(lat, lnt);
	}
	
	historyList = historyDAO.getHistoryList();
%>
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="index.jsp">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark/list.jsp">북마크 보기</a> | 
		<a href="bookmarkGroup/group.jsp">북마크 그룹 관리</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
		<%
		if(historyList != null && !historyList.isEmpty()) {
			for(HistoryDTO history : historyList) {
		%>
			<tr>
				<td><%=history.getID() %></td>
				<td><%=history.getLNT() %></td>
				<td><%=history.getLAT() %></td>
				<td><%=history.getVIEW_DATE() %></td>
				<td>
					<button onclick="location.href='deleteHistory.jsp?id=<%=history.getID() %>'">삭제</button>
				</td>
			</tr>
		<%		
			}
		} else {
		%>
			<tr>
				<td colspan="5" style="text-align: center; ">정보가 없습니다.</td>
			</tr>
		<%
		}
		%>
		</tbody>
	</table>
</body>
</html>