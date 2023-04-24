
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
 * Giao diện của UserDaoImpl
 */
public interface UserDao {

	/**
	 * Get user info by name
	 * Lấy thông tin = tên sv
	 * @param userName
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getUserbyName(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * Get user info by student id
	 * Lấy thông tin = mã sv
	 * @param IDuser student us
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getUserbyIDStudent(String IDuser) throws SQLException, ClassNotFoundException;

	/**
	 * Get user info by username and password
	 * Nhận thông tin theo tên và mật khẩu
	 * @param userName
	 * @param password
	 * @return user info
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserInfo getUser(String userName, String password) throws SQLException, ClassNotFoundException;

	/**
	 * Get admin info by username and password
	 * Nhận thông tin admin theo tên và mật khẩu
	 * @param userName
	 * @param password
	 * @return admin info
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ManageInfo getAdmin(String userName, String password) throws SQLException, ClassNotFoundException;

	/**
	 * Get both student and admin info by username
	 * Nhận cả thông tin sinh viên và admin theo tên 
	 * @param username
	 * @return info of both student and admin
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserInfo getAcount(String username) throws ClassNotFoundException, SQLException;

	/**
	 * Update password by username, salt, password
	 * Cập nhật mk qua username salt, pass
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
	 * Cập nhật thông tin người dùng
	 * @param userInfo
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean updateInfomation(UserInfo userInfo) throws ClassNotFoundException, SQLException;

	/**
	 * get all user name
	 * lấy tên user
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
	 * danh sách tất cả user
	 * @param userName
	 * @return list of user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<UserInfo> getListUser(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * Insert both student and admin into database
	 * Thêm cả sv và admin vào db
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
	 * thếm sv vào db
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
	 * xóa cả admin vs sv khỏi db
	 * @param idAccount
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean blockAccount(int idAccount) throws SQLException, ClassNotFoundException;

	/**
	 * Get ID in database by username
	 * Nhận ID trong db theo tên 
	 * @param userName
	 * @return id user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getIdByUserName(String userName) throws ClassNotFoundException, SQLException;
}
