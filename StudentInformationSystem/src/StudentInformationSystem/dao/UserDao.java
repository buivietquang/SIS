
package StudentInformationSystem.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

import StudentInformationSystem.entity.ManageInfo;
import StudentInformationSystem.entity.UserInfo;
import javafx.collections.ObservableList;

/**
 * Interface of UserDaoImpl
 */
public interface UserDao {

	/**
	 * Get user info by name
	 * @param userName
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getUserbyName(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * Get user info by student id
	 * @param IDuser student us
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getUserbyIDStudent(String IDuser) throws SQLException, ClassNotFoundException;

	/**
	 * Get user info by username and password
	 * @param userName
	 * @param password
	 * @return user info
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserInfo getUser(String userName, String password) throws SQLException, ClassNotFoundException;

	/**
	 * Get admin info by username and password
	 * @param userName
	 * @param password
	 * @return admin info
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ManageInfo getAdmin(String userName, String password) throws SQLException, ClassNotFoundException;

	/**
	 * Get both student and admin info by username
	 * @param username
	 * @return info of both student and admin
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getAcount(String username) throws ClassNotFoundException, SQLException;

	/**
	 * Update password by username, salt, password
	 * @param password
	 * @param salt to encrypt
	 * @param username
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean updatePassword(String password, String salt, String username)
			throws SQLException, ClassNotFoundException;

	/**
	 * Update user info
	 * @param userInfo
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateInfomation(UserInfo userInfo) throws ClassNotFoundException, SQLException;

	/**
	 * get all user name
	 * @return list of username
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<String> getAllUserName() throws SQLException, ClassNotFoundException;

	/**
	 * get admin by name
	 * @param userName 
	 * @return list of student
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ManageInfo getAdminbyName(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * get list of all user
	 * @param userName
	 * @return list of user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<UserInfo> getListUser(String userName) throws SQLException, ClassNotFoundException;

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
	public int insertAccount(UserInfo userInfo)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException;

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
	public boolean insertStudent(UserInfo userInfo) throws SQLException, ClassNotFoundException, ParseException;

	

	/**
	 * Delete both student and admin from database
	 * @param idAccount
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean blockAccount(int idAccount) throws SQLException, ClassNotFoundException;

	/**
	 * Get ID in database by username
	 * @param userName
	 * @return id user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdByUserName(String userName) throws ClassNotFoundException, SQLException;
}
