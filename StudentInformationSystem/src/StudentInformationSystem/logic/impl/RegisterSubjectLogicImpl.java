package StudentInformationSystem.logic.impl;

import java.sql.SQLException;

import StudentInformationSystem.dao.RegisterSubjectDao;
import StudentInformationSystem.dao.impl.RegisterSubjectDaoImpl;
import StudentInformationSystem.entity.RegisterSubject;
import StudentInformationSystem.logic.RegisterSubjectLogic;
import javafx.collections.ObservableList;

/**
 * Interact with Register Subject table in database
 */

public class RegisterSubjectLogicImpl implements RegisterSubjectLogic{
	
	RegisterSubjectDao registerSubjectDao = new RegisterSubjectDaoImpl();
	
	/**
	 * Get list of register subject by student id and semester
	 * @param idStudent MSSV
	 * @param semester 
	 * @return list of register subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<RegisterSubject> getRegisterSubject(int idStudent, String semester) 
			throws ClassNotFoundException, SQLException{
		return registerSubjectDao.getRegisterSubject(idStudent, semester);
	}
	
	/**
	 * Search info of subject by subjectCode
	 * @param subjectCode 
	 * @return list of subject
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public RegisterSubject searchBeforeAddSubject(String subjectCode) 
			throws SQLException, ClassNotFoundException{
		return registerSubjectDao.searchBeforeAddSubject(subjectCode);
	}
	
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
			throws ClassNotFoundException, SQLException {
		return registerSubjectDao.checkRequiredSubject(subjectCode, idStudent);
	}
	
	/**
	 * Get ID register of this student in database 
	 * @param idStudent 
	 * @return ID register
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdRegister(int idStudent) throws ClassNotFoundException, SQLException{
		return registerSubjectDao.getIdRegister(idStudent);
	}
	
	/**
	 * Get ID register by subject code
	 * @param subjectCode 
	 * @return ID subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdSubject(String subjectCode) throws ClassNotFoundException, SQLException{
		return registerSubjectDao.getIdSubject(subjectCode);
	}
	
	/**
	 * Insert subject into register subject table in database
	 * @param idStudent 
	 * @param subjectCode 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean insertSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException {
		return registerSubjectDao.insertSubject(idStudent, subjectCode);
	}
	
	/**
	 * Delete subject from register subject table in database
	 * @param idStudent
	 * @param subjectCode
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean deleteSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException {
		return registerSubjectDao.deleteSubject(idStudent, subjectCode);
	}
	
	
}
