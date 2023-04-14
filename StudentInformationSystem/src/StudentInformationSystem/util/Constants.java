
package StudentInformationSystem.util;

/**
 * contains constants
 * chứa các hằng số
 *
 */
public class Constants {
	// database
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "NULL";
	public static final String PASSWORD = "root";

	public static final String MESSAGE_ERROR = "message_error.properties";

	public static final int START_YEAR = 1980;

	// Alert
	public static final String INFORMATION = "Thông báo";
	public static final String WARNING = "Cảnh báo";
	public static final String ERROR = "Lỗi";
	public static final String SUCCESS = "Thành công";

	public static final String XINCHAO = "Xin chào    ";

	public static final String OVER_24 = "Bạn không được đăng ký quá 24 tín";
	public static final String SUBJECT_DUPLICATE = "Mã lớp đã bị trùng";
	public static final String LACK_OF_SUBJECT = "Bạn chưa đủ điều kiện đăng ký vì thiếu học phần ";
	public static final String REGISTRATION = "Bạn có muốn gửi đăng ký không";
	public static final String ERROR_REGISTRATION = "Gửi đăng ký thất bại hoặc bạn chưa thay đổi đăng ký";
	public static final String SUCCESS_REGISTRATION = "Gửi đăng ký thành công";
	public static final String SUBJECT_NOT_EXIST = "Mã học phần không tồn tại";
	public static final String LOGOUT = "Bạn có muốn đăng xuất không";
	public static final String CONFIRM_DEL = "Bạn có muốn xóa thông báo này";
	public static final String CONFIRM_BLOCK = "Bạn có chắc chắn khóa sinh viên này";

	public static final String HAVE_ERROR = "Đã có lỗi xảy ra";

	// format
	public static final String FORMAT_PASSWORD = "^[a-zA-Z0-9!@#$%^&*(){}_+.,<>?;:'-=]{8,15}$";
	public static final String FORMAT_PHONE = "^[0-9]*$";
	public static final String FORMAT_USERNAME = "^[a-zA-Z]{1}[a-zA-Z0-9]*$";
	public static final String FORMAT_MANAGER = "^[0-9]{5-10}$";
	public static final String FORMAT_EMAIL = "^[\\w!#$%&â€™*+/=?`{|}~^-]+(?:\\.[\\w!#$%&â€™*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	public static final String FORMAT_ADMIN = "^[a-zA-Z]{1}[a-zA-Z0-9]*$";
	public static final String FORMAT_SUBJECTCODE = "[A-Z]{2,3}[0-9]{4}";
	public static final String FORMAT_FUZZYSEARCH = "[a-zA-Z]";

	// Label

	public static final String NAME = "Họ tên ";
	public static final String ADDRESS = "Địa chỉ ";
	public static final String CMND = "Số CMND ";
	public static final String BIRTHDAY = "Ngày sinh ";
	public static final String CLASS_STUDENT = "Lớp ";
	public static final String DATE_ISSUE = "Ngày cấp ";
	public static final String GENDER = "Giới tính ";
	public static final String COURSE_STUDENT = "Khóa ";
	public static final String ISSUE_PLACE = "Nơi cấp ";
	public static final String MAJORS = "Ngành học ";
	public static final String PHONE_NUMBER = "Số điện thoại ";
	public static final String INSTITUTE = "Khoa ";
	public static final String NEW_PASS = "Mật khẩu mới ";
	public static final String PASS = "Mật khẩu ";
	// button
	public static final String UPDATE = "Cập nhật";
	public static final String BLOCK = "Khóa sinh viên";
	public static final String CANCEL = "Hủy";

	// message error
	public static final String ER001 = "ER001";
	public static final String ER002 = "ER002";
	public static final String ER003 = "ER003";
	public static final String ER004 = "ER004";
	public static final String ER005 = "ER005";
	public static final String ER006 = "ER006";
	public static final String ER007 = "ER007";
	public static final String ER008 = "ER008";
	public static final String ER009 = "ER009";
	public static final String ER010 = "ER010";
	public static final String ER011 = "ER011";
	public static final String ER012 = "ER012";
	public static final String ER013 = "ER013";
	public static final String ER014 = "ER014";
	public static final String ER015 = "ER015";
	public static final String ER016 = "ER016";
	public static final String ER017 = "ER017";
	public static final String ER018 = "ER018";
	public static final String ER019 = "ER019";

	public static final String EMPTY_STRING = "";

}
