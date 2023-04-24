
package StudentInformationSystem.controller;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.RegisterSubject;
import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.logic.RegisterSubjectLogic;
import StudentInformationSystem.logic.impl.RegisterSubjectLogicImpl;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Register for the subject
 * Đăng ký môn học
 * 
 *
 */
public class StudentRegisterSubjectController implements Initializable {

	static UserInfo userInfo;
	CustomAlert customAlert = new CustomAlert();

	private ObservableList<String> rqList = FXCollections.observableArrayList();
	private ObservableList<RegisterSubject> rsList;
	private RegisterSubject addNewRS;
	private RegisterSubjectLogic registerSubjectLogic = new RegisterSubjectLogicImpl();
	private int total = 0;
	boolean check = false;

	@FXML
	private Button btnSendRegister;
	@FXML
	private Button btnDeleteRegister;
	@FXML
	private ComboBox<String> cbSemesterRS;
	@FXML
	private TextField tfSubjectCodeRS;
	@FXML
	private Label lbErrorRS;
	@FXML
	private Label totalCredit;
	@FXML
	private CheckBox cbAll;
	@FXML
	private TableView<RegisterSubject> tableRS;
	@FXML
	private TableColumn<RegisterSubject, String> subjectCodeRS;
	@FXML
	private TableColumn<RegisterSubject, String> subjectNameRS;
	@FXML
	private TableColumn<RegisterSubject, String> instituteRS;
	@FXML
	private TableColumn<RegisterSubject, String> stageRegisterRS;
	@FXML
	private TableColumn<RegisterSubject, Integer> creditRS;
	@FXML
	private TableColumn<RegisterSubject, CheckBox> checkboxRS;

	private ObservableList<String> SemesterList = FXCollections.observableArrayList("20172", "20181", "20182");

