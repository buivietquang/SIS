package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.controlsfx.control.textfield.TextFields;
import javafx.scene.input.MouseEvent;

import StudentInformationSystem.entity.Institute;
import StudentInformationSystem.entity.ManageInfo;
import StudentInformationSystem.entity.Subject;
import StudentInformationSystem.logic.InstituteLogic;
import StudentInformationSystem.logic.SubjectLogic;
import StudentInformationSystem.logic.impl.InstituteLogicImpl;
import StudentInformationSystem.logic.impl.SubjectLogicImpl;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import StudentInformationSystem.validate.ValidateSubject;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Manage subjects - Quản lý môn học
 */
public class ManageSubjectController implements Initializable {

	SubjectLogic subjectLogic = new SubjectLogicImpl();
	InstituteLogic instituteLogic = new InstituteLogicImpl();
	CustomAlert customAlert = new CustomAlert();
	static ManageInfo manageInfo;

	@FXML
	private TextField tfsubjectCode, tfsubjectName, tfcreditSubject, tfcreditTuition, tfweight, tfsearchSubject,
			tfSuggestion;
	@FXML
	private TextField tfsubjectCodeAdd, tfsubjectNameAdd, tfcreditSubjectAdd, tfcreditTuitionAdd, tfweightAdd;
	@FXML
	private Label lbIdSubject, requiredList;
	@FXML
	private ComboBox<String> cbInstitute, tfinstitute, cbInstituteAdd;
	@FXML
	private Button btnUpdate, btnAdd, btnUpdateSubject;
	@FXML
	private TableView<Subject> tbUpdateSubject;
	@FXML
	private TableColumn<Subject, String> subjectCodeUS, subjectNameUS, creditSubjectUS, creditTuitionUS, instituteUS,
			weightUS;
	@FXML
	private AnchorPane addSubjectPane;

	@FXML
	private ImageView clear, cancel;

	private ObservableList<Subject> listSubject = FXCollections.observableArrayList();

	private ObservableList<Institute> listIntitute = FXCollections.observableArrayList();

	private ObservableList<String> listRequiredSubject = FXCollections.observableArrayList();

	/**
	 * Display module management interface - Hiển thị giao diện quản lý 
	 *
	 * @param stackPane
	 * @param manageInfo
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane, ManageInfo manageInfo) throws IOException {
		ManageSubjectController.manageInfo = manageInfo;
		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/ManageSubjectView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 * 
	 * 
	 * Khởi tạo giá trị cho combobox và bảng hiển thị tất cả các mô-đun
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			listSubject = getListSubject("", "", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ArrayList<String> suggestion = new ArrayList<String>();

		for (Subject subject : listSubject) {
			suggestion.add(subject.getSubjectCode() + " - " + subject.getSubjectName());
		}

		TextFields.bindAutoCompletion(tfSuggestion, suggestion);

		addSubjectPane.setLayoutX(1300);

		subjectCodeUS.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectCode"));
		subjectNameUS.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectName"));
		creditSubjectUS.setCellValueFactory(new PropertyValueFactory<Subject, String>("creditSubject"));
		creditTuitionUS.setCellValueFactory(new PropertyValueFactory<Subject, String>("creditTuition"));
		instituteUS.setCellValueFactory(new PropertyValueFactory<Subject, String>("institute"));
		weightUS.setCellValueFactory(new PropertyValueFactory<Subject, String>("weight"));

		tbUpdateSubject.setItems(listSubject);

		// init combobox select institute
		try {
			listIntitute = instituteLogic.getListInstitute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ObservableList<String> instituteNameList = FXCollections.observableArrayList();
		instituteNameList.add(Constants.EMPTY_STRING);
		for (int i = 0; i < listIntitute.size(); i++) {
			instituteNameList.add(listIntitute.get(i).getInstituteName());
		}
		cbInstitute.getItems().addAll(instituteNameList);
		cbInstituteAdd.getItems().addAll(instituteNameList);
		tfinstitute.getItems().addAll(instituteNameList);
	}

	/**
	 * 
	 * Lấy giá trị của module đã chọn khi click vào bảng Xem chi tiết
	 * của các giá trị này cho label và text field
	 *
	 * @param click
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void tbSelectSubject(javafx.scene.input.MouseEvent click) throws ClassNotFoundException, SQLException {

		Subject subject = tbUpdateSubject.getSelectionModel().getSelectedItem();
		if (subject != null) {
			tfsubjectCode.setText(subject.getSubjectCode());
			tfsubjectName.setText(subject.getSubjectName());
			tfcreditSubject.setText(String.valueOf(subject.getCreditSubject()));
			tfcreditTuition.setText(String.valueOf(subject.getCreditTuition()));
			tfinstitute.getSelectionModel().select(subject.getInstitute());
			tfweight.setText(String.valueOf(subject.getWeight()));
			lbIdSubject.setText(String.valueOf(subject.getIdSubject()));
		}
	}

	/**
	 * 
	 * Đặt lại tất cả gí trị được hiển thị trên Label và Text Field
	 */
	@FXML
	public void refresh() {
		tfsubjectCode.setText("");
		tfsubjectName.setText("");
		tfcreditSubject.setText("");
		tfcreditTuition.setText("");
		tfinstitute.getSelectionModel().select("");
		tfweight.setText("");
		tfsubjectCodeAdd.setText("");
		tfsubjectNameAdd.setText("");
		tfcreditSubjectAdd.setText("");
		tfcreditTuitionAdd.setText("");
		cbInstituteAdd.getSelectionModel().select("");
		tfweightAdd.setText("");
	}

