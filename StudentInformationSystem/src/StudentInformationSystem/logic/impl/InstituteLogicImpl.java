
package StudentInformationSystem.logic.impl;

import java.sql.SQLException;

import StudentInformationSystem.dao.InstituteDao;
import StudentInformationSystem.dao.impl.InstituteDaoImpl;
import StudentInformationSystem.entity.Institute;
import StudentInformationSystem.logic.InstituteLogic;
import javafx.collections.ObservableList;

/**
 * xử lý logic khoa viện
 * 
 *
 */


public class InstituteLogicImpl implements InstituteLogic {
	
	/**
	 * Get list of institute from database
	 * Lấy thộng tin  khoa viện trong db
	 * @return list of institute
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<Institute> getListInstitute() throws ClassNotFoundException, SQLException {
		InstituteDao instituteDao = new InstituteDaoImpl();
		return instituteDao.getListInstitute();
	}
	
	/**
	 * Check valid before insert
	 * Kiểm tra hợp lệ trước khi chèn
	 * @param tenKhoa institute name
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean checkValidInstitute(String tenKhoa) throws ClassNotFoundException, SQLException{
		InstituteDao instituteDao = new InstituteDaoImpl();
		ObservableList<Institute> listInstitute = instituteDao.getListInstitute();
		if(tenKhoa == null)
		    return false;
		for(Institute inst: listInstitute){
		    if(inst.getInstituteName().equals(tenKhoa)){
		         return false;
		    }
		}       
		return true;
	}	
		// Search ten khoa coa kha nang giong nhat
		public String searchInstitute(String tenKhoa) throws ClassNotFoundException, SQLException{
			tenKhoa = tenKhoa.toLowerCase();
			InstituteDao instituteDao = new InstituteDaoImpl();
			ObservableList<Institute> listInstitute = instituteDao.getListInstitute();
			for(Institute inst: listInstitute){
				String nameInstitute = inst.getInstituteName().toLowerCase();
				if(nameInstitute.matches("(.*)"+tenKhoa+"(.*)"))
					return nameInstitute;
			}
			return "";         
	 }

}
