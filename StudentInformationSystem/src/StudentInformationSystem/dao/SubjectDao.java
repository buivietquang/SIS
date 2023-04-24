package StudentInformationSystem.dao;

import java.sql.SQLException;

import StudentInformationSystem.entity.Subject;
import javafx.collections.ObservableList;

/**
 * Manipulation with Subject table in database
 * Thao tác với bảng môn trong db
 *
 */
public interface SubjectDao {

	/**
	 * Get list of subject by subject code, subject name and institute
	 * Lấy danh sách môn qua mã, tên và khoa
	 * @param subjectCode
	 * @param subjectName name of subject
	 * @param institute
	 * @return list of subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Subject> getListSubject(String subjectCode, String subjectName, String institute)
			throws ClassNotFoundException, SQLException;

	/**
	 * Update info of subject
	 * cập nhật thông tin môn
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateSubject(Subject subject) throws ClassNotFoundException, SQLException;

	/**
	 * Check if this subject is exist by subject code and subject name
	 * Kiểm tra xem môn học này có tồn tại hay không bằng mã môn học và tên môn học
	 * @param subjectCode
	 * @param subjectName name of subject
	 * @return true if exist
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkExistSubject(String subjectCode, String subjectName)
			throws SQLException, ClassNotFoundException;

	/**
	 * Insert new subject into database
	 * Thêm môn vào db
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean insertSubject(Subject subject, int institute) throws ClassNotFoundException, SQLException;

	/**
	 * Set required subject
	 * Đặt chủ đề bắt buộc
	 * @param subjectCode
	 * @param subjectRequiredList list of required subject
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void setRequiredSubject(String subjectCode, ObservableList<String> subjectRequiredList)
			throws SQLException, ClassNotFoundException;

}
