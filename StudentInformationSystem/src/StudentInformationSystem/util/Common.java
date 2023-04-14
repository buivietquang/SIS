
package StudentInformationSystem.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import StudentInformationSystem.properties.MessageErrorProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Commonly used functions
 * Các chức năng thường dùng
 *
 */
public class Common {

	/**
	 * Random chain salt function
	 *
	 * @return salt chain random birth
	 */
	public static String getRandomString() {
		// create random strings that do not overlap using UUIDs
		// tạo các chuỗi ngẫu nhiên không trùng nhau bằng UUID
		String salt = UUID.randomUUID().toString();
		return salt;

	}

	/**
	 * String encoding algorithm SHA-1
	 * Thuật toán mã hóa chuỗi SHA-1
	 *
	 * @param pass
	 *            string needs encoding
	 * @param salt
	 *            Randomly generated salt series
	 * @return result string encoded by SHA-1 algorithm
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeSHA1(String pass, String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String data = pass + salt; // add two strings together
		byte[] byteOfData; // declare a byte array;
		try {
			byteOfData = data.getBytes("UTF-8"); // convert string into byte array
			// Create a MessageDigest with the SHA-1 algorithm
			// Tạo MessageDigest với thuật toán SHA-1
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

			byte[] code = sha1.digest(byteOfData);
			// create a String for the newly encrypted byte array
			// tạo String cho mảng byte vừa được mã hóa
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < code.length; i++) {
				sb.append(Integer.toString((code[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e1) {
			throw new NoSuchAlgorithmException();
		} catch (UnsupportedEncodingException e2) {
			throw new UnsupportedEncodingException();
		}
	}

	/**
	 * Function of sex list
	 * Chức năng của danh sách giới tính
	 *
	 * @return List gender
	 */
	public static ObservableList<String> getListGender() {
		ObservableList<String> genderArr = FXCollections.observableArrayList();
		genderArr.add("Male");
		genderArr.add("Female");
		genderArr.add("Other");
		return genderArr;
	}

	/**
	 * function takes the current year
	 *
	 * @return the current year
	 */
	public static int getYearNow() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Get list of five
	 *
	 * @param fromYear
	 *            the year begins
	 * @param toYear
	 *            the final year
	 * @return
	 */
	public static ObservableList<String> getListYear(int fromYear, int toYear) {
		ObservableList<String> listYear = FXCollections.observableArrayList();
		for (int i = fromYear; i < toYear + 1; i++) {
			listYear.add(i + "");
		}
		return listYear;
	}

	/**
	 * Get list function month
	 *
	 * @return listMonth
	 */
	public static ObservableList<String> getListMonth() {
		ObservableList<String> listMonth = FXCollections.observableArrayList();
		for (int i = 1; i < 13; i++) {
			listMonth.add(i + "");
		}
		return listMonth;
	}

	/**
	 * Get list function day
	 *
	 * @return listDay
	 */
	public static ObservableList<String> getListDay(int year, int month) {
		ObservableList<String> listDay = FXCollections.observableArrayList();
		for (int i = 1; i < 29; i++) {
			listDay.add(i + "");
		}
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			listDay.add(29 + "");
			listDay.add(30 + "");
			break;
		case 2:
			if (year % 4 == 0) {
				listDay.add(29 + "");
			}
			break;
		default:
			listDay.add(29 + "");
			listDay.add(30 + "");
			listDay.add(31 + "");
		}
		return listDay;
	}

	/**
	 * converts the date string into a format in the DB
	 * chuyển đổi chuỗi ngày thành định dạng trong DB
	 *
	 * @param year
	 *            year
	 * @param month
	 *            month
	 * @param day
	 *            day
	 * @return string year-month-date in userInfo
	 */
	public static String convertToString(int year, int month, int day) {
		String strDate = year + "-" + month + "-" + day;
		return strDate;
	}

	/**
	 * The function converts a five-month list
	 *
	 * @param dateStr
	 *            string of years / month / day
	 * @return arratylist string five months a day
	 * @throws ParseException
	 */
	public static String[] convertStringToList(String dateStr) throws ParseException {
		String[] list = new String[3];
		list = dateStr.split("-");
		return list;
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
	public static String checkFormat(String text, String error, String label, String format) {
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
	 * Convert a string to a date in the year
	 * Chuyển đổi một chuỗi thành một ngày trong năm
	 *
	 * @param strDate
	 *            string to be transferred - chuỗi cần chuyển
	 * @return static ObservableList<Integer>
	 * @throws ParseException
	 */
	public static ObservableList<Integer> convertToArr(String strDate) throws ParseException {
		ObservableList<Integer> ymdArr = FXCollections.observableArrayList();

		ymdArr.add(Integer.parseInt(strDate.substring(0, 4)));
		ymdArr.add(Integer.parseInt(strDate.substring(5, 7)));
		ymdArr.add(Integer.parseInt(strDate.substring(8, 10)));

		return ymdArr;
	}

	/**
	 * error retrieval function
	 * chức năng truy xuất lỗi
	 *
	 * @param key:
	 *            error code
	 *
	 * @return content error
	 */
	public static String getErr(String key) {
		MessageErrorProperties mesErr = new MessageErrorProperties();
		String err = "";
		err = mesErr.getData(key);
		return err;
	}

	/**
	 * fuzzy search processing
	 * xử lý tìm kiếm 
	 * 
	 * @param key
	 * @return
	 */
	public static boolean fuzzySearch(KeyEvent key) {

		if (key.getCode() == KeyCode.BACK_SPACE)
			return true;
		else if (key.getCode().isDigitKey())
			return true;
		else if (key.getCode().isLetterKey()) {
			Pattern pattern = Pattern.compile(Constants.FORMAT_FUZZYSEARCH);
			if (pattern.matcher(key.getCode().toString()).matches())
				return true;
			else
				return false;
		} else
			return false;
	}
}
