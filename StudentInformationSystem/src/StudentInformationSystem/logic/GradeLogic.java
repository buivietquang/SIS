
package StudentInformationSystem.logic;

import java.sql.SQLException;

import StudentInformationSystem.entity.Grade;
import javafx.collections.ObservableList;

/**
 * Interface of GradeLogicImpl
 * Giao diện của GradeLogicImpl
 */
public interface GradeLogic {
	
	/**
	 * Get student course mark by MSSV 
	 * Nhận điểm môn học sinh viên qua MSSV
	 * @param username MSSV
	 * @return list of mark
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Grade> getGrade(String username) throws ClassNotFoundException, SQLException;

	/**
	 * Update student mark
	 * Cập nhật điếm sinh viên
	 * @param username MSSV
	 * @param grade information about this subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateGrade(String username, Grade grade) throws ClassNotFoundException, SQLException;
	
	/**
	 * Get list of semester which this student learn
	 * Lấy danh sách học kỳ mà sinh viên này học
	 * @param username MSSV
	 * @return list of semester
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<String> getSemester(String username) throws ClassNotFoundException, SQLException;
}

