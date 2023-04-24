package StudentInformationSystem.dao;

import java.sql.SQLException;

import StudentInformationSystem.entity.RegisterSubject;
import javafx.collections.ObservableList;

/**
 * Manipulation with Registersubject table in database
 * Thao tác với bảng Registersubject trong db
 */

public interface RegisterSubjectDao {
	
	/**
	 * Get list of register subject by student id and semester
	 * Lấy danh sách đăng ký môn học theo id sinh viên và học kỳ
	 * @param idStudent MSSV
	 * @param semester 
	 * @return list of register subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<RegisterSubject> getRegisterSubject(int idStudent, String semester) 
			throws ClassNotFoundException, SQLException;
	
	/**
	 * Search info of subject by subjectCode
	 * Tìm thông tin môn qua mã môn
	 * @param subjectCode 
	 * @return subject
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public RegisterSubject searchBeforeAddSubject(String subjectCode) 
			throws SQLException, ClassNotFoundException;
	
	/**
	 * Search registered subject 
	 * Check if student pass this registerd subject
	 * Tìm kiếm môn đã đăng ký
	 * Kiểm tra xem sinh viên có vượt qua môn đăng ký này không
	 * @param subjectCode 
	 * @param idStudent MSSV
	 * @return list of subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<String> checkRequiredSubject(String subjectCode, int idStudent) 
			throws ClassNotFoundException, SQLException ;
	
	/**
	 * Get ID register of this student in database 
	 * Nhận đăng ký ID của sinh viên này trong db
	 * @param idStudent 
	 * @return id register
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdRegister(int idStudent) throws ClassNotFoundException, SQLException;
	
	/**
	 * Get ID register by subject code
	 * Lấy id đăng kí qua mã môn
	 * @param subjectCode 
	 * @return ID subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdSubject(String subjectCode) throws ClassNotFoundException, SQLException;
	
	/**
	 * Insert subject into register subject table in database
	 * Thêm môn vào bảng đăng ký trong db
	 * @param idStudent 
	 * @param subjectCode 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean insertSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException ;
	
	/**
	 * Delete subject from register subject table in database
	 * xóa
	 * @param idStudent
	 * @param subjectCode
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean deleteSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException ;
	
	
}
