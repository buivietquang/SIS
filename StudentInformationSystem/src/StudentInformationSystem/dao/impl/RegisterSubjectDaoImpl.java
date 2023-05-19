
package StudentInformationSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StudentInformationSystem.dao.RegisterSubjectDao;
import StudentInformationSystem.entity.RegisterSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Thao tác với dữ liệu bảng đăng ký học phần trong Database
 */
public class RegisterSubjectDaoImpl extends BaseDaoImpl implements RegisterSubjectDao{
	
	/**
	 * Get list of register subject by student id and semester
	 * @param idStudent MSSV
	 * @param semester 
	 * @return list of register subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<RegisterSubject> getRegisterSubject(int idStudent, String semester) 
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		RegisterSubject registerSubject = null;
		StringBuilder sql = null;
		ObservableList<RegisterSubject> rsList;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT subjectCode,name,instituteName ,creditSubject ");
			sql.append("FROM db_sis.registersubject, db_sis.detailregistersubject, db_sis.subject, db_sis.institute ");
			sql.append("WHERE db_sis.registersubject.idRegister = db_sis.detailregistersubject.idRegister ");
			sql.append("and db_sis.subject.idSubject = db_sis.detailregistersubject.idSubject ");
			sql.append("and db_sis.subject.idInstitute = db_sis.institute.idInstitute ");
			sql.append("and db_sis.registersubject.semester = ? ");
			sql.append("and db_sis.registersubject.idStudent = ?; ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, semester);
			pstatement.setInt(2, idStudent);
			
			rs = pstatement.executeQuery();
			rsList = FXCollections.observableArrayList();
			while(rs.next()) {
				
				registerSubject = new RegisterSubject(rs.getString("subjectCode"), rs.getString("name"), 
						rs.getString("instituteName"), "Thành Công", rs.getInt("creditSubject"));

				rsList.add(registerSubject);
			}
		} finally {
			closeConnection(conn);
		}
		return rsList;
	}
	
	/**
	 * Search info of subject by subjectCode
	 * @param subjectCode Mã học phần
	 * @return Học phần
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public RegisterSubject searchBeforeAddSubject(String subjectCode) 
			throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		RegisterSubject registerSubject = null;
		StringBuilder sql = null;
		
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT subjectCode, name, creditSubject,instituteName ");
			sql.append("FROM db_sis.subject, db_sis.institute ");
			sql.append("WHERE subjectCode = ? ");
			sql.append("and db_sis.subject.idInstitute = db_sis.institute.idInstitute");

			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, subjectCode);
			
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				
				registerSubject = new RegisterSubject(rs.getString("subjectCode"), rs.getString("name"), 
						rs.getString("instituteName"), "", rs.getInt("creditSubject"));

			}
		} finally {
			closeConnection(conn);
		}
		
		return registerSubject;
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
	@Override
	public ObservableList<String> checkRequiredSubject(String subjectCode, int idStudent) 
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		ObservableList<String> list = FXCollections.observableArrayList(); 
		
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT subjectCode FROM db_sis.subject ");
			sql.append("WHERE db_sis.subject.idSubject IN  ");
			sql.append("(SELECT idRequiredSubject FROM db_sis.requiredsubject,db_sis.subject ");
			sql.append("WHERE db_sis.requiredsubject.idSubject = db_sis.subject.idSubject ");
			sql.append("AND db_sis.subject.subjectCode = ? ");
			sql.append("AND db_sis.requiredsubject.idRequiredSubject NOT IN ");
			sql.append("(SELECT idRequiredSubject FROM db_sis.requiredsubject,db_sis.detailregistersubject,db_sis.registersubject,db_sis.subject ");
			sql.append("WHERE db_sis.requiredsubject.idRequiredSubject = db_sis.detailregistersubject.idSubject ");
			sql.append("AND db_sis.requiredsubject.idSubject = db_sis.subject.idSubject ");
			sql.append("AND db_sis.detailregistersubject.idRegister =  db_sis.registersubject.idRegister ");
			sql.append("AND db_sis.registersubject.idStudent = ? ");
			sql.append("AND db_sis.subject.subjectCode = ? ");
			sql.append("AND db_sis.detailregistersubject.middleGrade > 3.0 ");
			sql.append("AND db_sis.detailregistersubject.finalGrade > 3.0 )) ");
			
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, subjectCode);
			pstatement.setInt(2, idStudent);
			pstatement.setString(3, subjectCode);
			
			rs = pstatement.executeQuery();
			while(rs.next()) {
				list.add(new String(rs.getString("subjectCode")));
			}
		} finally {
			closeConnection(conn);
		}
		
		return list;
	}
	
	/**
	 * Get ID register of this student in database 
	 * @param idStudent ID sinh viên
	 * @return ID đăng ký
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public int getIdRegister(int idStudent) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		int idRegister = 0;
		
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT idRegister FROM db_sis.registersubject ");
			sql.append("WHERE db_sis.registersubject.idStudent = ? ");
			sql.append("AND db_sis.registersubject.semester = 20232");
			
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(1, idStudent);
			
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				idRegister = rs.getInt("idRegister");
			}
		} finally {
			closeConnection(conn);
		}
		
		return idRegister;
	}
	
	/**
	 * Get ID register by subject code
	 * @param subjectCode 
	 * @return ID subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public int getIdSubject(String subjectCode) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		int idSubject = 0;
		
		try {
			conn = getConnection();
			sql = new StringBuilder();			 
			sql.append("SELECT idSubject FROM db_sis.subject  ");
			sql.append("WHERE subjectCode = ? ");
			
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, subjectCode);
			
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				idSubject = rs.getInt("idSubject");
			}
		} finally {
			closeConnection(conn);
		}
		return idSubject;
	}
	
	/**
	 * Insert subject into register subject table in database
	 * @param idStudent 
	 * @param subjectCode 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean insertSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException {
		int idRegister = getIdRegister(idStudent);
		int idSubject  = getIdSubject(subjectCode);
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;
				
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("INSERT INTO db_sis.detailregistersubject  ");
			sql.append("(`idRegister`, `idSubject`) VALUES (?, ?); ");
			
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(1, idRegister);
			pstatement.setInt(2, idSubject);
			int success = pstatement.executeUpdate();

			if(success == 1) return true;
			else return false;
			
			
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * Delete subject from register subject table in database
	 * @param idStudent
	 * @param subjectCode
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean deleteSubject(int idStudent, String subjectCode) 
			throws ClassNotFoundException, SQLException {
		int idRegister = getIdRegister(idStudent);
		int idSubject  = getIdSubject(subjectCode);
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;
		 
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("DELETE FROM db_sis.detailregistersubject ");
			sql.append("WHERE idSubject = ? and idRegister = ?; ");
			
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(1, idSubject);
			pstatement.setInt(2, idRegister);
			int success = pstatement.executeUpdate();

			if(success == 1) return true;
			else return false;
			
		} finally {
			closeConnection(conn);
		}
	}
}
