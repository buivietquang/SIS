
package StudentInformationSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StudentInformationSystem.dao.NoticeDao;
import StudentInformationSystem.entity.Notice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NoticeDaoImpl extends BaseDaoImpl implements NoticeDao {

	/**
	 * Insert notice into database
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String insertNotice(Notice notice) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("insert into notice (title, content, time) VALUES (?,?,?)");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, notice.getTitle());
			pstatement.setString(2, notice.getContent());
			pstatement.setString(3, notice.getDatetime());		
			pstatement.executeUpdate();
			
			return "success";
	        
		} catch (SQLException e) {
			return "unsuccess";
	        
		} finally {
			closeConnection(conn);			
		}
		
	}

	/**
	 * Update notice from database
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String updateNotice(Notice notice) throws SQLException, ClassNotFoundException {
		// 
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("update notice set title = ?, content = ?, time = ? where idnotice = ?");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, notice.getTitle());
			pstatement.setString(2, notice.getContent());
			pstatement.setString(3, notice.getDatetime());
			pstatement.setInt(4, notice.getIdnotice());
			pstatement.executeUpdate();
			
			return "success";
			
		} catch (SQLException e) {
			return "unsuccess";
	        
		} finally {
			closeConnection(conn);			
		}
		
	}

	/**
	 * Delete a notice in database
	 * @param notie 
	 * @return success a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String deleteNotice(Notice notice) throws SQLException, ClassNotFoundException {
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("delete from notice where idnotice = ?");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(1, notice.getIdnotice());
			pstatement.executeUpdate();
			
			return "success";
			
		} catch (SQLException e) {
			return "unsuccess";
			
		} finally {
			closeConnection(conn);			
		}
		
	}
	
	/**
	 * Get list of all notice in database
	 * @return noticeList list of notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ObservableList<Notice> getNotice() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		Notice notice = null;
		StringBuilder sql = null;
		ObservableList<Notice> noticeList;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT * FROM notice");
			pstatement = conn.prepareStatement(sql.toString());
			
			rs = pstatement.executeQuery();
			noticeList = FXCollections.observableArrayList();
			while(rs.next()) {
				
				notice = new Notice(rs.getInt("idnotice"), rs.getString("title"), rs.getString("content"), rs.getString("time"));
				noticeList.add(notice);	
			}
		} finally {
			closeConnection(conn);
		}
		return noticeList;
	}

	/**
	 * Get detail of notice by notice id
	 * @param idNotice notice id
	 * @return notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Notice getDetailNotice(int idNotice) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		Notice notice = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT * FROM notice WHERE idNotice = ?");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(1, idNotice);
			
			rs = pstatement.executeQuery();
			while(rs.next()) {				
				notice = new Notice(rs.getInt("idnotice"), rs.getString("title"), 
						rs.getString("content"), rs.getString("time"));
			}
		} finally {
			closeConnection(conn);
		}
		return notice;
	}

}
