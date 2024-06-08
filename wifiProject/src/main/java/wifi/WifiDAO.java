package wifi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {
	
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

	public WifiDAO() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		connection();
	}
	
	private void connection() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\dev\\SQLite\\wifi.db");
			Statement state = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
            if (rs != null && !rs.isClosed()) {rs.close();}
            if (pstmt != null && !pstmt.isClosed()) {pstmt.close();}
            if (conn != null && !conn.isClosed()) {conn.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	/**
	 * 와이파이 저장
	 * @param list
	 */
	public void insertWifi(List<WifiDTO> list) {
		connection();
		
		try {
			String sql = " INSERT INTO wifi_info ( "
							+ "	X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, "
							+ "	X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, "
							+ "	X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
					   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < list.size(); i++) {
				pstmt.setString(1, list.get(i).getX_SWIFI_MGR_NO());
				pstmt.setString(2, list.get(i).getX_SWIFI_WRDOFC());
				pstmt.setString(3, list.get(i).getX_SWIFI_MAIN_NM());
				pstmt.setString(4, list.get(i).getX_SWIFI_ADRES1());
				pstmt.setString(5, list.get(i).getX_SWIFI_ADRES2());
				pstmt.setString(6, list.get(i).getX_SWIFI_INSTL_FLOOR());
				pstmt.setString(7, list.get(i).getX_SWIFI_INSTL_TY());
				pstmt.setString(8, list.get(i).getX_SWIFI_INSTL_MBY());
				pstmt.setString(9, list.get(i).getX_SWIFI_SVC_SE());
				pstmt.setString(10, list.get(i).getX_SWIFI_CMCWR());
				pstmt.setString(11, list.get(i).getX_SWIFI_CNSTC_YEAR());
				pstmt.setString(12, list.get(i).getX_SWIFI_INOUT_DOOR());
				pstmt.setString(13, list.get(i).getX_SWIFI_REMARS3());
				pstmt.setFloat(14, list.get(i).getLAT());
				pstmt.setFloat(15, list.get(i).getLNT());
				pstmt.setString(16, list.get(i).getWORK_DTTM());
				
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);		
	}
	
	/**
	 * 와이파이 조회
	 * @param lat
	 * @param lnt
	 * @return
	 */
	public List<WifiDTO> getWifiList(float lat, float lnt) {
		connection();
		
		List<WifiDTO> wifiList = new ArrayList();

		try {
			String sql = " SELECT ROUND(( "
					   + "			6371 * acos( "
					   + "			cos (radians(LAT)) "
					   + "			* cos(radians(?)) "
					   + "			* cos(radians(?) - radians(LNT)) "
					   + "			+ sin(radians(LAT)) "
					   + "			* sin(radians(?)) "
					   + "			) "
					   + "		), 6) as distance "
					   + "	 , * "
					   + " FROM wifi_info "
					   + " ORDER BY distance "
					   + " LIMIT 20; ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, lat);
			pstmt.setFloat(2, lnt);
			pstmt.setFloat(3, lat);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WifiDTO wifi = new WifiDTO();
				
				wifi.setDistance(rs.getFloat("distance"));
				wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				wifi.setLAT(rs.getFloat("LAT"));
				wifi.setLNT(rs.getFloat("LNT"));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
				
				wifiList.add(wifi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
		
		return wifiList;
	}
	
	public WifiDTO getWifiInfo(String mgrNo) {
		connection();
		
		WifiDTO wifi = null;
		
		try {
			String sql = " SELECT * "
					   + " FROM wifi_info "
					   + " WHERE X_SWIFI_MGR_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mgrNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				wifi = new WifiDTO();
				
				wifi.setDistance(0);
				wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				wifi.setLAT(rs.getFloat("LAT"));
				wifi.setLNT(rs.getFloat("LNT"));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
		
		return wifi;
	}

}
