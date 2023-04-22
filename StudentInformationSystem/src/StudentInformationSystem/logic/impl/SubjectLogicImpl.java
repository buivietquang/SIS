package StudentInformationSystem.logic.impl;

import java.sql.SQLException;

import StudentInformationSystem.dao.InstituteDao;
import StudentInformationSystem.dao.SubjectDao;
import StudentInformationSystem.dao.impl.InstituteDaoImpl;
import StudentInformationSystem.dao.impl.SubjectDaoImpl;
import StudentInformationSystem.entity.Institute;
import StudentInformationSystem.entity.Subject;
import StudentInformationSystem.logic.SubjectLogic;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import javafx.collections.ObservableList;

/**
 * Handle subject logic
 * Xử lý logic môn
 */
public class SubjectLogicImpl implements SubjectLogic {
	
	SubjectDao subjectDao = new SubjectDaoImpl();
	InstituteDao instituteDao = new InstituteDaoImpl();
	
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
	@Override
	public ObservableList<Subject> getListSubject(String subjectCode, String subjectName, String institute)
			throws ClassNotFoundException, SQLException {
	
		return subjectDao.getListSubject(subjectCode, subjectName, institute);
	}

	/**
	 * Update info of subject
	 * Cập nhật thông tin môn
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean updateSubject(Subject subject) throws ClassNotFoundException, SQLException {
		
		return subjectDao.updateSubject(subject);
	}

	/**
	 * Insert new subject into database
	 * thêm môn mới vào db
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean addSubject(Subject subject, ObservableList<String> requiredList) 
					throws ClassNotFoundException, SQLException {
		
		boolean check = subjectDao.checkExistSubject(subject.getSubjectCode(), subject.getSubjectName());
		ObservableList<Institute> instituteInfo = instituteDao.getInforInstitute(subject.getInstitute());
		if(check == true) {
			CustomAlert customAlert = new CustomAlert();
			customAlert.createAlert(Constants.WARNING, "Mã học phần hoặc tên học phần đã bị trùng");
			return false;
		}
		else {
			Boolean success;
			success = subjectDao.insertSubject(subject, instituteInfo.get(0).getIdInstitute());
			subjectDao.setRequiredSubject(subject.getSubjectCode(), requiredList);			
			return success;
		}
	}



}
