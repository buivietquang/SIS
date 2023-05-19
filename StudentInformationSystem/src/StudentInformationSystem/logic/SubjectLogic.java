
package StudentInformationSystem.logic;

import java.sql.SQLException;

import StudentInformationSystem.entity.Subject;
import javafx.collections.ObservableList;
/**
 * Interface of SubjectLogicImpl
 * Giao diện của SubjectLogicImpl
 */
public interface SubjectLogic {

	/**
	 * Get list of subject by subject code, subject name and institute
	 * Lấy danh sách môn học theo mã môn học, tên môn học và khoa
	 * @param subjectCode
	 * @param subjectName name of subject
	 * @param institute
	 * @return list of subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Subject> getListSubject(String subjectCode, String subjectName,
			  String institute) throws ClassNotFoundException, SQLException;
	
	/**
	 * Update info of subject
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateSubject(Subject subject) throws ClassNotFoundException, SQLException;

	/**
	 * Insert new subject into database
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean addSubject(Subject subject, ObservableList<String>requiredList) 
					throws ClassNotFoundException, SQLException;
}
