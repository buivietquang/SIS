package StudentInformationSystem.logic;

import java.sql.SQLException;

import StudentInformationSystem.entity.RegisterSubject;
import javafx.collections.ObservableList;

/**
 * Manipulate with RegisterSubject table in Database
 * Thao tác với bảng RegisterSubject trong Database
 */

public interface RegisterSubjectLogic {
	
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
	 * Tìm kiếm thông tin môn theo mã môn
	 * @param subjectCode 
	 * @return list of subject
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public RegisterSubject searchBeforeAddSubject(String subjectCode) 
			throws SQLException, ClassNotFoundException;
	
	/**
	 * Search registered subject 
	 * Check if student pass this registerd subject
	 * Tìm kiếm chủ đề đã đăng ký
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
	 * Nhận đăng ký ID của sinh viên này trong csdl
	 * @param idStudent 
	 * @return ID register
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdRegister(int idStudent) throws ClassNotFoundException, SQLException;
	
	/**
	 * Get ID register by subject code
	 * Nhận ID đăng ký theo mã môn
	 * @param subjectCode 
	 * @return ID subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdSubject(String subjectCode) throws ClassNotFoundException, SQLException;
	
	/**
	 * Insert subject into register subject table in database 
	 * Chèn môn vào bảng đăng ký trong csdl
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
