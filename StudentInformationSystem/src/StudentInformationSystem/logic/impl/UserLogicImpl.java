
package StudentInformationSystem.logic.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

import StudentInformationSystem.dao.UserDao;
import StudentInformationSystem.dao.impl.UserDaoImpl;
import StudentInformationSystem.entity.ManageInfo;
import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.logic.UserLogic;
import StudentInformationSystem.util.Common;
import javafx.collections.ObservableList;

/**
 * Handle user and admin info
 * 
 */
public class UserLogicImpl implements UserLogic {
	UserDao userDao = new UserDaoImpl();

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
	@Override
	public boolean checkUserLogin(String userName, String password)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();

		UserInfo userInfo = userDao.getAcount(userName);
		String salt = userInfo.getSalt();
		password = Common.encodeSHA1(password, salt);
		UserInfo tblUser = userDaoImpl.getUser(userName, password);

		if (tblUser != null) {
			return true;
		}
		return false;
	}

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
	@Override
	public boolean checkAdminLogin(String userName, String password)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		UserInfo userInfo = userDao.getAcount(userName);
		String salt = userInfo.getSalt();
		password = Common.encodeSHA1(password, salt);
		ManageInfo tblManage = userDaoImpl.getAdmin(userName, password);

		if (tblManage != null) {
			return true;
		}
		return false;
	}

	/**
	 * Check if old password is exist
	 * @param oldPassword
	 * @return true if success
	 */
	@Override
	public boolean checkOldPassword(String oldPassword, String username)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

		UserInfo userInfo = userDao.getAcount(username);
		String salt = userInfo.getSalt();
		String passwordFromDB = userInfo.getPassword();
		oldPassword = Common.encodeSHA1(oldPassword, salt);
		if (oldPassword.equals(passwordFromDB)) {
			return true;
		}
		return false;

	}

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
	@Override
	public boolean updatePassword(String newPassword, String username)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String salt = Common.getRandomString();
		newPassword = Common.encodeSHA1(newPassword, salt);
		return userDao.updatePassword(newPassword, salt, username);

	}

	/**
	 * Get user info by name
	 * @param userName
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public UserInfo getUserbyName(String userName) throws ClassNotFoundException, SQLException {
		return userDao.getUserbyName(userName);
	}
	
	/**
	 * Get user info by student id
	 * @param IDuser student us
	 * @return user info
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public UserInfo getInfoUserbyIDStudent(String IDuser) throws ClassNotFoundException, SQLException {
		return userDao.getUserbyIDStudent(IDuser);
	}

	/**
	 * Update user infor
	 * @param userInfo
	 * @return true if success
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public boolean updateInfomation(UserInfo userInfo) throws ClassNotFoundException, SQLException {
		return userDao.updateInfomation(userInfo);
	}

	/**
	 * get all user name
	 * @return list of username
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<String> getAllUserName() throws ClassNotFoundException, SQLException {
		return userDao.getAllUserName();
	}

	/**
	 * get list student
	 * @param userName MSSV
	 * @return list of student
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public ObservableList<UserInfo> getListUser(String userName) throws ClassNotFoundException, SQLException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		return userDaoImpl.getListUser(userName);
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
	public boolean insertUser(UserInfo userInfo) throws SQLException, ClassNotFoundException, ParseException,
			NoSuchAlgorithmException, UnsupportedEncodingException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		boolean successStudent = true;
		try {
			// insert vào bảng account lấy ra id vừa được tạo
			int idAccount = userDaoImpl.insertAccount(userInfo);
			userInfo.setIdAcount(idAccount);
			if (idAccount > 0) {
				// insert vào bảng Student
				successStudent = userDaoImpl.insertStudent(userInfo);
			}

		} catch (Exception e) {// bắt ngoại lệ
			System.out.println("Lỗi ở hàm createUser: " + e.getMessage());
			successStudent = false;// gán success = false
		}
		return successStudent;

	}

	/**
	 * Delete student from database
	 * @param idStudent 
	 * @return true if success
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public boolean blockUser(int idStudent) throws SQLException, ClassNotFoundException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		return userDaoImpl.blockAccount(idStudent);

	}

}
