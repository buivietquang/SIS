
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.UserInfo;

import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * Chức năng chính của lớp là hiển thị menu dọc   
 * Chuyển đổi giữa các giao diện sinh viên được sử dụng
 *
 */
public class StudentBaseController implements Initializable {

	static UserInfo userInfo = new UserInfo();

	/**
	 * Display interface for students
	 * 
	 * @param userName
	 *            Username of the user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void setWindows(String userName) throws IOException {

		Scene scene;
		try {
			UserLogicImpl userLogicImpl = new UserLogicImpl();
			userInfo = userLogicImpl.getUserbyName(userName);
			Stage Stage = new Stage();
			Stage.setTitle("Student Information System");
			Parent root = FXMLLoader
					.load(getClass().getResource("/StudentInformationSystem/view/BaseStudentView.fxml"));

			scene = new Scene(root, 1300, 650);
			scene.getStylesheets()
					.add(getClass().getResource("/StudentInformationSystem/view/BaseStudentView.css").toExternalForm());
			Stage.setScene(scene);

			Stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*--------------------------------------------------*/
	/*--------------------Initialize--------------------*/
	/*--------------------------------------------------*/
	/**
	 * Initialize and display the current user name Set module registration is the
	 * default view
	 * 
	 * Khởi tạo và hiển thị tên người dùng hiện tại Đặt đăng ký mô-đun là
	 * chế độ xem mặc định
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			setStudentNoticePane();
			changeStudentRegisterSubjectPane();
			lbUserName.setText(Constants.XINCHAO + userInfo.getFullname());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*--------------------------------------------------*/
	/*---------------------Interface--------------------*/
	/*--------------------------------------------------*/
	@FXML
	private Button btnRegisterSubject, btnViewGrade, btnUserInfo, btnSearchFee, btnLogoutUser;
	@FXML
	private Label lbUserName, lbSIS;
	@FXML
	private AnchorPane headerPane;
	@FXML
	private AnchorPane stackPane, noticePane;

	/*--------------------------------------------------*/
	/*---------------------ChangePane-------------------*/
	/*--------------------------------------------------*/

	/**
	 * Displays the personal information management interface
	 * 
	 * Hiển thị giao diện quản lý thông tin cá nhân
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeStudentInfoPane() throws IOException {
		StudentInfoController studentInfoController = new StudentInfoController();
		studentInfoController.setWindow(stackPane, userInfo);
	}

	/**
	 * Show subject register interface
	 * 
	 * Hiển thị giao diện đăng ký môn học
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeStudentRegisterSubjectPane() throws IOException {
		StudentRegisterSubjectController registerSubjectController = new StudentRegisterSubjectController();
		registerSubjectController.setWindow(stackPane, userInfo);
	}

	/**
	 * Display interface view learning results
	 * 
	 * Giao diện hiển thị xem kết quả học tập
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeStudentGradePane() throws IOException {
		StudentGradeController studentGradeController = new StudentGradeController();
		studentGradeController.setWindow(stackPane, userInfo);
	}

	/**
	 * Display interface view notification
	 * 
	 * Giao diện hiển thị thông báo xem
	 * 
	 * @throws IOException
	 */
	public void setStudentNoticePane() throws IOException {
		StudentNoticeController studentNoticeController = new StudentNoticeController();
		studentNoticeController.setWindow(noticePane);
	}

	/**
	 * Display the tuition view interface
	 * 
	 * Hiển thị giao diện xem học phí
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void changeStudentFeePane(ActionEvent event) throws IOException {
		StudentFeeController studentFeeController = new StudentFeeController();
		studentFeeController.setWindow(stackPane, userInfo);
	}

	/*--------------------------------------------------*/
	/*---------------------Logout-----------------------*/
	/*--------------------------------------------------*/

	/**
	 * Logout
	 */
	@FXML
	protected void logout() {

		CustomAlert customAlert = new CustomAlert();
		Alert alert = customAlert.createAlert(Constants.INFORMATION, Constants.LOGOUT);
		if (alert.getResult() == ButtonType.OK) {
			LoginController logout = new LoginController();
			logout.setWindows();
			new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> {
				Stage st = (Stage) btnLogoutUser.getScene().getWindow();
				st.close();
			})).play();
		}
	}

}
