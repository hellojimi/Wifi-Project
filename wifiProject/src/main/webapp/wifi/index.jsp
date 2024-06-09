<%@page import="java.util.ArrayList"%>
<%@page import="wifi.WifiDTO"%>
<%@page import="java.util.List"%>
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
	float lat = request.getParameter("lat") == null ? 0 : Float.parseFloat(request.getParameter("lat"));
	float lnt = request.getParameter("lnt") == null ? 0 : Float.parseFloat(request.getParameter("lnt"));

	List<WifiDTO> wifiList = new ArrayList();
	if(lat != 0 && lnt != 0) {
		wifiList = new WifiDAO().getWifiList(lat, lnt);		
	}
%>

	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="index.jsp">홈</a> | 
		<a href="#" onclick="goHistory()">위치 히스토리 목록</a> | 
		<a href="api.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark/list.jsp">북마크 보기</a> | 
		<a href="bookmarkGroup/group.jsp">북마크 그룹 관리</a>
	</div>
	<div class="info">
		LAT: <input type="text" id="lat" value="<%=lat %>" readonly />
		LNT: <input type="text" id="lnt" value="<%=lnt %>" readonly />
		<button onclick="askLocation()">내 위치 가져오기</button>
		<button onclick="loadWifi()">근처 WIFI 정보 보기</button>
	</div>
	<table>
		<thead>
			<tr>
				<th>거리(km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
		<%			
			if(wifiList != null && !wifiList.isEmpty()) {
				for(WifiDTO wifi : wifiList) {
		%>
					<tr>
						<td><%=wifi.getDistance() %></td>
						<td><%=wifi.getX_SWIFI_MGR_NO() %></td>
						<td><%=wifi.getX_SWIFI_WRDOFC() %></td>
						<td>
							<a href="detail.jsp?mgrNo=<%=wifi.getX_SWIFI_MGR_NO() %>" id="mgrNo"><%=wifi.getX_SWIFI_MAIN_NM() %></a>
						</td>
						<td><%=wifi.getX_SWIFI_ADRES1() %></td>
						<td><%=wifi.getX_SWIFI_ADRES2() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_FLOOR() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_TY() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_MBY() %></td>
						<td><%=wifi.getX_SWIFI_SVC_SE() %></td>
						<td><%=wifi.getX_SWIFI_CMCWR() %></td>
						<td><%=wifi.getX_SWIFI_CNSTC_YEAR() %></td>
						<td><%=wifi.getX_SWIFI_INOUT_DOOR() %></td>
						<td><%=wifi.getX_SWIFI_REMARS3() %></td>
						<td><%=wifi.getLNT() %></td>
						<td><%=wifi.getLAT() %></td>
						<td><%=wifi.getWORK_DTTM() %></td>
					</tr>
		<%	
				}
			} else {
		%>
				<tr>
					<td colspan="17" style="text-align: center; ">위치 정보를 입력한 후에 조회해 주세요.</td>
				</tr>
		<%
			}
		%>
		</tbody>
	</table>
	
<script>
	function askLocation() {
		if(navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(success, error);			
		} else {
			alert("해당 브라우저는 위치 정보를 제공하지 않습니다.");
		}
	}
	
	function success(position) {
		const lat = position.coords.latitude;
		const lnt = position.coords.longitude;
		
		document.getElementById("lat").value = lat;
		document.getElementById("lnt").value = lnt;
	}
	
	function error() {
		alert("위치 정보를 사용할 수 없습니다.");
	}
	
	function loadWifi() {
		const lat = document.getElementById("lat").value;
		const lnt = document.getElementById("lnt").value;
		
		location.href="index.jsp?lat=" + lat + "&lnt=" + lnt;
	}
		
	function goHistory() {
		const lat = document.getElementById("lat").value;
		const lnt = document.getElementById("lnt").value;
		
		location.href="history.jsp?lat=" + lat + "&lnt=" + lnt;
	}
</script>

</body>
</html>