	/**
	 * 
	 * Hiển thị danh sách các môn theo điều kiện tìm kiếm vào bảng
	 *
	 * @param subjectCode
	 *            Subject code
	 * @param subjectName
	 *            Subject title
	 * @param institute
	 *            institute of student
	 * @return List of subjects
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected ObservableList<Subject> getListSubject(String subjectCode, String subjectName, String institute)
			throws ClassNotFoundException, SQLException {

		return subjectLogic.getListSubject(subjectCode, subjectName, institute);
	}

	/**
	 * 
	 * Hiển thị danh sách các mô-đun theo ký tự người dùng
	 *
	 * @param key
	 *            Getting input events from the keyboard
	 *            Get input từ bàn phím
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void searchByKeyPressed(KeyEvent key) throws ClassNotFoundException, SQLException {

		if (key.getCode().isLetterKey() || key.getCode() == KeyCode.BACK_SPACE || key.getCode().isDigitKey()) {
			ObservableList<Subject> listSubject = FXCollections.observableArrayList();

			if (cbInstitute.getSelectionModel().getSelectedItem() != null
					&& !tfsearchSubject.getText().toString().equals("")) {
				listSubject = getListSubject(tfsearchSubject.getText().toString().trim(),
						tfsearchSubject.getText().toString().trim(),
						cbInstitute.getSelectionModel().getSelectedItem().toString());
			} else if (cbInstitute.getSelectionModel().getSelectedItem() != null
					&& tfsearchSubject.getText().toString().equals("")) {
				listSubject = getListSubject("", "", cbInstitute.getSelectionModel().getSelectedItem().toString());
			} else {
				listSubject = getListSubject(tfsearchSubject.getText().toString().trim(),
						tfsearchSubject.getText().toString().trim(), "");
			}
			tbUpdateSubject.setItems(listSubject);
		}
	}

	/**
	 * 
	 * Hiển thị danh sách các mô-đun bên dưới hộp tổ hợp của người dùng đã chọn
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void searchByInstitute() throws ClassNotFoundException, SQLException {

		ObservableList<Subject> listSubject = FXCollections.observableArrayList();
		if (cbInstitute.getSelectionModel().getSelectedItem() != null
				&& tfsearchSubject.getText().toString().equals("")) {
			listSubject = getListSubject("", "", cbInstitute.getSelectionModel().getSelectedItem().toString());
		} else if (cbInstitute.getSelectionModel().getSelectedItem() != null
				&& !tfsearchSubject.getText().toString().equals("")) {
			listSubject = getListSubject(tfsearchSubject.getText().toString().trim(),
					tfsearchSubject.getText().toString().trim(),
					cbInstitute.getSelectionModel().getSelectedItem().toString());
		} else {
			listSubject = getListSubject("", "", "");
		}

		tbUpdateSubject.setItems(listSubject);
	}

	/**
	 * Switch to a new subject
	 * 
	 */
	@FXML
	public void changeAddSubjectPane() {
		TranslateTransition openAddSubject = new TranslateTransition(new Duration(300), addSubjectPane);
		openAddSubject.setToX(-1300);
		openAddSubject.play();
		refresh();
	}

	/**
	 * Go to pane Edit subject - chinh sửa
	 */
	@FXML
	private void changeUpdateSubjectPane() {
		TranslateTransition openAddSubject = new TranslateTransition(new Duration(300), addSubjectPane);
		openAddSubject.setToX(0);
		openAddSubject.play();
		refresh();
	}