	/**
	 * Display subject register subject
	 * Hiển thị chủ đề đăng ký chủ đề
	 * 
	 * @param Original
	 *            AnchorPane stackPane to change the student interface
	 * @param userInfo
	 *            Information of students currently in use
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane, UserInfo userInfo) throws IOException {
		StudentRegisterSubjectController.userInfo = userInfo;
		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/StudentRegisterSubjectView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 * Initialize the value for the semester's comboBox set Disable registration
	 * buttons, delete the module for the Semester is not open
	 * 
	 * Khởi tạo giá trị cho bộ comboBox của học kỳ Vô hiệu hóa nút đăng ký, 
	 * xóa mô-đun cho Học kỳ không mở
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cbSemesterRS.setItems(SemesterList);
		tfSubjectCodeRS.setDisable(true);
		btnDeleteRegister.setDisable(true);
		btnSendRegister.setDisable(true);
	}

	/**
	 * Get the value of the semester that students choose Show the courses
	 * registered in that period up on the board
	 * 
	 * Lấy giá trị của học kỳ mà sinh viên chọn 
	 * Hiển thị các khóa học đã đăng ký trong khoảng thời gian đó lên bảng
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void onChangeCbSemester() throws ClassNotFoundException, SQLException {

		cbAll.setSelected(false);
		total = 0;
		String value = cbSemesterRS.getValue();

		if (value == "20182") {
			tfSubjectCodeRS.setDisable(false);
			btnSendRegister.setDisable(false);
			btnDeleteRegister.setDisable(false);
		} else {
			tfSubjectCodeRS.setDisable(true);
			btnSendRegister.setDisable(true);
			btnDeleteRegister.setDisable(true);
		}

		rsList = registerSubjectLogic.getRegisterSubject(userInfo.getIdStudent(), value);

		// set table column
		subjectCodeRS.setCellValueFactory(new PropertyValueFactory<RegisterSubject, String>("subjectCode"));
		subjectNameRS.setCellValueFactory(new PropertyValueFactory<RegisterSubject, String>("subjectName"));
		instituteRS.setCellValueFactory(new PropertyValueFactory<RegisterSubject, String>("institute"));
		stageRegisterRS.setCellValueFactory(new PropertyValueFactory<RegisterSubject, String>("stageRegister"));
		creditRS.setCellValueFactory(new PropertyValueFactory<RegisterSubject, Integer>("credit"));
		checkboxRS.setCellValueFactory(new PropertyValueFactory<RegisterSubject, CheckBox>("select"));

		tableRS.setItems(rsList);

		// lấy giá trị của tổng số tín chỉ đăng ký
		// Get the value of the total number of registered credits
		for (int i = 0; i < tableRS.getItems().size(); i++)
			total += tableRS.getItems().get(i).getCredit();

		totalCredit.setText(String.valueOf(total));
	}

	/**
	 * Check the conditions before applying for a subject If the student is
	 * eligible, the subject will be added to the table
	 * 
	 * Kiểm tra các điều kiện trước khi đăng ký môn học Nếu sinh viên
	 * đủ điều kiện, đối tượng sẽ được thêm vào bảng
	 *
	 * @param key
	 *            Getting input events from the keyboard
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void addNewSubject(KeyEvent key) throws ClassNotFoundException, SQLException {

		if (key.getCode() == KeyCode.ENTER) {

			rqList.removeAll(rqList);
			// tìm kiếm mã học phần trước khi thêm
			// search for subject code before adding
			addNewRS = registerSubjectLogic.searchBeforeAddSubject(tfSubjectCodeRS.getText().toString().trim());

			// nếu mã học phần không tồn tại thì thông báo
			// If the subject code does not exist then the message
			if (addNewRS == null)
				lbErrorRS.setText(Constants.SUBJECT_NOT_EXIST);
			else {
				// nếu đã đăng ký quá 24 tín
				// if registered over 24 credits
				if (addNewRS.getCredit() + total > 24) {
					lbErrorRS.setText(Constants.OVER_24);
					return;
				}
				// nếu chưa đăng ký quá 24 tín
				// if you have not registered for more than 24 credits
				tableRS.getItems().stream().filter(
						item -> item.getSubjectCode().equals(tfSubjectCodeRS.getText().toString().toUpperCase().trim()))
						.findAny().ifPresentOrElse((item) -> {
							lbErrorRS.setText(Constants.SUBJECT_DUPLICATE);
							tfSubjectCodeRS.setText("");
						}, () -> {

							try {
								rqList = registerSubjectLogic.checkRequiredSubject(
										tfSubjectCodeRS.getText().toString().toUpperCase().trim(),
										userInfo.getIdStudent());
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}

							if (rqList.size() == 0) {
								rsList.add(addNewRS);
								total += addNewRS.getCredit();
								lbErrorRS.setText("");
							} else {
								StringBuilder err = new StringBuilder();
								int size = rqList.size();

								for (int i = 0; i < size; i++) {
									tableRS.getItems().stream()
											.filter(item -> rqList.get(0).equals(item.getSubjectCode())).findAny()
											.ifPresent(item -> {
												rqList.remove(0);
											});
								}

								if (rqList.size() == 0) {
									total += addNewRS.getCredit();
									rsList.add(addNewRS);
									lbErrorRS.setText("");
								} else {
									err.append(Constants.LACK_OF_SUBJECT);
									for (int i = 0; i < rqList.size(); i++) {
										err.append(rqList.get(i) + " ");
									}
									lbErrorRS.setText(err.toString());
								}
							}
							tfSubjectCodeRS.setText("");
							totalCredit.setText(String.valueOf(total));
						});
			}

			tableRS.setItems(rsList);
		}
	}

	/**
	 * Save the student modules registered to Database
	 * Lưu các học phần sinh viên đã đăng ký vào CSDL
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void completeRegister() throws ClassNotFoundException, SQLException {

		RegisterSubject registerSubject;
		ObservableList<RegisterSubject> beforeDeleteList = FXCollections.observableArrayList();

		Alert alert = customAlert.createAlert(Constants.INFORMATION, Constants.REGISTRATION);

		if (alert.getResult() == ButtonType.OK) {
			for (int i = 0; i < tableRS.getItems().size(); i++) {
				registerSubject = new RegisterSubject();
				registerSubject = tableRS.getItems().get(i);

				if (registerSubject.getStageRegister() == "")
					check = registerSubjectLogic.insertSubject(userInfo.getIdStudent(), registerSubject.getSubjectCode());
			}

			beforeDeleteList = registerSubjectLogic.getRegisterSubject(userInfo.getIdStudent(), "20182");

			for (int i = 0; i < beforeDeleteList.size(); i++) {

				int count = 0;
				for (int j = 0; j < rsList.size(); j++) {
					if (beforeDeleteList.get(i).getSubjectCode().equals(rsList.get(j).getSubjectCode()))
						count++;
				}

				if (count == 0)
					check = registerSubjectLogic.deleteSubject(userInfo.getIdStudent(),
							beforeDeleteList.get(i).getSubjectCode());
			}
			if (check == false)
				customAlert.createAlert(Constants.ERROR, Constants.ERROR_REGISTRATION);
			else
				customAlert.createAlert(Constants.INFORMATION, Constants.SUCCESS_REGISTRATION);

			onChangeCbSemester();
		}
	}

	/**
	 * Delete the selected modules
	 * Xóa các mô-đun đã chọn
	 */
	@FXML
	protected void removeSubject() {

		for (int i = 0; i < tableRS.getItems().size(); i++) {
			if (tableRS.getItems().get(i).getSelect().isSelected() == true) {
				rsList.remove(tableRS.getItems().get(i));
				i--;
			}
		}

		total = 0;
		for (int i = 0; i < tableRS.getItems().size(); i++)
			total += tableRS.getItems().get(i).getCredit();

		totalCredit.setText(String.valueOf(total));
	}

	/**
	 * All check boxes for each module will be enabled
	 * Tất cả check box cho mỗi mô-đun sẽ được bật
	 */
	@FXML
	protected void checkAll() {

		if (cbAll.isSelected() == true) {
			for (int i = 0; i < tableRS.getItems().size(); i++) {
				tableRS.getItems().get(i).getSelect().setSelected(true);
			}
		} else {
			for (int i = 0; i < tableRS.getItems().size(); i++) {
				tableRS.getItems().get(i).getSelect().setSelected(false);
			}
		}
	}

	// -------------------searchSubjectPane------------------------------
	@FXML
	private Button btnSearchSubject;

	/**
	 * Search for subject
	 * Tìm kiếm môn học
	 * 
	 * @throws IOException
	 */
	@FXML
	protected void changeSearchSubjectPane() throws IOException {
		Stage Stage = new Stage();
		Parent root = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/StudentSearchSubjectView.fxml"));
		Scene scene = new Scene(root, 976, 544);

		Stage.setScene(scene);

		Stage.show();
	}
	// -------------------searchSubjectPane------------------------------

}
