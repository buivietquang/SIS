package StudentInformationSystem.dao;

import java.sql.SQLException;

import StudentInformationSystem.entity.RegisterSubject;
import javafx.collections.ObservableList;

/**
 * Manipulation with Registersubject table in database
 */

public interface RegisterSubjectDao {
	
	/**
	 * Get list of register subject by student id and semester
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
	 * @param idStudent 
	 * @return id register
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdRegister(int idStudent) throws ClassNotFoundException, SQLException;
	
	/**
	 * Get ID register by subject code
	 * @param subjectCode 
	 * @return ID subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdSubject(String subjectCode) throws ClassNotFoundException, SQLException;
	
	/**
	 * Insert subject into register subject table in database
	 * @param idStudent 
	 * @param subjectCode 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean insertSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException ;
	
	/**
	 * Delete subject from register subject table in database
	 * @param idStudent
	 * @param subjectCode
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean deleteSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException ;
	
	
}
