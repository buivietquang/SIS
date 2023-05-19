
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.logic.impl.UserLogicImpl;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Common;
import StudentInformationSystem.util.Constants;
import StudentInformationSystem.validate.ValidateUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * View and change student information
 * Xem và thay đổi thông tin sinh viên
 *
 */
public class StudentInfoController extends StudentBaseController implements Initializable {

	static UserInfo userInfo;
	CustomAlert customAlert = new CustomAlert();

	/**
	 * Display interface view and change information
	 * Hiển thị giao diện xem và thay đổi thông tin
	 *
	 * @param stackPane
	 *            Original AnchorPane to change student interface
	 * @param userInfo
	 *            Student information is in use
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane, UserInfo userInfo) throws IOException {

		StudentChangePasswordController.userInfo = userInfo;
		StudentInfoController.userInfo = userInfo;

		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/StudentchangeInfoView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	@FXML
	public AnchorPane updateInfomationPane;
	@FXML
	protected TextField updateFullname, updateEmail, updatePhone, updateClass, updateCourse, updateMajors,
			updateIdentity, updateDateIssue, updateIssuePlace, updateInstitute, updateAddress;

	@FXML
	protected ComboBox<String> updateYearBirth, updateMonthBirth, updateDayBirth, updateGender;

	/**
	 * create a month list
	 * tạo danh sách tháng
	 * 
	 * @param event
	 *            Catch events when you select year
	 * @throws SQLException
	 */
	@FXML
	protected void addListMonth(ActionEvent event) throws SQLException {
		String month = updateMonthBirth.getValue();
		if (month != null) {
			addListDay(event);
		}
	}

	/**
	 * create a day list
	 * tạo danh sách ngày
	 * 
	 * @param event
	 *            Catch events when you select month
	 * @throws SQLException
	 */
	@FXML
	protected void addListDay(ActionEvent event) throws SQLException {
		if (updateYearBirth.getValue() == null) {
			return;
		}
		int year = Integer.parseInt(updateYearBirth.getValue().toString());
		int month = Integer.parseInt(updateMonthBirth.getValue().toString());
		ObservableList<String> listDay = Common.getListDay(year, month);
		updateDayBirth.getItems().removeAll(updateDayBirth.getItems());
		updateDayBirth.getItems().addAll(listDay);
	}

	/**
	 * Click on the comfirm button (update student information)
	 * Click vào nút xác nhận (cập nhật thông tin sinh viên)
	 * 
	 * @param event
	 *            Catch events when you click on the button
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void updateUserInformation(ActionEvent event) throws ClassNotFoundException, SQLException {
		try {
			UserInfo userInfoUpdate = new UserInfo();
			UserLogicImpl userLogicImpl = new UserLogicImpl();
			ValidateUser validateUser = new ValidateUser();
			// get value on interface
			userInfoUpdate.setAddress(updateAddress.getText());
			userInfoUpdate.setFullname(updateFullname.getText());
			userInfoUpdate.setEmail(updateEmail.getText());
			userInfoUpdate.setPhonenumber(updatePhone.getText());
			userInfoUpdate.setClassUser(updateClass.getText());
			userInfoUpdate.setMajors(updateMajors.getText());
			userInfoUpdate.setCourse(updateCourse.getText());
			userInfoUpdate.setIdentityCardNumber(updateIdentity.getText());
			userInfoUpdate.setDateIssue(updateIdentity.getText());
			userInfoUpdate.setIssuePlace(updateIssuePlace.getText());
			userInfoUpdate.setGender(updateGender.getValue());
			int year = Integer.parseInt(updateYearBirth.getValue());
			int month = Integer.parseInt(updateMonthBirth.getValue());
			int day = Integer.parseInt(updateDayBirth.getValue());
			String dateOfBirth = Common.convertToString(year, month, day);
			userInfoUpdate.setDateOfBirth(dateOfBirth);
			userInfoUpdate.setIdStudent(userInfo.getIdStudent());
			// validate information
			String errStr = "";
			errStr = validateUser.validateUserInfo(userInfoUpdate);

			if (!Constants.EMPTY_STRING.equals(errStr)) {
				customAlert.createAlert(Constants.ERROR, errStr);
				// update
			} else {
				boolean checkUpdate = userLogicImpl.updateInfomation(userInfoUpdate);
				if (checkUpdate) {
					alertSuccess();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays pop-up password changes
	 * Hiển thị các thay đổi mật khẩu bật lên
	 * 
	 * @throws IOException
	 */
	@FXML
	protected void changePasswordPane() throws IOException {

		Stage Stage = new Stage();
		Parent root = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/StudentChangePassView.fxml"));
		Scene scene = new Scene(root, 974, 544);
		Stage.setScene(scene);

		Stage.show();

	}

	/**
	 * Message display success
	 * Thông báo hiển thị thành công
	 */
	protected void alertSuccess() {
		customAlert.createAlert(Constants.INFORMATION, Constants.SUCCESS);
	}

	/**
	 * The function initializes and views the user information
	 * Hàm khởi tạo và xem thông tin người dùng
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> genderArr = Common.getListGender();
		updateGender.getItems().addAll(genderArr);

		ObservableList<String> listYear = Common.getListYear(Constants.START_YEAR, Common.getYearNow());
		updateYearBirth.getItems().addAll(listYear);

		ObservableList<String> listMonth = Common.getListMonth();
		updateMonthBirth.getItems().addAll(listMonth);

		updateFullname.setText(userInfo.getFullname());
		String[] yearMonthDay = new String[3];

		try {
			yearMonthDay = Common.convertStringToList(userInfo.getDateOfBirth());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		updateYearBirth.setValue(yearMonthDay[0]);
		updateMonthBirth.setValue(yearMonthDay[1]);
		updateDayBirth.setValue(yearMonthDay[2]);
		updateGender.setValue(userInfo.getGender());
		updateEmail.setText(userInfo.getEmail());
		updatePhone.setText(userInfo.getPhonenumber());
		updateAddress.setText(userInfo.getAddress());
		updateClass.setText(userInfo.getClassUser());
		updateCourse.setText(userInfo.getCourse());
		updateMajors.setText(userInfo.getMajors());
		updateIdentity.setText(userInfo.getIdentityCardNumber());
		updateDateIssue.setText(userInfo.getDateIssue());
		updateIssuePlace.setText(userInfo.getIssuePlace());
		updateInstitute.setText(userInfo.getInstituteName());
	}
}
