
package StudentInformationSystem.dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// import com.mysql.jdbc.Statement;

import StudentInformationSystem.dao.UserDao;
import StudentInformationSystem.entity.ManageInfo;
import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.util.Common;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Interact with account, student, manager table in database
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/**
	 * Get user info by username and password
	 * @param userName
	 * @param password
	 * @return user info
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public UserInfo getUser(String userName, String password) throws SQLException, ClassNotFoundException {
		UserInfo tblUser = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("select * ");
			sql.append("from db_sis.student std inner join db_sis.account acc on std.idStudent = acc.idAccount ");
			sql.append("inner join db_sis.institute on std.idInstitute = db_sis.institute.idInstitute ");
			sql.append("where username = ? and password = ? and acc.idPermission = 1");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, userName);
			pstatement.setString(2, password);
			rs = pstatement.executeQuery();
			while (rs.next()) {
				tblUser = new UserInfo(rs.getInt("idAccount"), rs.getInt("idPermission"), rs.getString("username"),
						rs.getString("password"), "null", rs.getString("salt"), rs.getInt("idStudent"),
						rs.getString("fullname"), rs.getString("address"), rs.getString("phonenumber"),
						rs.getString("dateofbirth"), rs.getString("email"), rs.getString("position"),
						rs.getString("gender"), rs.getString("class"), rs.getString("course"), rs.getString("majors"),
						rs.getString("identityCardNumber"), rs.getString("dateIssue"), rs.getString("issuePlace"),
						rs.getInt("idInstitute"), rs.getString("instituteName"));
			}
		} catch (SQLException e) {
			System.out.println("error in  getUser " + e.getMessage());
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			System.out.println("error in  getUser  " + e.getMessage());
			throw new ClassNotFoundException();
		} finally {
			closeConnection(conn);
		}
		return tblUser;
	}

	/**
	 * Get admin info by username and password
	 * @param userName
	 * @param password
	 * @return admin info
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ManageInfo getAdmin(String userName, String password) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		ManageInfo tblUser = null;
		StringBuilder sql = null;

		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT * FROM db_sis.account, db_sis.manager ");
			sql.append(
					"WHERE db_sis.account.idPermission = 2 AND db_sis.account.username = ? AND db_sis.account.password = ?");

			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, userName);
			pstatement.setString(2, password);
			rs = pstatement.executeQuery();

			while (rs.next()) {
				tblUser = new ManageInfo(rs.getInt("idAccount"), rs.getInt("idPermission"), rs.getString("username"),
						rs.getString("password"), "null", rs.getString("salt"), rs.getInt("idManager"),
						rs.getString("fullname"), rs.getString("address"), rs.getString("phonenumber"),
						rs.getString("dateofbirth"), rs.getString("email"), rs.getString("position"));
			}

		} catch (SQLException e) {
			System.out.println("error in  getAdmin " + e.getMessage());
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			System.out.println("error in  getAdmin  " + e.getMessage());
			throw new ClassNotFoundException();
		} finally {
			closeConnection(conn);
		}

		return tblUser;
	}

	/**
	 * Get user info by name
	 * @param userName
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public UserInfo getUserbyName(String userName) throws SQLException, ClassNotFoundException {
		UserInfo tblUser = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("select *  ");
			sql.append(
					"from db_sis.student inner join db_sis.account on db_sis.account.idAccount = db_sis.student.idStudent ");
			sql.append("inner join db_sis.institute on db_sis.student.idInstitute = db_sis.institute.idInstitute  ");
			sql.append("where username = ? ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, userName);
			rs = pstatement.executeQuery();
			while (rs.next()) {
				tblUser = new UserInfo(rs.getInt("idAccount"), rs.getInt("idPermission"), rs.getString("username"),
						rs.getString("password"), "null", rs.getString("salt"), rs.getInt("idStudent"),
						rs.getString("fullname"), rs.getString("address"), rs.getString("phonenumber"),
						rs.getString("dateofbirth"), rs.getString("email"), rs.getString("position"),
						rs.getString("gender"), rs.getString("class"), rs.getString("course"), rs.getString("majors"),
						rs.getString("identityCardNumber"), rs.getString("dateIssue"), rs.getString("issuePlace"),
						rs.getInt("idInstitute"), rs.getString("instituteName"));
			}
		} catch (SQLException e) {
			System.out.println("error in  getUserbyName " + e.getMessage());
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			System.out.println("error in  getUserbyName: " + e.getMessage());
			throw new ClassNotFoundException();
		} finally {
			closeConnection(conn);
		}
		return tblUser;
	}

	/**
	 * get admin by name
	 * @param userName 
	 * @return list of student
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ManageInfo getAdminbyName(String userName) throws SQLException, ClassNotFoundException {
		ManageInfo tblManage = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("select *  ");
			sql.append(
					"from db_sis.manager inner join db_sis.account on db_sis.account.idAccount = db_sis.manager.idManager ");
			sql.append("where username = ? ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, userName);
			rs = pstatement.executeQuery();
			while (rs.next()) {
				tblManage = new ManageInfo(rs.getInt("idAccount"), rs.getInt("idPermission"), rs.getString("username"),
						rs.getString("password"), "null", rs.getString("salt"), 0, rs.getString("fullname"),
						rs.getString("address"), rs.getString("phonenumber"), rs.getString("dateofbirth"),
						rs.getString("email"), rs.getString("position"));
			}
		} catch (SQLException e) {
			System.out.println("error in  getUserbyName " + e.getMessage());
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			System.out.println("error in  getUserbyName: " + e.getMessage());
			throw new ClassNotFoundException();
		} finally {
			closeConnection(conn);
		}
		return tblManage;
	}

	/**
	 * Get user info by student id
	 * @param IDuser student us
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public UserInfo getUserbyIDStudent(String IDuser) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		UserInfo tblUser = null;
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("select * from db_sis.account, db_sis.student, db_sis.institute ");
			sql.append("where idstudent = ? and db_sis.account.idAccount = db_sis.student.idStudent ");
			sql.append("and db_sis.student.idInstitute = db_sis.institute.idInstitute ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, IDuser);
			rs = pstatement.executeQuery();
			while (rs.next()) {
				tblUser = new UserInfo(rs.getInt("idAccount"), rs.getInt("idPermission"), rs.getString("username"),
						rs.getString("password"), "null", rs.getString("salt"), rs.getInt("idStudent"),
						rs.getString("fullname"), rs.getString("address"), rs.getString("phonenumber"),
						rs.getString("dateofbirth"), rs.getString("email"), rs.getString("position"),
						rs.getString("gender"), rs.getString("class"), rs.getString("course"), rs.getString("majors"),
						rs.getString("identityCardNumber"), rs.getString("dateIssue"), rs.getString("issuePlace"),
						rs.getInt("idInstitute"), rs.getString("instituteName"));
			}
		} finally {
			closeConnection(conn);
		}
		return tblUser;
	}

	/**
	 * get all user name
	 * @return list of username
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<String> getAllUserName() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		ObservableList<String> list = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("SELECT username FROM db_sis.account ");
			sql.append("WHERE idPermission = 1");
			pstatement = conn.prepareStatement(sql.toString());

			rs = pstatement.executeQuery();
			list = FXCollections.observableArrayList();
			while (rs.next()) {
				list.add(rs.getString("username"));
			}
			rs = pstatement.executeQuery();
		} finally {
			closeConnection(conn);
		}
		return list;
	}

	/**
	 * Get both student and admin info by username
	 * @param username
	 * @return info of both student and admin
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public UserInfo getAcount(String username) throws ClassNotFoundException, SQLException {

		UserInfo userInfo = new UserInfo();
		try {
			conn = getConnection();
			
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("select a.salt,a.password ");
				sql.append(" from account a where a.username = ?;");
				pstatement = conn.prepareStatement(sql.toString());
				
				pstatement.setString(1, username);
				
				rs = pstatement.executeQuery();
				while (rs.next()) {
					userInfo.setSalt(rs.getString("salt"));
					userInfo.setPassword(rs.getString("password"));
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error in  getAcount: " + e.getMessage());
			throw new ClassNotFoundException();
		} catch (SQLException e) {
			System.out.println("error in  getAcount: " + e.getMessage());
			throw new SQLException();
		} finally {
			closeConnection(conn);
		}
		return userInfo;

	}
	
	/**
	 * Get ID in database by username
	 * @param userName
	 * @return id user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public int getIdByUserName(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		StringBuilder sql = null;
		int MSSV = 0;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("select idaccount from db_sis.account ");
			sql.append("where db_sis.account.username = ?");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, userName);
			rs = pstatement.executeQuery();
			while (rs.next())
				MSSV = rs.getInt("idaccount");

		} finally {
			closeConnection(conn);
		}
		return MSSV;
	}

	/**
	 * Update password by username, salt, password
	 * @param password
	 * @param salt to encrypt
	 * @param username
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public boolean updatePassword(String password, String salt, String username)
			throws SQLException, ClassNotFoundException {
		try {
			conn = getConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append(" update account set password = ?, salt = ? ");
				sql.append(" where username = ? ;");
				int i = 1;
				pstatement = conn.prepareStatement(sql.toString());
				pstatement.setString(i++, password);
				pstatement.setString(i++, salt);
				pstatement.setString(i++, username);
				int isSuccess = pstatement.executeUpdate();
				if (isSuccess > 0)
					return true;
			}
		} catch (SQLException e) {
			System.out.println("error in  updatePassword: " + e.getMessage());
			throw new SQLException();
		} finally {
			closeConnection(conn);
		}
		return false;
	}

	/**
	 * Update user info
	 * @param userInfo
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateInfomation(UserInfo userInfo) throws ClassNotFoundException, SQLException {
		try {
			conn = getConnection();
			if (conn != null) {
				StringBuilder sql = new StringBuilder();
				sql.append(
						" update student set fullname = ?, address = ?, phonenumber = ?, dateofbirth = ?, email = ?,");
				sql.append(" gender = ?, class = ?, course = ?, majors = ?, identityCardNumber = ?,");
				sql.append(" dateIssue = ?, issuePlace = ? ");
				sql.append(" where idStudent = ? ;");
				int i = 1;
				pstatement = conn.prepareStatement(sql.toString());
				pstatement.setString(i++, userInfo.getFullname());
				pstatement.setString(i++, userInfo.getAddress());
				pstatement.setString(i++, userInfo.getPhonenumber());
				pstatement.setString(i++, userInfo.getDateOfBirth());
				pstatement.setString(i++, userInfo.getEmail());
				pstatement.setString(i++, userInfo.getGender());
				pstatement.setString(i++, userInfo.getClassUser());
				pstatement.setString(i++, userInfo.getCourse());
				pstatement.setString(i++, userInfo.getMajors());
				pstatement.setString(i++, userInfo.getIdentityCardNumber());
				pstatement.setString(i++, userInfo.getDateIssue());
				pstatement.setString(i++, userInfo.getIssuePlace());
				pstatement.setInt(i++, userInfo.getIdStudent());
				int isSuccess = pstatement.executeUpdate();
				;
				if (isSuccess > 0)
					return true;
			}
		} catch (SQLException e) {
			System.out.println("error in  updateInfomation: " + e.getMessage());
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			System.out.println("error in  updateInfomation: " + e.getMessage());
			throw new ClassNotFoundException();
		} finally {
			closeConnection(conn);
		}
		return false;
	}

	/**
	 * get list of all user
	 * @param userName
	 * @return list of user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<UserInfo> getListUser(String userName) throws SQLException, ClassNotFoundException {
		UserInfo tblUser = null;
		ObservableList<UserInfo> listUser = FXCollections.observableArrayList();
		StringBuilder sql = null;
		try {
			conn = getConnection();
			sql = new StringBuilder();
			sql.append("select *  ");
			sql.append(
					"from db_sis.student inner join db_sis.account on db_sis.account.idAccount = db_sis.student.idStudent ");
			sql.append("inner join db_sis.institute on db_sis.student.idInstitute = db_sis.institute.idInstitute  ");
			sql.append("where db_sis.account.idPermission = 1 and username like ? ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setString(1, "%" + userName + "%");
			rs = pstatement.executeQuery();
			while (rs.next()) {
				tblUser = new UserInfo(rs.getInt("idAccount"), rs.getInt("idPermission"), rs.getString("username"),
						rs.getString("password"), "null", rs.getString("salt"), rs.getInt("idStudent"),
						rs.getString("fullname"), rs.getString("address"), rs.getString("phonenumber"),
						rs.getString("dateofbirth"), rs.getString("email"), rs.getString("position"),
						rs.getString("gender"), rs.getString("class"), rs.getString("course"), rs.getString("majors"),
						rs.getString("identityCardNumber"), rs.getString("dateIssue"), rs.getString("issuePlace"),
						rs.getInt("idInstitute"), rs.getString("instituteName"));

				listUser.add(tblUser);
			}
		} catch (SQLException e) {
			System.out.println("error in  getListUser " + e.getMessage());
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			System.out.println("error in  getListUser: " + e.getMessage());
			throw new ClassNotFoundException();
		} finally {
			closeConnection(conn);
		}
		return listUser;
	}

	/**
	 * Insert both student and admin into database
	 * @param userInfo
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public int insertAccount(UserInfo userInfo)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
		int idAccount = -1;
		int i = 1;

		String salt = Common.getRandomString();
		String newPassword = Common.encodeSHA1(userInfo.getPassword(), salt);

		try {
			conn = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into account(idPermission, username, password,salt) ");
			sql.append(" values (?, ?, ?, ?); ");

			pstatement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstatement.setInt(i++, 1);
			pstatement.setString(i++, userInfo.getUsername());
			pstatement.setString(i++, newPassword);
			pstatement.setString(i++, salt);
			pstatement.executeUpdate();
			rs = pstatement.getGeneratedKeys();
			if (rs.next()) {
				idAccount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("error in  insertAccount: " + e.getMessage());
			throw new SQLException();
		} finally {
			closeConnection(conn);
		}
		return idAccount;
	}

	/**
	 * Insert student into database
	 * @param userInfo
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public boolean insertStudent(UserInfo userInfo) throws SQLException, ClassNotFoundException, ParseException {
		String birthDayStr = userInfo.getDateOfBirth();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date1 = sdf1.parse(birthDayStr);
		java.sql.Date birthDay = new java.sql.Date(date1.getTime());
		try {
			conn = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"insert into student(idStudent, fullname, address, phonenumber, dateofbirth, email, position, gender, ");
			sql.append("class, course, majors, identityCardNumber, dateIssue, issuePlace, idInstitute) ");
			sql.append(" values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?);");
			int i = 1;
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(i++, userInfo.getIdAcount());
			pstatement.setString(i++, userInfo.getFullname());
			pstatement.setString(i++, userInfo.getAddress());
			pstatement.setString(i++, userInfo.getPhonenumber());
			pstatement.setDate(i++, birthDay);
			pstatement.setString(i++, userInfo.getEmail());
			pstatement.setString(i++, userInfo.getPosition());
			pstatement.setString(i++, userInfo.getGender());
			pstatement.setString(i++, userInfo.getClassUser());
			pstatement.setString(i++, userInfo.getCourse());
			pstatement.setString(i++, userInfo.getMajors());
			pstatement.setString(i++, userInfo.getIdentityCardNumber());
			pstatement.setString(i++, userInfo.getDateIssue());
			pstatement.setString(i++, userInfo.getIssuePlace());
			pstatement.setInt(i++, userInfo.getIdInstitute());

			int isSuccess = pstatement.executeUpdate();
			if (isSuccess > 0)
				return true; 

		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closeConnection(conn);
		}
		return false;
	}

	
	/**
	 * Delete both student and admin from database
	 * @param idAccount
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public boolean blockAccount(int idAccount) throws SQLException, ClassNotFoundException {
		try {
			conn = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" update account set idPermission = 3 where idAccount = ? and idPermission = 1; ");
			pstatement = conn.prepareStatement(sql.toString());
			pstatement.setInt(1, idAccount);// set userId
			int isSuccess = pstatement.executeUpdate();
			if (isSuccess > 0)
				return true;
		} catch (SQLException e) {
			System.out.println("error in  blockAccount: " + e.getMessage());
			throw new SQLException();
		} finally {
			closeConnection(conn);
		}
		return false;
	}

}
