
package StudentInformationSystem.logic;

import java.sql.SQLException;

import StudentInformationSystem.entity.Institute;
import javafx.collections.ObservableList;

/**
 * Interface of InstituteLogicImpl
 * Giao diện của InstituteLogicImpl
 */
public interface InstituteLogic {
	
	/**
	 * Get list of institute from database
	 * Lấy danh sách viện từ cơ sở dữ liệu
	 * @return list of institute
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Institute> getListInstitute() throws ClassNotFoundException, SQLException;

}
