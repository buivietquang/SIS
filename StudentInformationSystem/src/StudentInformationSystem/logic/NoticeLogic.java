
package StudentInformationSystem.logic;

import java.sql.SQLException;

import StudentInformationSystem.entity.Notice;
import javafx.collections.ObservableList;
/**
 *Interface of NoticeLogicImpl
 * Giao điện của NoticeLogicImpl
 * 
 */
public interface NoticeLogic {
	
	/**
	 * Insert notice into database
	 * Thêm thông báo vào database
	 * @param notice 
	 * @return a string with success or fail - thông báo ok hay không
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String insertNotice(Notice notice) throws ClassNotFoundException, SQLException;
	
	/**
	 * Update notice from database
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String updateNotice(Notice notice) throws ClassNotFoundException, SQLException;
	
	/**
	 * Delete a notice in database
	 * @param notie 
	 * @return success a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String deleteNotice(Notice notice) throws ClassNotFoundException, SQLException;
	
	/**
	 * Get list of all notice in database
	 * Nhận danh sách tất cả các thông báo trong database
	 * @return noticeList list of notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<Notice> getNotice() throws ClassNotFoundException, SQLException;

	/**
	 * Get detail of notice by notice id
	 * Nhận chi tiết thông báo theo id thông báo
	 * @param idNotice notice id
	 * @return notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Notice getDetailNotice(int idNotice) throws ClassNotFoundException, SQLException;

}
