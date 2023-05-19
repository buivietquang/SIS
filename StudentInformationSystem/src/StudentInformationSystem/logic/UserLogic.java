
package StudentInformationSystem.logic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

import StudentInformationSystem.entity.UserInfo;
import javafx.collections.ObservableList;

/**
 * Interface of UserLogicImpl
 * Giao diện của UserLogicImpl
 */
public interface UserLogic {

	/**
	 * Get user info by name
	 * Lấy thông tin sv bằng tên sv
	 * @param userName
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getUserbyName(String userName) throws ClassNotFoundException, SQLException;

	/**
	 * Get user info by student id
	 * Lấy thông tin bằng mã
	 * @param IDuser student us
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getInfoUserbyIDStudent(String IDuser) throws ClassNotFoundException, SQLException;

	/**
	 * Check student login
	 * @param userName
	 * @param password
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean checkUserLogin(String userName, String password)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * Check admin login
	 * @param userName
	 * @param password
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean checkAdminLogin(String userName, String password)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * Check if old password is exist
	 * Check mk cũ
	 * @param oldPassword
	 * @return true if success
	 */
	public boolean checkOldPassword(String oldPassword, String username)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * Update user infor
	 * @param userInfo
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateInfomation(UserInfo userInfo) throws ClassNotFoundException, SQLException;

	/**
	 * Change password
	 * @param newPassword
	 * @param username
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean updatePassword(String newPassword, String username)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * get all user name
	 * @return list of username
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<String> getAllUserName() throws ClassNotFoundException, SQLException;

	/**
	 * get list student
	 * @param userName MSSV
	 * @return list of student
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<UserInfo> getListUser(String userName) throws ClassNotFoundException, SQLException;

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
	public boolean insertUser(UserInfo userInfo) throws SQLException, ClassNotFoundException, ParseException,
			NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * Delete student from database
	 * @param idStudent 
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean blockUser(int idStudent) throws SQLException, ClassNotFoundException;
}
