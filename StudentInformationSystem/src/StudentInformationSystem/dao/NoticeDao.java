
package StudentInformationSystem.dao;

import java.sql.SQLException;
import StudentInformationSystem.entity.Notice;
import javafx.collections.ObservableList;


public interface NoticeDao {
	
	/**
	 * Insert notice into database
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String insertNotice(Notice notice) throws SQLException, ClassNotFoundException;
	
	/**
	 * Update notice from database
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String updateNotice(Notice notice) throws SQLException, ClassNotFoundException;
	
	/**
	 * Delete a notice in database
	 * @param notie 
	 * @return success a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String deleteNotice(Notice notice) throws SQLException, ClassNotFoundException;
	
	/**
	 * Get list of all notice in database
	 * @return noticeList list of notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<Notice> getNotice() throws ClassNotFoundException, SQLException;

	/**
	 * Get detail of notice by notice id
	 * @param idNotice notice id
	 * @return notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Notice getDetailNotice(int idNotice) throws SQLException, ClassNotFoundException;

}
