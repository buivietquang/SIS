
package StudentInformationSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StudentInformationSystem.dao.FeeDao;
import StudentInformationSystem.entity.Fee;

import StudentInformationSystem.entity.RegisterSubjectFee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FeeDaoImpl extends BaseDaoImpl implements FeeDao {
	
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
	public ObservableList<Fee> getFee() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		Fee fee = null;
		StringBuilder sql = null;
		ObservableList<Fee> feeList;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT * FROM fee");
			pstatement = conn.prepareStatement(sql.toString());
			
			rs = pstatement.executeQuery();
			feeList = FXCollections.observableArrayList();
			while(rs.next()) {
				
				fee = new Fee(rs.getInt("idfee"), rs.getString("feeName"), rs.getInt("money"));
				feeList.add(fee);	
			}
		} finally {
			closeConnection(conn);
		}
		return feeList;
	}

	/**
	  * Get list norm of fee
	  * @return feeList list norm of fee
	  * @throws SQLException
	  * @throws ClassNotFoundException
	  */
	@Override
	public ObservableList<RegisterSubjectFee> getSubjectbyStudent(int idStudent, String semester)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		RegisterSubjectFee registersubjectfee = null;
		StringBuilder sql = null;
		ObservableList<RegisterSubjectFee> registersubjectfeeList;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT db_sis.student.idStudent, db_sis.subject.idSubject, db_sis.subject.subjectCode, db_sis.subject.name, ");
			sql.append("db_sis.subject.creditSubject, db_sis.subject.creditTuition, db_sis.subject.idInstitute as sub_Institute, ");
			sql.append("db_sis.student.idInstitute as stu_Institute, db_sis.institute.instituteName, db_sis.registersubject.semester ");
			
			sql.append("FROM db_sis.student, db_sis.registersubject, db_sis.detailregistersubject, db_sis.subject, db_sis.institute ");
			
			sql.append("WHERE db_sis.student.idStudent = db_sis.registersubject.idStudent and ");
			sql.append("db_sis.registersubject.idRegister = db_sis.detailregistersubject.idRegister and ");
			sql.append("db_sis.detailregistersubject.idSubject = db_sis.subject.idSubject and ");
			sql.append("db_sis.subject.idInstitute = db_sis.institute.idInstitute and db_sis.student.idStudent = ? and db_sis.registersubject.semester = ?");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(1, idStudent);
			pstatement.setString(2, semester);
			
			rs = pstatement.executeQuery();
			registersubjectfeeList = FXCollections.observableArrayList();
			while(rs.next()) {
				
				registersubjectfee = new RegisterSubjectFee(rs.getInt("idStudent"), rs.getInt("idSubject"), rs.getString("subjectCode"),
						rs.getString("name"), rs.getString("instituteName"), rs.getInt("creditSubject"), rs.getInt("creditTuition"),
						rs.getInt("stu_Institute"), rs.getInt("sub_Institute"), rs.getString("semester"));
				registersubjectfeeList.add(registersubjectfee);	
			}
		} finally {
			closeConnection(conn);
		}
		return registersubjectfeeList;
	}

}
