
package StudentInformationSystem.validate;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.util.Common;
import StudentInformationSystem.util.Constants;

/**
 * check student information
 * check thông tin sinh viên
 *
 */

public class ValidateUser {

	/**
	 * Check input value when login
	 * 
	 * @param usernameName
	 * @param password
	 *            password
	 * @param isAdmin
	 *            is admin or not
	 * @return returns true if successful
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean validateUserLogin(String userName, String password, boolean isAdmin)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String errStr = "";
		// check userName is empty
		errStr = checkEmpty(userName, Constants.ER001, Constants.USERNAME);
		if (errStr.length() > 0) {
			return false;
		}
		// check userName is empty
		errStr = checkEmpty(password, Constants.ER001, Constants.PASSWORD);
		if (errStr.length() > 0) {
			return false;
		}

		if (isAdmin) {
			UserLogicImpl userLogicImpl = new UserLogicImpl();
			return userLogicImpl.checkAdminLogin(userName, password);
		} else {
			UserLogicImpl userLogicImpl = new UserLogicImpl();
			return userLogicImpl.checkUserLogin(userName, password);
		}

	}

	/**
	 * Check input value when changing password
	 *
	 * @param oldPassword
	 *            old password
	 * @param newPassword
	 *            A new password
	 * @param confirmPassword
	 *            Confirm new password
	 * @param username
	 *            user name
	 * @return returns a string of characters indicating status
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String validateChangePassworrd(String oldPassword, String newPassword, String confirmPassword,
			String username)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String errStr = "";
		// check format password
		errStr = Common.checkFormat(newPassword, Constants.ER009, Constants.NEW_PASS, Constants.FORMAT_PASSWORD);
		if (errStr.length() > 0) {
			return errStr;
		}
		// check confirm password
		if (!newPassword.equals(confirmPassword)) {
			errStr = Common.getErr(Constants.ER002);
			return errStr;
		}
		// check password does exist in the DB
		UserLogic userLogic = new UserLogicImpl();
		if (!userLogic.checkOldPassword(oldPassword, username)) {
			return Constants.PASS + Common.getErr(Constants.ER009);
		}
		return "";
	}

	/**
	 * Check user information
	 *
	 * @param userInfo
	 *            user information
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String validateUserInfo(UserInfo userInfo) throws ClassNotFoundException, SQLException {
		String errStr = "";

		// check birthday
		String birthDay = userInfo.getDateOfBirth();
		if (birthDay.indexOf("null") != -1) {
			errStr = Constants.BIRTHDAY + " " + Common.getErr(Constants.ER001);
			return errStr;
		}
		// check email is empty
		String email = userInfo.getEmail();
		errStr = checkEmpty(email, Constants.ER001, "Email ");
		if (errStr.length() > 0) {
			return errStr;
		}
		// check format email
		errStr = checkFormat(email, Constants.ER009, "Email ", Constants.FORMAT_EMAIL);
		if (errStr.length() > 0) {
			return errStr;
		}

		// check phone number is empty
		String phone = userInfo.getPhonenumber();
		errStr = checkEmpty(phone, Constants.ER001, Constants.PHONE_NUMBER);
		if (errStr.length() > 0) {
			return errStr;
		}
		// check format phone number
		errStr = checkFormat(phone, Constants.ER009, Constants.PHONE_NUMBER, Constants.FORMAT_PHONE);
		if (errStr.length() > 0) {
			return errStr;
		}
		// check address is empty
		String address = userInfo.getAddress();
		errStr = checkEmpty(address, Constants.ER001, Constants.ADDRESS);
		if (errStr.length() > 0) {
			return errStr;
		}
		// check for long addresses too
		errStr = checkMaxLength(address, Constants.ER006, 255, Constants.ADDRESS);
		if (errStr.length() > 0) {
			return errStr;
		}

		return "";
	}

	private String checkFormat(String text, String error, String label, String format) {
		if (text.length() == 0) {
			return "";
		}
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(text);
		if (!matcher.matches()) {
			String errStr = label + " " + Common.getErr(error);
			return errStr;
		}
		return "";
	}

	/**
	 * check format
	 *
	 * @param text
	 *            string to check
	 * @param error
	 *            error code
	 * @param label
	 *            error label
	 * @param format
	 *            format code
	 * @return String ( content error )
	 */
	private String checkEmpty(String text, String error, String label) {
		String errStr = "";
		if ("".equals(text)) {
			errStr = label + " " + Common.getErr(error);
			return errStr;
		}
		return "";
	}

	/**
	 * Maximum length check function
	 *
	 * @param text
	 *            string to check
	 * @param error
	 *            error code
	 * @param max
	 *            maximum length
	 * @param label
	 *            type of check information
	 * @return
	 */
	public static String checkMaxLength(String text, String error, int max, String label) {
		String errStr = "";
		if (text.length() > max) {
			errStr = label + " " + Common.getErr(error);
			return errStr;
		}
		return "";
	}
}
