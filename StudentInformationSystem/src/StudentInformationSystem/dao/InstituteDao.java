
package StudentInformationSystem.dao;

import java.sql.SQLException;

import StudentInformationSystem.entity.Institute;
import javafx.collections.ObservableList;

/**
 * Interface of InstituteDaoImpl
 */
public interface InstituteDao {
	/**
	 * Get list of institute from database
	 * @return list of institute
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Institute> getListInstitute() throws ClassNotFoundException, SQLException;

	/**
	 * Insert institute into database
	 * @param nameInstitute name of institute
	 * @param phoneInstitute phone support number
	 * @param addressInstitute address of institute
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void InsertInstitute(String nameInstitute, String phoneInstitute, String addressInstitute)
			throws ClassNotFoundException, SQLException;

	/**
	 * Get info of institute by name
	 * @param institute name of institute
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<Institute> getInforInstitute(String institute) 
			throws SQLException, ClassNotFoundException;
}
