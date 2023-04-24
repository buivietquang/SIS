
package StudentInformationSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StudentInformationSystem.dao.GradeDao;
import StudentInformationSystem.entity.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Thao tác với dữ liệu về kết quả học phần trong Database
 */
public class GradeDaoImpl extends BaseDaoImpl implements GradeDao{

	/**
	 * Get student course mark by MSSV 
	 * @param username MSSV
	 * @return list of mark
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<Grade> getGrade(String username) 
			throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		Grade grade = null;
		StringBuilder sql = null;
		ObservableList<Grade> gradeList;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT * FROM db_sis.grade_view ");
			sql.append("WHERE username = ?");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, username);			
			
			rs = pstatement.executeQuery();
			gradeList = FXCollections.observableArrayList();
			while(rs.next()) {
				float weight = rs.getFloat("weight");
				float middleGrade = rs.getFloat("middleGrade");
				float finalGrade = rs.getFloat("finalGrade");
				String letterGrade = setLetterGrade(weight, middleGrade, finalGrade);
				
				grade = new Grade(rs.getString("semester"), rs.getString("subjectCode"), rs.getString("name"),
						rs.getInt("creditSubject"), middleGrade, finalGrade, letterGrade);
				
				if(grade.getMiddleGrade() == 0 && grade.getFinalGrade() == 0);
				else gradeList.add(grade);
					
			}
		} finally {
			closeConnection(conn);
		}
		return gradeList;
	}

	/**
	 * Get list of semester which this student learn
	 * @param username MSSV
	 * @return list of semester
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<String> getSemester(String username) throws ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		ObservableList<String> semesterList = FXCollections.observableArrayList();
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT distinct semester FROM db_sis.grade_view ");
			sql.append("WHERE username = ?");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, username);	
			rs = pstatement.executeQuery();
			while(rs.next()) {
				String semester = rs.getString("semester");				
				if(semester.equals("20232"));
				else semesterList.add(semester);
			}
		} finally {
			closeConnection(conn);
		}
		return semesterList;
	}
	
	/**
	 * Display lettergrade with 3 params 
	 * @param d weight
	 * @param e middle grade
	 * @param f final grade
	 * @return letter grade
	 */
	public String setLetterGrade(double d, double e, double f) {
		double grade = e*(1-d) + f*d; 
		
		if(e < 3.0 || f < 3.0) return "F";
		else if(grade >= 9.5) return "A+";
		else if(grade >= 8.5) return "A";
		else if(grade >= 8.0) return "B+";
		else if(grade >= 7.0) return "B";
		else if(grade >= 6.5) return "C+";
		else if(grade >= 5.5) return "C";
		else if(grade >= 5.0) return "D+";
		else if(grade >= 4.0) return "D";
		else return "F";
 	}
	
	/**
	 * Update student mark
	 * @param username MSSV
	 * @param grade information about this subject
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateGrade(String username, Grade grade) 
			throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstatement = null;
		StringBuilder sql = null;

		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("update grade_view set middleGrade = ? ,finalGrade = ? ");
			sql.append("where username = ? and semester = ? and subjectCode = ? ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setDouble(1, grade.getMiddleGrade());
			pstatement.setDouble(2, grade.getFinalGrade());
			pstatement.setString(3, username);
			pstatement.setString(4, grade.getSemester());
			pstatement.setString(5, grade.getSubjectCode());
			
			int success = pstatement.executeUpdate();

			if(success == 1) return true;
			else return false;

		} finally {
			closeConnection(conn);
		}
	}
}
