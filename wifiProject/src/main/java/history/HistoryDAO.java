package history;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
	
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

	public HistoryDAO() {
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
	 * 히스토리 조회
	 * @return
	 */
	public List<HistoryDTO> getHistoryList() {
		connection();
		
		List<HistoryDTO> historyList = new ArrayList();
		
		try {
			String sql = " SELECT * "
					   + " FROM history "
					   + " ORDER BY ID DESC; ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HistoryDTO history = new HistoryDTO();
				
				history.setID(rs.getInt("ID"));
				history.setLAT(rs.getString("LAT"));
				history.setLNT(rs.getString("LNT"));
				history.setVIEW_DATE(rs.getString("VIEW_DATE"));
				
				historyList.add(history);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
		
		return historyList;
	}
	
	/**
	 * 히스토리 저장
	 * @param lat
	 * @param lnt
	 */
	public void addHistory(float lat, float lnt) {
		connection();
		
		try {
			String sql = " INSERT INTO history (lat, lnt) "
					   + " VALUES(?, ?); ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, lat);
			pstmt.setFloat(2, lnt);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
	}
	
	/**
	 * 히스토리 삭제
	 * @param id
	 */
	public void deleteHistory(int id) {
		connection();
		
		try {
			String sql = " DELETE FROM history "
					   + " WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
	}
}
