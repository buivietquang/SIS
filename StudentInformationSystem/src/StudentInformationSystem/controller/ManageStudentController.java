package StudentInformationSystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import StudentInformationSystem.entity.ManageInfo;
import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.logic.impl.UserLogicImpl;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Common;
import StudentInformationSystem.util.Constants;
import StudentInformationSystem.validate.ValidateUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

/**
 * Student Information Management - Quản lý thông tin sinh viên
 */
public class ManageStudentController implements Initializable {

	static ManageInfo manageInfo;
	CustomAlert customAlert = new CustomAlert();

	/**
	 * Hiển thị giao diện quản lý sinh vin
	 * @param stackPane
	 * @param manageInfo
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane, ManageInfo manageInfo) throws IOException {
		ManageSubjectController.manageInfo = manageInfo;
		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/ManageStudentView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 * Khởi tạo
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			showUser("");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private TableView<UserInfo> tableUser;
	@FXML
	private TableColumn<UserInfo, String> columnID;
	@FXML
	private TableColumn<UserInfo, String> columnUserName;
	@FXML
	private TableColumn<UserInfo, String> columnFullname;
	@FXML
	private TableColumn<UserInfo, String> columnBirthday;
	@FXML
	private TableColumn<UserInfo, String> columnGender;
	@FXML
	private TableColumn<UserInfo, String> columnEmail, columnPhone, columnAddress;
	@FXML
	private TextField keyword;

	/**
	 * Hiển thị danh sách người = MSSV
	 *
	 * @param username
	 *            Student's MSSV
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void showUser(String username) throws ClassNotFoundException, SQLException {
		tableUser.setItems(null);
		UserLogicImpl userLogicImpl = new UserLogicImpl();
		ObservableList<UserInfo> listUser = userLogicImpl.getListUser("");
		columnID.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("idAcount"));
		columnUserName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("username"));
		columnFullname.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("fullname"));
		columnBirthday.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("dateOfBirth"));
		columnGender.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("gender"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("email"));
		columnPhone.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("phonenumber"));
		columnAddress.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("address"));
		tableUser.setItems(listUser);
	}

	/**
	 * processing khi click vào button
	 *
	 * @param event
	 *            Ghi lại khi click vào button
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	private void searchUser(ActionEvent event) throws ClassNotFoundException, SQLException {
		tableUser.setItems(null);
		String username = keyword.getText();
		UserLogicImpl userLogicImpl = new UserLogicImpl();
		ObservableList<UserInfo> listUser = userLogicImpl.getListUser(username);
		columnID.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("idAcount"));
		columnUserName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("username"));
		columnFullname.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("fullname"));
		columnBirthday.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("dateOfBirth"));
		columnGender.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("gender"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("email"));
		columnPhone.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("phonenumber"));
		columnAddress.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("address"));
		tableUser.setItems(listUser);
	}

	@FXML
	private TextField linkFileExcel;

	File file;

	/**
	 * Trình sử lý onclick tren button
	 * 
	 * @param event
	 * @throws SQLException
	 */
	@FXML
	protected void openExcelFile(ActionEvent event) throws SQLException {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		file = fileChooser.showOpenDialog(stage);
		linkFileExcel.setText("" + file);
	}

	@FXML
	private Text addUserNotification;

	/**
	 * Onclick handler trên button add (add student list)
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 */
	@FXML
	protected void addUser(ActionEvent event)
			throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, ParseException {
		ValidateUser validateUser = new ValidateUser();
		String errStr = "";
		if (file != null) {
			ArrayList<UserInfo> listUser = readUserExcel(file);
			int count = 0;
			for (int i = 0; i < listUser.size(); i++) {
				UserInfo userInfo = listUser.get(i);
				errStr = validateUser.validateUserInfo(userInfo);
				if (!Constants.EMPTY_STRING.equals(errStr)) {
					addUserNotification.setText(errStr);
				} else {
					UserLogicImpl userLogicImpl = new UserLogicImpl();
					userLogicImpl.insertUser(userInfo);
					count++;
				}
			}
			if (count == listUser.size()) {
				alertSuccess();
			}
		}
		showUser("");
		linkFileExcel.clear();

	}

