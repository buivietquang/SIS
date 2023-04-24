
package StudentInformationSystem.dao;

import java.sql.SQLException;
import StudentInformationSystem.entity.Notice;
import javafx.collections.ObservableList;

public interface NoticeDao {
	
	/**
	 * Insert notice into database
	 * Thêm thông báo vào db
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String insertNotice(Notice notice) throws SQLException, ClassNotFoundException;
	
	/**
	 * Update notice from database
	 * Cập nhật
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String updateNotice(Notice notice) throws SQLException, ClassNotFoundException;
	
	/**
	 * Delete a notice in database
	 * Xóa
	 * @param notie 
	 * @return success a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String deleteNotice(Notice notice) throws SQLException, ClassNotFoundException;
	
	/**
	 * Get list of all notice in database
	 * Lấy thông báo trong db
	 * @return noticeList list of notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<Notice> getNotice() throws ClassNotFoundException, SQLException;

	/**
	 * Get detail of notice by notice id
	 * chi tiết thông báo bằng id
	 * @param idNotice notice id
	 * @return notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Notice getDetailNotice(int idNotice) throws SQLException, ClassNotFoundException;

}
