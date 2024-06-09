package bookmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDAO {
	
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

	public BookmarkDAO() {
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
	 * 북마크 조회
	 * @return
	 */
	public List<BookmarkDTO> getBookmarkList() {
		connection();
		
		List<BookmarkDTO> bookmarkList = new ArrayList();
		
		try {
			String sql = " SELECT bookmark_id "
					   + "	 	, bg.name as bookmark_name"
					   + "	 	, wifi_name "
					   + "	 	, b.regist_date as regist_date "
					   + " FROM bookmark b JOIN bookmarkGroup bg "
					   + "   ON b.group_id = bg.id "
					   + " ORDER BY bookmark_id; ";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookmarkDTO bookmark = new BookmarkDTO();
				bookmark.setBookmark_id(rs.getInt("bookmark_id"));
				bookmark.setBookmark_name(rs.getString("bookmark_name"));
				bookmark.setWifi_name(rs.getString("wifi_name"));
				bookmark.setRegist_date(rs.getString("regist_date"));
				
				bookmarkList.add(bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
		
		return bookmarkList;
	}
	
	public BookmarkDTO getBookmark(int bookmarkId) {
		connection();

		BookmarkDTO bookmark = null;
		
		try {
			String sql = " SELECT bookmark_id "
					   + "		, bg.name as bookmark_name"
					   + "	 	, wifi_name "
					   + "	 	, b.regist_date as regist_date "
					   + " FROM bookmark b JOIN bookmarkGroup bg "
					   + "   ON b.group_id = bg.id "
					   + " WHERE bookmark_id = ?; ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookmarkId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bookmark = new BookmarkDTO();
				
				bookmark.setBookmark_id(rs.getInt("bookmark_id"));
				bookmark.setBookmark_name(rs.getString("bookmark_name"));
				bookmark.setWifi_name(rs.getString("wifi_name"));
				bookmark.setRegist_date(rs.getString("regist_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
		
		return bookmark;
	}
	
	/**
	 * 북마크 추가
	 * @param bookmarkName
	 * @param wifiName
	 */
	public void addBookmark(String bookmarkName, String wifiName) {
		connection();
		
		try {
			String preSql = " SELECT id "
						  + " FROM bookmarkGroup "
						  + " WHERE name = ?; ";
			pstmt = conn.prepareStatement(preSql);
			pstmt.setString(1, bookmarkName);
			
			rs = pstmt.executeQuery();
			
			String sql = " INSERT INTO bookmark (group_id, wifi_name) "
					   + " VALUES(?, ?); ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rs.getInt("id"));
			pstmt.setString(2, wifiName);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
	}
	
	/**
	 * 북마크 삭제
	 * @param bookmarkId
	 */
	public void deleteBookmark(int bookmarkId) {
		connection();
		
		try {
			String sql = " DELETE FROM bookmark "
					   + " WHERE bookmark_id = ?; ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookmarkId);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
	}
}
