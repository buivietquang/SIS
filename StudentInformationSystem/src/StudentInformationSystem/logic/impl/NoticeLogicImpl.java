
package StudentInformationSystem.logic.impl;

import java.sql.SQLException;

import StudentInformationSystem.dao.NoticeDao;
import StudentInformationSystem.dao.impl.NoticeDaoImpl;
import StudentInformationSystem.entity.Notice;
import StudentInformationSystem.logic.NoticeLogic;
import javafx.collections.ObservableList;

/**
 * Handle notice 
 * Xử lý thông báo
 */
public class NoticeLogicImpl implements NoticeLogic {

	/**
	 * Insert notice into database
	 * Chèn thông báo vào db
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String insertNotice(Notice notice) throws ClassNotFoundException, SQLException {
			NoticeDaoImpl noticeDaoImpl = new NoticeDaoImpl();
			return noticeDaoImpl.insertNotice(notice);
	}

	/**
	 * Update notice from database
	 * cập nhật
	 * @param notice 
	 * @return a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String updateNotice(Notice notice) throws ClassNotFoundException, SQLException {
			NoticeDaoImpl noticeDaoImpl = new NoticeDaoImpl();
			return noticeDaoImpl.updateNotice(notice);
	}

	/**
	 * Delete a notice in database
	 * Xóa
	 * @param notie 
	 * @return success a string with success or fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public String deleteNotice(Notice notice) throws ClassNotFoundException, SQLException {
			NoticeDaoImpl noticeDaoImpl = new NoticeDaoImpl();
			return noticeDaoImpl.deleteNotice(notice);
	}

	/**
	 * Get list of all notice in database
	 * Nhận danh sách tất cả các thông báo trong db
	 * @return noticeList list of notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ObservableList<Notice> getNotice() throws ClassNotFoundException, SQLException {
		NoticeDaoImpl notice = new NoticeDaoImpl();
		return notice.getNotice();
	}

	/**
	 * Get detail of notice by notice id
	 * Nhận chi tiết thông báo theo id thông báo
	 * @param idNotice notice id
	 * @return notice
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Notice getDetailNotice(int idNotice) throws ClassNotFoundException, SQLException {
		NoticeDao notice = new NoticeDaoImpl();
		return notice.getDetailNotice(idNotice);
	}
}
