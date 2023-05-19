
package StudentInformationSystem.logic;

import java.sql.SQLException;

import StudentInformationSystem.entity.Fee;
import StudentInformationSystem.entity.RegisterSubjectFee;
import javafx.collections.ObservableList;
/**
 * Interface of FeeLogicImpl
 * Giao diện của FeeLogicImpl
 *
 */

 public interface FeeLogic {
	 
	/**
	* Get list of subject which student register in this semester
	* Lấy danh sách môn học sinh viên đăng ký trong học kỳ này
	* @param idStudent MSSV
	* @param semester 
	* @return registersubjectfeeList list of subject - đăng ký môn học Liệt kê danh sách môn học
	* @throws SQLException
	* @throws ClassNotFoundException
	*/
	public ObservableList<RegisterSubjectFee> getSubjectbyStudent(int idStudent, String semester) throws ClassNotFoundException, SQLException;
	
	/**
	  * Get list norm of fee
	  * Lấy danh sách định mức phí
	  * @return feeList list norm of fee
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
	public ObservableList<Fee> getFee() throws ClassNotFoundException, SQLException;
	
	/**
	 * Calculate total money this student must pay in this semester
	 * Tính tổng số tiền sinh viên này phải đóng trong học kỳ này
	 * @param idStudent student id
	 * @param semester 
	 * @return totalfee total money
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int totalFee(int idStudent, String semester) throws ClassNotFoundException, SQLException;

}
