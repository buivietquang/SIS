
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.Grade;
import StudentInformationSystem.entity.ManageInfo;
import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.logic.GradeLogic;
import StudentInformationSystem.logic.UserLogic;
import StudentInformationSystem.logic.impl.GradeLogicImpl;
import StudentInformationSystem.logic.impl.UserLogicImpl;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import StudentInformationSystem.validate.ValidateGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * Quản lý thành tích học sinh
 *
 */
public class ManageGradeController implements Initializable {

	GradeLogic gradeLogic = new GradeLogicImpl();
	UserLogic userLogic = new UserLogicImpl();
	CustomAlert customAlert = new CustomAlert();
	static ManageInfo manageInfo;

	@FXML
	private ComboBox<String> cbMSSV;
	@FXML
	private Label lbFullName, lbSubjectCode, lbSubjectName, lbSemester;
	@FXML
	private TextField tfmiddleGrade, tffinalGrade;
	@FXML
	private Button btnUpdateGrade;
	@FXML
	private TableView<Grade> tableGrade;
	@FXML
	private TableColumn<Grade, String> semester, subjectCode, subjectName, letterGrade;
	@FXML
	private TableColumn<Grade, Integer> credit;
	@FXML
	private TableColumn<Grade, Float> middleGrade, finalGrade;
	private ObservableList<String> MSSVlist = FXCollections.observableArrayList();

	/**
	 * Giao diện quản lý mo đun hiển thị 
	 * 
	 * @param stackPane
	 *            Original AnchorPane thay đổi giao diện quản lý
	 * @param manageInfo
	 *            Information of the administrator is using - thông tin admin đang dùng
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane, ManageInfo manageInfo) throws IOException {
		ManageGradeController.manageInfo = manageInfo;
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/StudentInformationSystem/view/ManageGradeView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 * Khởi tạo giá trị chứa "MSSV"
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			MSSVlist = getMSSV();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		cbMSSV.setItems(MSSVlist);
	}

	/**
	 * 
	 * Lấy giá trị của module, kết quả học tập được chọn khi click vào bảng 
	 * Hiển thị chi tiết các giá trị này cho label và textfield
	 *
	 * @param click
	 * 			  Nhận các tác vụ khi click vào bảng
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void tbSelectSubject(javafx.scene.input.MouseEvent click) throws ClassNotFoundException, SQLException {
		Grade grade = tableGrade.getSelectionModel().getSelectedItem();
		lbSubjectCode.setText(grade.getSubjectCode());
		lbSubjectName.setText(grade.getSubjectName());
		tfmiddleGrade.setText(String.valueOf(grade.getMiddleGrade()));
		tffinalGrade.setText(String.valueOf(grade.getFinalGrade()));
		lbSemester.setText(grade.getSemester());
	}

	/**
	 * Lấy danh sách sinh viên
	 *
	 * @return List of strings "MSSV"
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected ObservableList<String> getMSSV() throws ClassNotFoundException, SQLException {
		return userLogic.getAllUserName();
	}

	/**
	 * 
	 * Bấm vào hộp và chọn MSSV hiển thị tất cả dữ liệu đã học và điểm trong bảng
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void selectMSSV() throws ClassNotFoundException, SQLException {

		ObservableList<Grade> gradeList = FXCollections.observableArrayList();

		UserInfo userInfo = new UserInfo();
		userInfo = userLogic.getUserbyName(cbMSSV.getSelectionModel().getSelectedItem());

		lbFullName.setText(userInfo.getFullname());
		gradeList = gradeLogic.getGrade(userInfo.getUsername());

		// set table column
		semester.setCellValueFactory(new PropertyValueFactory<Grade, String>("semester"));
		subjectCode.setCellValueFactory(new PropertyValueFactory<Grade, String>("subjectCode"));
		subjectName.setCellValueFactory(new PropertyValueFactory<Grade, String>("subjectName"));
		credit.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("credit"));
		middleGrade.setCellValueFactory(new PropertyValueFactory<Grade, Float>("middleGrade"));
		finalGrade.setCellValueFactory(new PropertyValueFactory<Grade, Float>("finalGrade"));
		letterGrade.setCellValueFactory(new PropertyValueFactory<Grade, String>("letterGrade"));

		tableGrade.setItems(gradeList);
	}

	/**
	 * 
	 * Cập nhật điểm từng môn cho sinh viên
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	protected void updateGrade() throws ClassNotFoundException, SQLException {

		UserInfo userInfo = new UserInfo();
		userInfo = userLogic.getUserbyName(cbMSSV.getSelectionModel().getSelectedItem());

		boolean status = gradeLogic.updateGrade(userInfo.getUsername(), getGrade());

		if (status)
			customAlert.createAlert(Constants.INFORMATION, "Cập nhật thành công");
		else
			customAlert.createAlert(Constants.ERROR, "Cập nhật thất bại");

		selectMSSV();
	}

	/**
	 * 
	 * Đóng gói giá trị cho đối tượng Grade
	 * 
	 * @return Grade
	 */
	public Grade getGrade() {
		ValidateGrade validateGrade = new ValidateGrade();
		String errStr = validateGrade.vailidateUpdateGrade(tfmiddleGrade, tffinalGrade);

		if (errStr.equals("")) {
			Grade grade = new Grade();
			grade.setMiddleGrade(Float.parseFloat(tfmiddleGrade.getText()));
			grade.setSubjectCode(lbSubjectCode.getText());
			grade.setFinalGrade(Float.parseFloat(tffinalGrade.getText()));
			grade.setSemester(lbSemester.getText());
			return grade;
		} else {
			customAlert.createAlert(Constants.WARNING, errStr);
			return null;
		}
	}

}
