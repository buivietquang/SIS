
package StudentInformationSystem.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.Institute;
import StudentInformationSystem.entity.Subject;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Search and display the subjects list
 * Tìm kiếm và hiển thị danh sách môn học
 */
public class StudentViewSubjectController implements Initializable {

	SubjectLogic subjectLogic = new SubjectLogicImpl();
	InstituteLogic instituteLogic = new InstituteLogicImpl();
	CustomAlert customAlert = new CustomAlert();

	@FXML
	public AnchorPane searchSubjectPane;

	@FXML
	private Label lbIdSubject;
	@FXML
	protected TextField tfsearchSubject;
	@FXML
	private ComboBox<String> cbInstitute;

	@FXML
	private TableView<Subject> tbUpdateSubject;
	@FXML
	private TableColumn<Subject, String> subjectCode, subjectName, creditSubject, creditTuition, institute, weight;

	private ObservableList<Subject> listSubject = FXCollections.observableArrayList();

	private ObservableList<Institute> listIntitute = FXCollections.observableArrayList();

	/**
	 * Initialize value for subject lookup table
	 * Khởi tạo giá trị cho bảng tra cứu chủ đề
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			listSubject = getListSubject("", "", "");
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
		tbUpdateSubject.setItems(listSubject);

		subjectCode.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectCode"));
		subjectName.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectName"));
		creditSubject.setCellValueFactory(new PropertyValueFactory<Subject, String>("creditSubject"));
		creditTuition.setCellValueFactory(new PropertyValueFactory<Subject, String>("creditTuition"));
		institute.setCellValueFactory(new PropertyValueFactory<Subject, String>("institute"));
		weight.setCellValueFactory(new PropertyValueFactory<Subject, String>("weight"));

	}

	/**
	 * Display the list of subjects under search conditions to the table
	 * Hiển thị danh sách các môn theo điều kiện tìm kiếm vào bảng
	 * 
	 * @param subjectCode
	 *            Subject code
	 * @param subjectName
	 *            Subject name
	 * @param institute
	 * @return List of subjects
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected ObservableList<Subject> getListSubject(String subjectCode, String subjectName, String institute)
			throws ClassNotFoundException, SQLException {

		return subjectLogic.getListSubject(subjectCode, subjectName, institute);
	}

	/**
	 * Display the list of subjects by combobox
	 * Hiển thị danh sách môn học theo combobox
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
	 * Display the list of modules by user character
	 * Hiển thị danh sách các mô-đun theo ký tự người dùng
	 * 
	 * @param key
	 *            Get input events from the keyboard
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

}
