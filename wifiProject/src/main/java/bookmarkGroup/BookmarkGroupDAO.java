package bookmarkGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDAO {
	
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

	public BookmarkGroupDAO() {
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
	 * 북마크 그룹 조회
	 * @return
	 */
	public List<BookmarkGroupDTO> getBookmarkGroupList() {
		connection();
		
		List<BookmarkGroupDTO> bookmarkList = new ArrayList();
		
		try {
			String sql = " SELECT id "
					   + "	 	, name "
					   + "	 	, IFNULL(no, \"\") as no "
					   + "	 	, regist_date "
					   + "	 	, IFNULL(update_date, \"\") as update_date "
					   + "	FROM bookmarkGroup "
					   + "	ORDER BY id; ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookmarkGroupDTO bookmarkGroup = new BookmarkGroupDTO();
				bookmarkGroup.setId(rs.getInt("id"));
				bookmarkGroup.setName(rs.getString("name"));
				bookmarkGroup.setNo(rs.getString("no"));
				bookmarkGroup.setRegist_date(rs.getString("regist_date"));
				bookmarkGroup.setUpdate_date(rs.getString("update_date"));
				
				bookmarkList.add(bookmarkGroup);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
		
		return bookmarkList;
	}
	
	public BookmarkGroupDTO getBookmarkGroup(int id) {
		connection();
		
		BookmarkGroupDTO bookmarkGroup = null;
		
		try {
			String sql = " SELECT id "
					   + "	 	, name "
					   + "	 	, IFNULL(no, \"\") as no "
					   + "	FROM bookmarkGroup "
					   + "	WHERE id = ?; ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bookmarkGroup = new BookmarkGroupDTO();
				
				bookmarkGroup.setId(rs.getInt("id"));
				bookmarkGroup.setName(rs.getString("name"));
				bookmarkGroup.setNo(rs.getString("no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
		
		return bookmarkGroup;
	}
	
	/**
	 * 북마크 그룹 추가
	 * @param name
	 * @param no
	 */
	public void addBookmarkGroup(String name, String no) {
		connection();
		
		try {
			String sql = " INSERT INTO bookmarkGroup (name, no) "
					   + " VALUES (?, ?); ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
	}
	
	/**
	 * 북마크 그룹 수정
	 * @param id
	 * @param name
	 * @param no
	 */
	public void updateBookmarkGroup(int id, String name, String no) {
		connection();
		
		try {
			String sql = " UPDATE bookmarkGroup "
					   + " SET name = ? "
					   + "   , no = ? "
					   + "   , update_date = DATETIME('now', 'localtime') "
					   + " WHERE id = ?; ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, no);
			pstmt.setInt(3, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
	}
	
	/**
	 * 북마크 그룹 삭제
	 * @param id
	 */
	public void deleteBookmarkGroup(int id) {
		connection();
		
		try {
			String sql = " DELETE FROM bookmarkGroup "
					   + " WHERE id = ?; ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(this.conn, this.pstmt, this.rs);
	}

}