	/**
	 * Processing chức năng khi double click vào tableUser
	 */
	@FXML
	public void handleRowSelect(MouseEvent event) {
		tableUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.getClickCount());
				if (event.getClickCount() > 1) {
					try {
						UserLogicImpl userLogicImpl = new UserLogicImpl();

						int idStudent = tableUser.getSelectionModel().getSelectedItem().getIdAcount();
						String userName = tableUser.getSelectionModel().getSelectedItem().getUsername();
						UserInfo userInfo = userLogicImpl.getUserbyName(userName);

						// Create pooup display user information
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(userName);
						alert.setHeaderText(userName + " --- " + userInfo.getFullname());
						alert.setContentText(null);

						GridPane expContent = new GridPane();
						// add fullname
						Label fullnameL = new Label(Constants.NAME);
						expContent.add(fullnameL, 0, 0);

						TextField fullnameTF = new TextField();
						fullnameTF.setText(userInfo.getFullname());
						fullnameTF.setMaxWidth(Double.MAX_VALUE);
						fullnameTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(fullnameTF, Priority.ALWAYS);
						GridPane.setHgrow(fullnameTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(fullnameTF, 1, 0);

						// add address
						Label addressL = new Label(Constants.ADDRESS);
						expContent.add(addressL, 2, 0);

						TextField addressTF = new TextField();
						addressTF.setText(userInfo.getAddress());
						addressTF.setMaxWidth(Double.MAX_VALUE);
						addressTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(addressTF, Priority.ALWAYS);
						GridPane.setHgrow(addressTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(addressTF, 3, 0);

						// add identification number
						Label identityCardNumberL = new Label(Constants.CMND);
						expContent.add(identityCardNumberL, 4, 0);

						TextField identityCardNumberTF = new TextField();
						identityCardNumberTF.setText(userInfo.getIdentityCardNumber());
						identityCardNumberTF.setMaxWidth(Double.MAX_VALUE);
						identityCardNumberTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(identityCardNumberTF, Priority.ALWAYS);
						GridPane.setHgrow(identityCardNumberTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(identityCardNumberTF, 5, 0);

						// add birthday
						Label birthdayL = new Label(Constants.BIRTHDAY);
						expContent.add(birthdayL, 0, 1);

						DatePicker birthdayTF = new DatePicker();
						ObservableList<Integer> ymdArr = Common.convertToArr(userInfo.getDateOfBirth());
						birthdayTF.setValue(LocalDate.of(ymdArr.get(0), ymdArr.get(1), ymdArr.get(2)));
						birthdayTF.setShowWeekNumbers(true);

						birthdayTF.setMaxWidth(Double.MAX_VALUE);
						birthdayTF.setMaxHeight(Double.MAX_VALUE);
						expContent.add(birthdayTF, 1, 1);

						// add classStudent
						Label classL = new Label(Constants.CLASS_STUDENT);
						expContent.add(classL, 2, 1);

						TextField classTF = new TextField();
						classTF.setText(userInfo.getClassUser());
						classTF.setMaxWidth(Double.MAX_VALUE);
						classTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(classTF, Priority.ALWAYS);
						GridPane.setHgrow(classTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(classTF, 3, 1);

						// add date issue
						Label dateIssueL = new Label(Constants.DATE_ISSUE);
						expContent.add(dateIssueL, 4, 1);

						TextField dateIssueTF = new TextField();
						dateIssueTF.setText(userInfo.getDateIssue());
						dateIssueTF.setMaxWidth(Double.MAX_VALUE);
						dateIssueTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(dateIssueTF, Priority.ALWAYS);
						GridPane.setHgrow(dateIssueTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(dateIssueTF, 5, 1);

						// add gender
						Label genderL = new Label(Constants.GENDER);
						expContent.add(genderL, 0, 2);

						TextField genderTF = new TextField();
						genderTF.setText(userInfo.getGender());
						genderTF.setMaxWidth(Double.MAX_VALUE);
						genderTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(genderTF, Priority.ALWAYS);
						GridPane.setHgrow(genderTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(genderTF, 1, 2);

						// add courseStudent
						Label courseL = new Label(Constants.COURSE_STUDENT);
						expContent.add(courseL, 2, 2);

						TextField courseTF = new TextField();
						courseTF.setText(userInfo.getCourse());
						courseTF.setMaxWidth(Double.MAX_VALUE);
						courseTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(courseTF, Priority.ALWAYS);
						GridPane.setHgrow(courseTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(courseTF, 3, 2);

						// add issue Place identification number
						Label issuePlaceL = new Label(Constants.ISSUE_PLACE);
						expContent.add(issuePlaceL, 4, 2);

						TextField issuePlaceTF = new TextField();
						issuePlaceTF.setText(userInfo.getIssuePlace());
						issuePlaceTF.setMaxWidth(Double.MAX_VALUE);
						issuePlaceTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(issuePlaceTF, Priority.ALWAYS);
						GridPane.setHgrow(issuePlaceTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(issuePlaceTF, 5, 2);

						// add email
						Label emailL = new Label("Email");
						expContent.add(emailL, 0, 3);

						TextField emailTF = new TextField();
						emailTF.setText(userInfo.getEmail());
						emailTF.setMaxWidth(Double.MAX_VALUE);
						emailTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(emailTF, Priority.ALWAYS);
						GridPane.setHgrow(emailTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(emailTF, 1, 3);

						// add majors (nganh hoc)
						Label majorsL = new Label(Constants.MAJORS);
						expContent.add(majorsL, 2, 3);

						TextField majorsTF = new TextField();
						majorsTF.setText(userInfo.getMajors());
						majorsTF.setMaxWidth(Double.MAX_VALUE);
						majorsTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(majorsTF, Priority.ALWAYS);
						GridPane.setHgrow(majorsTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(majorsTF, 3, 3);

						// add password
						Label passwordL = new Label(Constants.NEW_PASS);
						expContent.add(passwordL, 4, 3);

						PasswordField passwordTF = new PasswordField();
						passwordTF.setMaxWidth(Double.MAX_VALUE);
						passwordTF.setMaxHeight(Double.MAX_VALUE);
						expContent.add(passwordTF, 5, 3);

						// add phone nummber
						Label phoneNumberL = new Label(Constants.PHONE_NUMBER);
						expContent.add(phoneNumberL, 0, 4);

						TextField phoneNumberTF = new TextField();
						phoneNumberTF.setText(userInfo.getPhonenumber());
						phoneNumberTF.setMaxWidth(Double.MAX_VALUE);
						phoneNumberTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(phoneNumberTF, Priority.ALWAYS);
						GridPane.setHgrow(phoneNumberTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(phoneNumberTF, 1, 4);

						// add institute (khoa)
						Label instituteL = new Label(Constants.INSTITUTE);
						expContent.add(instituteL, 2, 4);

						TextField instituteTF = new TextField();
						instituteTF.setText(userInfo.getInstituteName());
						instituteTF.setEditable(false);
						instituteTF.setMaxHeight(Double.MAX_VALUE);
						GridPane.setVgrow(instituteTF, Priority.ALWAYS);
						GridPane.setHgrow(instituteTF, Priority.ALWAYS);
						expContent.setMaxWidth(Double.MAX_VALUE);
						expContent.add(instituteTF, 3, 4);

						// create button
						ButtonType buttonTypeUpdate = new ButtonType(Constants.UPDATE);
						ButtonType buttonTypeBlock = new ButtonType(Constants.BLOCK);
						ButtonType buttonTypeCancel = new ButtonType(Constants.CANCEL);

						boolean flat = false;
						while (!flat) {
							alert.getDialogPane().setContent(expContent);

							alert.getButtonTypes().setAll(buttonTypeUpdate, buttonTypeBlock, buttonTypeCancel);
							Optional<ButtonType> result = alert.showAndWait();
							// click on button update
							if (result.get() == buttonTypeUpdate) {
								userInfo.setFullname(fullnameTF.getText());
								userInfo.setDateOfBirth(birthdayTF.getValue().toString());
								userInfo.setGender(genderTF.getText());
								userInfo.setEmail(emailTF.getText());
								userInfo.setPhonenumber(phoneNumberTF.getText());
								userInfo.setAddress(addressTF.getText());
								userInfo.setClassUser(classTF.getText());
								userInfo.setCourse(courseTF.getText());
								userInfo.setMajors(majorsTF.getText());
								userInfo.setIdentityCardNumber(identityCardNumberTF.getText());
								userInfo.setDateIssue(dateIssueTF.getText());
								userInfo.setIssuePlace(issuePlaceTF.getText());

								ValidateUser validateUser = new ValidateUser();
								String errStr = "";
								errStr = validateUser.validateUserInfo(userInfo);
								String newPass = passwordTF.getText();

								if (!Constants.EMPTY_STRING.equals(errStr)) {
									alert.setHeaderText(errStr);
									flat = false;
								} else {
									userLogicImpl.updateInfomation(userInfo);
									if (!"".equals(newPass)) {

										userLogicImpl.updatePassword(newPass, userName);

									}
									alertSuccess();
									showUser("");
									flat = true;
								}
								// click on button block
							} else if (result.get() == buttonTypeBlock) {
								Alert alertBlock = customAlert.createAlert(Constants.INFORMATION,
										Constants.CONFIRM_BLOCK);

								if (alertBlock.getResult() == ButtonType.OK) {
									userLogicImpl.blockUser(idStudent);
									alertSuccess();
									showUser("");
									flat = true;
								}
								// click cancel
							} else {
								flat = true;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * Hàm đọc dữ liệu trong file excel
	 *
	 * @param file:
	 *            files need to read the data
	 * 			  file cần đọc data
	 * @return readable list (list UserInfo)
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<UserInfo> readUserExcel(File file) throws IOException {
		ArrayList<UserInfo> listUser = null;
		if (file != null) {
			listUser = new ArrayList<>();
			FileInputStream fileInput = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fileInput);
			XSSFSheet sheet = wb.getSheetAt(0);
			String str[] = new String[18];
			UserInfo userInfo = null;
			for (Row row : sheet) {
				int count = 0;
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						str[count++] = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						str[count++] = "" + (int) cell.getNumericCellValue();
						break;
					}

				}
				userInfo = new UserInfo();
				userInfo.setUsername(str[0]);
				userInfo.setPassword(str[1]);
				userInfo.setConfirmPassword(str[2]);
				userInfo.setFullname(str[3]);
				userInfo.setAddress(str[4]);
				userInfo.setPhonenumber(str[5]);
				userInfo.setDateOfBirth(str[6]);
				userInfo.setEmail(str[7]);
				userInfo.setPosition(str[8]);
				userInfo.setGender(str[9]);
				userInfo.setClassUser(str[10]);
				userInfo.setCourse(str[11]);
				userInfo.setMajors(str[12]);
				userInfo.setIdentityCardNumber(str[13]);
				userInfo.setDateIssue(str[14]);
				userInfo.setIssuePlace(str[15]);
				userInfo.setIdInstitute(Integer.parseInt(str[16]));
				listUser.add(userInfo);
			}

			fileInput.close();
			wb.close();
		}
		return listUser;
	}

	/**
	 * Message display success - Thông báo thành công
	 */
	protected void alertSuccess() {
		customAlert.createAlert(Constants.INFORMATION, Constants.SUCCESS);
	}
}
