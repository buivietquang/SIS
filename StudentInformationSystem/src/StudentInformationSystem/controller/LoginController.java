
package StudentInformationSystem.controller;

import java.util.regex.Pattern;

import StudentInformationSystem.util.Constants;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class handles login - Class xử lí login
 */

public class LoginController {

	@FXML
	private TextField signinUserName, signinPassword_text;
	@FXML
	private PasswordField signinPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Image logo;
	@FXML
	private AnchorPane loginPane;
	@FXML
	private AnchorPane containerPane;
	@FXML
	private Label labelLogin, lbError;
	@FXML
	private ImageView imgShowPassword, imgUnShowPassword;

	/**
	 * Hàm này sẽ được gọi khi chạy chương trình để hiển thị thông tin đăng nhập giao diện
	 */
	public void setWindows() {
		Stage Stage = new Stage();
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/StudentInformationSystem/view/LoginView.fxml"));
			Scene scene = new Scene(root, 1300, 650);
			scene.getStylesheets().add(getClass().getResource("/StudentInformationSystem/view/LoginView.css").toExternalForm());
			Stage.setScene(scene);
			Stage.setTitle("STUDENT INFORMATION SYSTEM");
			Stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bắt nhấn ENTER trong TextField mật khẩu để đăng nhập
	 * 
	 * @param key
	 * 				ghi lại tất cả được nhập từ bàn phím
	 */
	@FXML
	protected void onEnterPress(KeyEvent key) {
		if (key.getCode() == KeyCode.ENTER)
			login();
	}

	/**
	 * 
	 * Kiểm tra điều kiện đăng nhập </br> Nếu đủ điều kiện thì chuyển sang màn hình Dashboard
	 */
	@FXML
	protected void login() {
		// image.setVisible(true);
		new Timeline(new KeyFrame(Duration.millis(500), e -> {
			try {
				String userName = signinUserName.getText().toString();
				String password = signinPassword.getText().toString();
				ValidateUser validateUser = new ValidateUser();
				Pattern pattern = Pattern.compile(Constants.FORMAT_ADMIN);
				boolean check = false;

				if (userName.length() < 5 || password.length() < 5) {
					signinUserName.setStyle("-fx-border-color: #ff3d00;-fx-border-width:3px;");
					signinPassword.setStyle("-fx-border-color: #ff3d00;-fx-border-width:3px;");
					signinPassword.setText("");
					lbError.setText("Tài khoản và mật khẩu phải lớn hơn 5 ký tự");
				} else if (pattern.matcher(userName).matches()) {
					check = validateUser.validateUserLogin(userName, password, true);
					if (check) {
						// open ManageUser View
						try {
							Stage stage;
							stage = (Stage) btnLogin.getScene().getWindow();
							stage.close();
							ManageBaseController lms = new ManageBaseController();
							lms.setWindows(userName);
						} catch (Exception ev) {
							ev.printStackTrace();
						}
					} else {
						signinUserName.setStyle("-fx-border-color: #ff3d00;-fx-border-width:3px;");
						signinPassword.setStyle("-fx-border-color: #ff3d00;-fx-border-width:3px;");
						signinPassword.setText("");
					}
				} else {
					check = validateUser.validateUserLogin(userName, password, false);
					if (check) {
						// open User View
						try {
							Stage stage;
							stage = (Stage) btnLogin.getScene().getWindow();
							stage.close();
							StudentBaseController lms = new StudentBaseController();
							lms.setWindows(userName);
						} catch (Exception ev) {
							ev.printStackTrace();
						}
					} else {
						signinUserName.setStyle("-fx-border-color: #ff3d00;-fx-border-width:3px;");
						signinPassword.setStyle("-fx-border-color: #ff3d00;-fx-border-width:3px;");
						signinPassword.setText("");
						lbError.setText("Tài khoản hoặc mật khẩu không trùng khớp");
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		})).play();

	}

	/**
	 * 
	 * Thay đổi mật khẩu từ mật khẩu sang văn bản và ngược lại
	 *
	 * @param event
	 *            Get the mouse click 
	 */
	@FXML
	public void showPassword(MouseEvent event) {
		if (event.getTarget() == imgShowPassword) {
			signinPassword.setVisible(false);
			signinPassword_text.setText(signinPassword.getText());
			signinPassword_text.setVisible(true);
			imgShowPassword.setVisible(false);
			imgUnShowPassword.setVisible(true);
		} else if (event.getTarget() == imgUnShowPassword) {
			signinPassword.setVisible(true);
			signinPassword.setText(signinPassword_text.getText());
			signinPassword_text.setVisible(false);
			imgShowPassword.setVisible(true);
			imgUnShowPassword.setVisible(false);
		}
	}

}
