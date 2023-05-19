
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.dao.impl.UserDaoImpl;
import StudentInformationSystem.entity.ManageInfo;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
 * Chức năng chính của lớp là hiển thị menu
 * Thay đổi qua lại giữa các giao diện quản lý
 *
 */

public class ManageBaseController implements Initializable {

	static ManageInfo manageInfo = new ManageInfo();

	/**
	 * 
	 * 
	 * Giao diện hiển thị cho người quản trị
	 *
	 * @param userName
	 *            Username of the user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void setWindows(String userName) throws ClassNotFoundException, SQLException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		manageInfo = userDaoImpl.getAdminbyName(userName);
		Scene scene;
		try {

			Stage Stage = new Stage();
			Stage.setTitle("Student Information System");
			Parent root = FXMLLoader.load(getClass().getResource("/StudentInformationSystem/view/BaseAdminView.fxml"));

			scene = new Scene(root, 1300, 650);

			scene.getStylesheets().add(getClass().getResource("/StudentInformationSystem/view/BaseAdminView.css").toExternalForm());
			Stage.setScene(scene);

			Stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * Khởi tạo và hiển thị tên người dùng hiện tại. Cơ chế quản lý đối tượng
	 * là chế độ xem mặc định
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lbUserName.setText(Constants.XINCHAO + manageInfo.getFullname());
		try {
			changeManageSubjectPane();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*--------------------------------------------------*/
	/*---------------------Interface--------------------*/
	/*--------------------------------------------------*/
	@FXML
	public Button btnupdateNoticePane, btnupdateGradePane, btnupdateSubjectPane, btnupdateInstitutePane;
	@FXML
	public Label lbUserName;
	@FXML
	public AnchorPane stackPane;

	/*--------------------------------------------------*/
	/*---------------------ChangePane-------------------*/
	/*--------------------------------------------------*/

	/**
	 * Hiển thị giao diện Quản lý học tập
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeManageGradePane() throws IOException {
		ManageGradeController manageGradeController = new ManageGradeController();
		manageGradeController.setWindow(stackPane, manageInfo);
	}

	/**
	 * Hiển thị giao diện Trình quản lý thông báo
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeManageNoticePane() throws IOException {
		ManageNoticeController manageNoticeController = new ManageNoticeController();
		manageNoticeController.setWindow(stackPane);
	}

	/**
	 * Hiển thị giao diện Quản lý sinh viên
	 * 
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeManagementStudentPane() throws IOException {
		ManageStudentController manageStudentController = new ManageStudentController();
		manageStudentController.setWindow(stackPane, manageInfo);
	}

	/**
	 * Hiển thị giao diện Quản lý môn học
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeManageSubjectPane() throws IOException {
		ManageSubjectController manageSubjectController = new ManageSubjectController();
		manageSubjectController.setWindow(stackPane, manageInfo);
	}

	/**
	 * Hiển thị giao diện Quản lý Khoa
	 * 
	 * @throws IOException
	 */
	@FXML
	public void changeManageInstitutePane() throws IOException {
		ManageInstituteController manageInstituteController = new ManageInstituteController();
		manageInstituteController.setWindow(stackPane);
	}

	/*--------------------------------------------------*/
	/*---------------------Log Out----------------------*/
	/*--------------------------------------------------*/

	@FXML
	private Button btnLogoutAdmin;

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
				Stage st = (Stage) btnLogoutAdmin.getScene().getWindow();
				st.close();
			})).play();
		}
	}

}
