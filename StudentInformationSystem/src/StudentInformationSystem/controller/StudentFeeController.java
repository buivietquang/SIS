
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.RegisterSubjectFee;
import StudentInformationSystem.entity.UserInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * View tuition information
 * 
 * Xem thông tin học phí
 * 
 */
public class StudentFeeController implements Initializable {

	FeeLogicImpl feeLogic = new FeeLogicImpl();
	static UserInfo userInfo;

	@FXML
	private Label lbstudent, lbtctrong, lbtcngoai, lbsotien;

	@FXML
	private TableView<RegisterSubjectFee> tblregistersubject;

	@FXML
	private TableColumn<RegisterSubjectFee, String> subjectcode;

	@FXML
	private TableColumn<RegisterSubjectFee, String> subjectname;

	@FXML
	private TableColumn<RegisterSubjectFee, String> institutename;

	@FXML
	private TableColumn<RegisterSubjectFee, Integer> creditsubject;

	@FXML
	private TableColumn<RegisterSubjectFee, Integer> credittuition;
	@FXML
	private ComboBox<String> cbhocky = new ComboBox<String>();

	private ObservableList<RegisterSubjectFee> registerSubjectList;

	/**
	 * View tuition information interface
	 * 
	 * Giao diện xem thông tin học phí
	 * 
	 * @param stackPane
	 *            Original AnchorPane to change the management interface
	 * 			  Original AnchorPane thay đổi giao diện quản lý
	 * @param userInfo
	 *            Information of the user is using
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane, UserInfo userInfo) throws IOException {
		StudentFeeController.userInfo = userInfo;
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/StudentInformationSystem/view/StudentFeeView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 * Graduates need to view the tuition information and return the tuition fees of
	 * the students
	 * 
	 * Sinh viên tốt nghiệp cần xem thông tin học phí và nộp lại học phí của
	 * những học sinh
	 * 
	 * @param event
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbhocky.getItems().addAll("20172", "20181", "20182", "20191", "20192");
		registerSubjectList = FXCollections.observableArrayList();
		cbhocky.getSelectionModel().select("20182");

		try {
			registerSubjectList = feeLogic.getSubjectbyStudent(userInfo.getIdStudent(),
					cbhocky.getSelectionModel().getSelectedItem());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		subjectcode.setCellValueFactory(new PropertyValueFactory<RegisterSubjectFee, String>("subjectcode"));
		subjectname.setCellValueFactory(new PropertyValueFactory<RegisterSubjectFee, String>("subjectname"));
		institutename.setCellValueFactory(new PropertyValueFactory<RegisterSubjectFee, String>("institutename"));
		creditsubject.setCellValueFactory(new PropertyValueFactory<RegisterSubjectFee, Integer>("creditsubject"));
		credittuition.setCellValueFactory(new PropertyValueFactory<RegisterSubjectFee, Integer>("credittuition"));

		tblregistersubject.setItems(registerSubjectList);

		lbstudent.setText(" " + userInfo.getFullname() + " - " + userInfo.getUsername());

		try {
			lbtctrong.setText(" " + String.valueOf(feeLogic.getFee().get(0).getMoney()));
			lbtcngoai.setText(" " + String.valueOf(feeLogic.getFee().get(1).getMoney()));
			lbsotien.setText(" " + String.valueOf(
					feeLogic.totalFee(userInfo.getIdStudent(), cbhocky.getSelectionModel().getSelectedItem())));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Graduates need to view the tuition information and return the tuition fees of
	 * the students
	 * 
	 * Sinh viên tốt nghiệp cần xem thông tin học phí và nộp lại học phí của
	 * những học sinh
	 * 
	 * @param event
	 */
	@FXML
	public void getSemester() {

		registerSubjectList = FXCollections.observableArrayList();

		try {
			registerSubjectList = feeLogic.getSubjectbyStudent(userInfo.getIdStudent(),
					cbhocky.getSelectionModel().getSelectedItem());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		tblregistersubject.setItems(registerSubjectList);

		try {
			lbsotien.setText(" " + String.valueOf(
					feeLogic.totalFee(userInfo.getIdStudent(), cbhocky.getSelectionModel().getSelectedItem())));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
