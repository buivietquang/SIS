
package StudentInformationSystem.logic.impl;

import java.sql.SQLException;

import StudentInformationSystem.dao.impl.FeeDaoImpl;
import StudentInformationSystem.entity.Fee;
import StudentInformationSystem.entity.RegisterSubjectFee;
import StudentInformationSystem.logic.FeeLogic;
import javafx.collections.ObservableList;
/**
 * Handle logic of fee
 * Xử lý fee logic
 *
 */
public class FeeLogicImpl implements FeeLogic {

	/**
	* Get list norm of fee
	* Lấy danh sách định mức phí
	* @return feeList list norm of fee
	* @throws SQLException
	* @throws ClassNotFoundException
	*/
	@Override
	public ObservableList<Fee> getFee() throws ClassNotFoundException, SQLException {
		FeeDaoImpl feeDaoImpl = new FeeDaoImpl();
		return feeDaoImpl.getFee();
	}

	/**
	* Get list of subject which student register in this semester
	* Lấy danh sách môn học sinh viên đăng ký trong học kỳ này
	* @param idStudent MSSV
	* @param semester 
	* @return registersubjectfeeList list of subject
	* @throws SQLException
	* @throws ClassNotFoundException
	*/
	@Override
	public ObservableList<RegisterSubjectFee> getSubjectbyStudent(int idStudent, String semester)
			throws ClassNotFoundException, SQLException {
		FeeDaoImpl feeDaoImpl = new FeeDaoImpl();
		return feeDaoImpl.getSubjectbyStudent(idStudent, semester);
	}

	/**
	 * Calculate total money this student must pay in this semester
	 * Tính tổng số tiền sinh viên này phải đóng trong học kỳ này
	 * @param idStudent student id
	 * @param semester 
	 * @return totalfee total money
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public int totalFee(int idStudent, String semester) throws ClassNotFoundException, SQLException {
		FeeDaoImpl feeDaoImpl = new FeeDaoImpl();
		ObservableList<RegisterSubjectFee> listSubject = feeDaoImpl.getSubjectbyStudent(idStudent, semester);
		ObservableList<Fee> listFee = feeDaoImpl.getFee();
		int totalfee = 0;
		
		for(int i = 0; i < listSubject.size(); i++) {
			if(listSubject.get(i).getIdinstituteOfstudent() == listSubject.get(i).getIdinstituteOfsubject())
				totalfee += listFee.get(0).getMoney() * listSubject.get(i).getCredittuition();
			else totalfee += listFee.get(1).getMoney() * listSubject.get(i).getCredittuition();	
		}
		return totalfee;
	}

}
