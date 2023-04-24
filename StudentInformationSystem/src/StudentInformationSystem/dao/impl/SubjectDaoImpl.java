
package StudentInformationSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StudentInformationSystem.dao.SubjectDao;
import StudentInformationSystem.entity.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Thao tác dữ liệu với bảng học phần trong Database
 *
 */
public class SubjectDaoImpl extends BaseDaoImpl implements SubjectDao {

	/**
	 * Get list of subject by subject code, subject name and institute
	 * @param subjectCode
	 * @param subjectName name of subject
	 * @param institute
	 * @return list of subject
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<Subject> getListSubject(String subjectCode, String subjectName, 
			String institute) throws ClassNotFoundException, SQLException{
		ObservableList<Subject> listSubject = FXCollections.observableArrayList();
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;	
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT * FROM db_sis.subject_view WHERE 1=1 ");
			if(!"".equals(institute) && "".equals(subjectCode) && "".equals(subjectName)) {
				sql.append("AND instituteName = '"+institute+"'");
			}
			else if("".equals(institute) && !"".equals(subjectCode) && !"".equals(subjectName)){
				sql.append("AND subjectCode LIKE '%"+subjectCode+"%' OR name LIKE '%"+subjectName+"%'");
			}
			else if(!"".equals(institute) && !"".equals(subjectCode) && !"".equals(subjectName)){
				sql.append("AND instituteName = '"+institute+"' AND (subjectCode LIKE '%"+subjectCode+"%' OR name LIKE '%"+subjectName+"%')");
			}

			pstatement = conn.prepareStatement(sql.toString());
			rs = pstatement.executeQuery();
			
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setIdSubject(rs.getInt("idSubject"));
				subject.setSubjectCode(rs.getString("subjectCode"));
				subject.setSubjectName(rs.getString("name"));
				subject.setCreditSubject(rs.getInt("creditSubject"));
				subject.setCreditTuition(rs.getInt("creditTuition"));
				subject.setInstitute(rs.getString("instituteName"));
				subject.setWeight(rs.getFloat("weight"));
				listSubject.add(subject);
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			closeConnection(conn);
		}
		return listSubject;
	}
	
	/**
	 * Update info of subject
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean updateSubject(Subject subject) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			
			sql.append("UPDATE db_sis.subject_view set subjectCode = ?, name = ?, ");
			sql.append("creditSubject = ?, creditTuition = ?, ");
			sql.append("weight = ? WHERE idSubject = ?");

			pstatement = conn.prepareStatement(sql.toString());
						
			pstatement.setString(1, subject.getSubjectCode());
			pstatement.setString(2, subject.getSubjectName());
			pstatement.setInt(3, subject.getCreditSubject());
			pstatement.setInt(4, subject.getCreditTuition());
//			pstatement.setString(5, institute);
			pstatement.setDouble(5, subject.getWeight());
			pstatement.setInt(6, subject.getIdSubject());
			
			int success = pstatement.executeUpdate();			
			if(success == 1) return true;
			else return false;
			
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
	}
	
	/**
	 * Check if this subject is exist by subject code and subject name
	 * @param subjectCode
	 * @param subjectName name of subject
	 * @return true if exist
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public boolean checkExistSubject(String subjectCode, String subjectName) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		int count = 0;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			
			sql.append("SELECT count(*) as COUNT from subject ");
			sql.append("WHERE subjectCode = ? or name = ? ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, subjectCode);
			pstatement.setString(2, subjectName);
			rs = pstatement.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			closeConnection(conn);
		}
		
		if(count > 0) return true;
		else return false;
	}

	/**
	 * Insert new subject into database
	 * @param subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean insertSubject(Subject subject, int institute) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;

		try {
			conn = getConnection();
			sql = new StringBuilder();		
			sql.append("INSERT INTO db_sis.subject ");
			sql.append("(subjectCode, name, creditSubject, creditTuition, idInstitute, weight) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?)");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, subject.getSubjectCode());
			pstatement.setString(2, subject.getSubjectName());
			pstatement.setInt(3, subject.getCreditSubject());
			pstatement.setInt(4, subject.getCreditTuition());
			pstatement.setInt(5, institute);
			pstatement.setDouble(6, subject.getWeight());
			
			int success = pstatement.executeUpdate();
			
			if(success == 1) return true;
			else return false;
		} catch (SQLException e) {
			System.out.println(e);
			return false;		
		} finally {
			closeConnection(conn);
		}
	}
	
	/**
	 * Set required subject
	 * @param subjectCode
	 * @param subjectRequiredList list of required subject
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public void setRequiredSubject(String subjectCode, ObservableList<String> subjectRequiredList) 
			throws SQLException, ClassNotFoundException {
		
		subjectRequiredList.forEach(subjectRequired -> {
			try {
				Connection conn = null;
				PreparedStatement pstatement = null;
				StringBuilder sql = null;
				conn = getConnection();
				sql = new StringBuilder();		
				sql.append("INSERT INTO db_sis.requiredsubject (idSubject, idRequiredSubject) ");
				sql.append("VALUES ((select idSubject from subject where subjectCode = ?)");
				sql.append(", (select idSubject from subject where subjectCode = ?)) ");
				pstatement = conn.prepareStatement(sql.toString());
				
				pstatement.setString(1, subjectCode);
				pstatement.setString(2, subjectRequired);
				pstatement.executeUpdate();
				
			} catch (SQLException e) {
		
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});			
		return ;
	}
	
}
