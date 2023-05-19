
package StudentInformationSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StudentInformationSystem.dao.InstituteDao;
import StudentInformationSystem.entity.Institute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * interact with institute table
 * tương tác với bảng khoa 
 */

public class InstituteDaoImpl extends BaseDaoImpl implements InstituteDao {
	
	/**
	 * Get list of institute from database
	 * @return list of institute
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<Institute> getListInstitute() 
			throws ClassNotFoundException, SQLException {
		
		ObservableList<Institute> listInstitute = FXCollections.observableArrayList();
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;		
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("select * from db_sis.institute");
			pstatement = conn.prepareStatement(sql.toString());
			rs = pstatement.executeQuery();
			
			while (rs.next()) {
				Institute institute = new Institute();
				institute.setIdInstitute(rs.getInt("idInstitute"));
				institute.setInstituteName(rs.getString("instituteName"));
				listInstitute.add(institute);
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			closeConnection(conn);
		}
		return listInstitute;
	}
	
	/**
	 * Get info of institute by name
	 * @param institute name of institute
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ObservableList<Institute> getInforInstitute(String institute) 
			throws SQLException, ClassNotFoundException{
        ObservableList<Institute> inforInstitute = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement pstatement = null;
        ResultSet rs = null;
        conn = getConnection();
        try {
        	conn = getConnection();
        	String sqlInfor = "select * FROM db_sis.institute WHERE instituteName = '"+institute+"'";   
        	pstatement = conn.prepareStatement(sqlInfor.toString());
        	rs = pstatement.executeQuery();
        	while (rs.next()) {
        		Institute instituteInfor = new Institute();
        		instituteInfor.setIdInstitute(rs.getInt("idInstitute"));
        		instituteInfor.setInstituteName(rs.getString("instituteName"));
        		instituteInfor.setPhoneSupport(rs.getString("phoneSupport"));
               	instituteInfor.setAddress(rs.getString("address"));
               	inforInstitute.add(instituteInfor);
        	}

        } catch (SQLException e) {
               System.out.println(e);
        } finally {
               closeConnection(conn);
        }
        
        return inforInstitute;
    }
	
	/**
	 * Insert institute into database
	 * @param nameInstitute name of institute
	 * @param phoneInstitute phone support number
	 * @param addressInstitute address of institute
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
    public void InsertInstitute(String nameInstitute, String phoneInstitute , String addressInstitute) 
    		throws ClassNotFoundException, SQLException {
    	Connection conn = null;
    	PreparedStatement pstatement = null;
    	StringBuilder sqlInsertInstitute = null;
        String lastID = null;
        int ID;
        ResultSet rs = null;
        try {
        	conn = getConnection();
        	String sqlGetLastID = "SELECT * FROM db_sis.institute ORDER BY idInstitute DESC LIMIT 1;";
        	pstatement = conn.prepareStatement(sqlGetLastID.toString());
        	rs = pstatement.executeQuery();
        	while (rs.next()) {				
        		lastID = rs.getString("idInstitute");        
        	}

        } catch (SQLException e) {
        	System.out.println(e);
        } finally {
        	closeConnection(conn);
        }
           ID =  Integer.parseInt(lastID) + 1;
        try {
        	conn = getConnection();
        	sqlInsertInstitute = new StringBuilder();
        	sqlInsertInstitute.append("INSERT INTO db_sis.institute VALUES (?, ?, ?, ?);");
        	pstatement = conn.prepareStatement(sqlInsertInstitute.toString());
            pstatement.setInt(1, ID);
            pstatement.setString(2, nameInstitute);
            pstatement.setString(3, phoneInstitute);
            pstatement.setString(4, addressInstitute);
            pstatement.executeUpdate();

        } catch (SQLException e) {
        	System.out.println(e);
        } finally {
        	closeConnection(conn);
        }
    }

}