	/**
	 * Add a subject
	 *
	 * @param key
	 */
	@FXML
	public void addRequiredSubject(KeyEvent key) {

		String[] suggest = tfSuggestion.getText().toString().split("-");

		if (key.getCode() == KeyCode.ENTER) {
			Pattern pattern = Pattern.compile(Constants.FORMAT_SUBJECTCODE);
			if (tfSuggestion.getText().equals("") || !pattern.matcher(suggest[0].trim()).matches())
				return;
			else {

				if (listRequiredSubject.indexOf(suggest[0].trim()) < 0) {
					listRequiredSubject.add(suggest[0].trim());
					StringBuilder text = new StringBuilder();
					listRequiredSubject.forEach(subject -> {
						text.append("   " + subject);
					});
					requiredList.setText(text.toString());
					tfSuggestion.setText("");
				} else
					customAlert.createAlert(Constants.WARNING, "Mã học phần đã bị trùng");
			}
		}
	}

	/**
	 * Delete the selected prerequisites - Xóa các điều kiện tiên quyết đã chọn
	 *
	 * @param event
	 *            capture mouse events
	 */
	@FXML
	public void clearRequiredList(MouseEvent event) {
		if (event.getTarget() == clear) {

			listRequiredSubject.removeAll(listRequiredSubject);
			StringBuilder text = new StringBuilder();
			listRequiredSubject.forEach(subject -> {
				text.append("   " + subject);
			});
			requiredList.setText(text.toString());
		}
	}

	/**
	 * Update subject
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void updateSubject() throws ClassNotFoundException, SQLException {

		boolean status = subjectLogic.updateSubject(getSubject("update"));
		if (status == true) {
			customAlert.createAlert(Constants.INFORMATION, "Cập nhật thành công");
		} else {
			customAlert.createAlert(Constants.ERROR, "Cập nhật thất bại");
		}
		searchByInstitute();
	}

	/**
	 * Add subject
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void addSubject() throws ClassNotFoundException, SQLException {

		boolean status = subjectLogic.addSubject(getSubject("add"), listRequiredSubject);
		if (status == true) {
			customAlert.createAlert(Constants.INFORMATION, "Thêm mới thành công");
		} else {
			customAlert.createAlert(Constants.ERROR, "Thêm mới thất bại");
		}
		searchByInstitute();
	}

	/**
	 * Lấy dữ liệu từ Textfield, Label, Combobox để đưa vào đối tượng Subject
	 *
	 * @param type
	 *            to determine validate under addSubject or under updateSubject
	 *            để xác định tính hợp lệ trong addSubject hoặc dưới updateSubject
	 * @return the Subject object in the entity
	 */
	public Subject getSubject(String type) {

		ValidateSubject validateSubject = new ValidateSubject();

		if (type == "add") {
			String errStr = validateSubject.vailidateUpdateSubject(tfsubjectCodeAdd, tfsubjectNameAdd,
					tfcreditSubjectAdd, tfcreditTuitionAdd, cbInstituteAdd, tfweightAdd);
			if (errStr.equals("")) {
				Subject subject = new Subject();
				subject.setSubjectCode(tfsubjectCodeAdd.getText());
				subject.setSubjectName(tfsubjectNameAdd.getText());
				subject.setInstitute(cbInstituteAdd.getSelectionModel().getSelectedItem());
				subject.setCreditSubject(Integer.parseInt(tfcreditSubjectAdd.getText()));
				subject.setCreditTuition(Integer.parseInt(tfcreditTuitionAdd.getText()));
				subject.setWeight(Float.parseFloat(tfweightAdd.getText()));
				return subject;
			} else {
				customAlert.createAlert(Constants.WARNING, errStr);
				return null;
			}

		} else if (type == "update") {
			String errStr = validateSubject.vailidateUpdateSubject(tfsubjectCode, tfsubjectName, tfcreditSubject,
					tfcreditTuition, tfinstitute, tfweight);
			if (errStr.equals("")) {
				Subject subject = new Subject();
				subject.setIdSubject(Integer.parseInt(lbIdSubject.getText()));
				subject.setSubjectCode(tfsubjectCode.getText());
				subject.setSubjectName(tfsubjectName.getText());
				subject.setInstitute(tfinstitute.getSelectionModel().getSelectedItem());
				subject.setCreditSubject(Integer.parseInt(tfcreditSubject.getText()));
				subject.setCreditTuition(Integer.parseInt(tfcreditTuition.getText()));
				subject.setWeight(Float.parseFloat(tfweight.getText()));
				return subject;
			} else {
				customAlert.createAlert(Constants.WARNING, errStr);
				return null;
			}

		} else
			return null;
	}
}
