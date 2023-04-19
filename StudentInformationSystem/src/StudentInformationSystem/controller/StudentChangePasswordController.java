
package StudentInformationSystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import StudentInformationSystem.validate.ValidateUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Change password
 * 
 */
public class StudentChangePasswordController implements Initializable {

	static UserInfo userInfo;
	CustomAlert customAlert = new CustomAlert();

	@FXML
	private PasswordField oldPassword, newPassword, confirmPassword;
	@FXML
	private Text changePasswordNotice;
	@FXML
	private TextField userName;

	/**
	 * Handle function when click on save button
	 * 
	 *  Chức năng xử lý khi nhấp vào nút lưu
	 * 
	 * @param event
	 */
	@FXML
	protected void savePassword(ActionEvent event) {
		try {

			ValidateUser validateUser = new ValidateUser();
			UserLogic userLogic = new UserLogicImpl();
			String oldPw = oldPassword.getText();
			String newPw = newPassword.getText();
			String confirmPw = confirmPassword.getText();
			String errStr = validateUser.validateChangePassworrd(oldPw, newPw, confirmPw, userName.getText());
			if (errStr.length() > 0) {
				changePasswordNotice.setText(errStr);
				oldPassword.clear();
				newPassword.clear();
				confirmPassword.clear();
			} else {
				userLogic.updatePassword(newPw, userName.getText());
				alertSuccess();
				final Node source = (Node) event.getSource();
				final Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Message display success
	 * 
	 * Thông báo hiển thị thành công
	 */

	protected void alertSuccess() {
		customAlert.createAlert(Constants.INFORMATION, "Thành công");
	}

	/**
	 * The function defaults to the textField, comboBox
	 * 
	 * Hàm mặc định là textField, comboBox
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userName.setText(userInfo.getUsername());
	}

}
