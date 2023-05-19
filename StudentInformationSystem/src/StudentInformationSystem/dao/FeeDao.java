package StudentInformationSystem.dao;

import java.sql.SQLException;

import StudentInformationSystem.entity.Fee;
import StudentInformationSystem.entity.RegisterSubjectFee;
import javafx.collections.ObservableList;

/**
 * Interface of FeeDaoImpl
 */


public interface FeeDao {
	
	/**
	 * Get list of subject which student register in this semester
	 * @param idStudent MSSV
	 * @param semester 
	 * @return registersubjectfeeList list of subject
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<RegisterSubjectFee> getSubjectbyStudent(int idStudent, String semester) 
			throws ClassNotFoundException, SQLException;
	/**
	  * Get list norm of fee
	  * @return feeList list norm of fee
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
	public ObservableList<Fee> getFee() throws ClassNotFoundException, SQLException;

}
