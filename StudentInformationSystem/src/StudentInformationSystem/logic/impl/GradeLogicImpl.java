
package StudentInformationSystem.logic.impl;

import java.sql.SQLException;

import StudentInformationSystem.dao.GradeDao;
import StudentInformationSystem.dao.impl.GradeDaoImpl;
import StudentInformationSystem.entity.Grade;
import StudentInformationSystem.logic.GradeLogic;
import javafx.collections.ObservableList;

/**
 * Handle logic of student grade
 * 
 * @author TKXDPM-Nhom1
 *
 */
public class GradeLogicImpl implements GradeLogic{
	
	GradeDao gradeDao = new GradeDaoImpl();
	
	/**
	 * Get student course mark by MSSV 
	 * @param username MSSV
	 * @return list of mark
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<Grade> getGrade(String username) throws ClassNotFoundException, SQLException {		
		return gradeDao.getGrade(username);
	}

	/**
	 * Update student mark
	 * @param username MSSV
	 * @param grade information about this subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean updateGrade(String username, Grade grade) 
					throws ClassNotFoundException, SQLException {
		return gradeDao.updateGrade(username, grade);
	}

	/**
	 * Get list of semester which this student learn
	 * @param username MSSV
	 * @return list of semester
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<String> getSemester(String username) throws ClassNotFoundException, SQLException {
		return gradeDao.getSemester(username);
	}
	
}
