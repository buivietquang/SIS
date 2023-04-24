
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.Grade;
import StudentInformationSystem.entity.GradeTotal;
import StudentInformationSystem.entity.UserInfo;
import StudentInformationSystem.logic.impl.GradeLogicImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class StudentGradeController implements Initializable {

	GradeLogicImpl studentGrade = new GradeLogicImpl();
	static UserInfo userInfo;

	double GPASemster, GPATotal, CPATotal;
	int creditTotalGrade = 0, creditDebtGrade = 0, creditRegisterGrade = 0;
	int creditRegisterSemster = 0, creditPassGrade = 0;

	@FXML
	private Label lbFullName;
	@FXML
	private Label lbMSSV;
	@FXML
	private Label lbDateOfBirth;
	@FXML
	private Label lbAddress;
	@FXML
	private TableView<Grade> tableGrade;
	@FXML
	private TableColumn<Grade, String> semester;
	@FXML
	private TableColumn<Grade, String> subjectCode;
	@FXML
	private TableColumn<Grade, String> subjectName;
	@FXML
	private TableColumn<Grade, Integer> credit;
	@FXML
	private TableColumn<Grade, Integer> middleGrade;
	@FXML
	private TableColumn<Grade, Integer> finalGrade;
	@FXML
	private TableColumn<Grade, Integer> letterGrade;

	private ObservableList<Grade> studentGradeList = FXCollections.observableArrayList();

	@FXML
	private TableView<GradeTotal> tableCPA;
	@FXML
	private TableColumn<GradeTotal, String> semesterTotal;
	@FXML
	private TableColumn<GradeTotal, Double> GPA;
	@FXML
	private TableColumn<GradeTotal, Double> CPA;
	@FXML
	private TableColumn<GradeTotal, Integer> creditPass;
	@FXML
	private TableColumn<GradeTotal, Integer> creditTotal;
	@FXML
	private TableColumn<GradeTotal, Integer> creditDebt;
	@FXML
	private TableColumn<GradeTotal, Integer> creditRegister;
	@FXML
	private TableColumn<GradeTotal, String> warning;

	private ObservableList<GradeTotal> studentTotalList = FXCollections.observableArrayList();

	/**
	 * Display the results of learning lookup interface
	 * 
	 * Hiển thị kết quả giao diện tra cứu học tập
	 * 
	 * @param Original
	 *            AnchorPane stackPane to change the student interface
	 *            AnchorPane stackPane thay đổi giao diện sinh viên
	 * @param userInfo
	 *            Information of students currently in use
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane, UserInfo userInfo) throws IOException {
		StudentGradeController.userInfo = userInfo;
		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/StudentGradeView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 *  Initialize the value of the user information on the label Initialize
	 * students' results on the board
	 * 
	 * 
	 * Khởi tạo giá trị thông tin người dùng trên label
	 * Khởi tạo kết quả của học sinh lên bảng
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			studentGradeList = studentGrade.getGrade(userInfo.getUsername());
			initTableGrade();
			initTableCPA();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		setInfo();
	}

	/**
	 * Set values for student parameters
	 * 
	 * Đặt giá trị cho thông số sinh viên
	 */
	public void setInfo() {
		lbFullName.setText(userInfo.getFullname());
		lbMSSV.setText(userInfo.getUsername());
		lbDateOfBirth.setText(userInfo.getDateOfBirth());
		lbAddress.setText(userInfo.getAddress());
	}

	/**
	 * Initialize the score for each subject
	 * 
	 * Khởi tạo điểm cho từng môn học
	 */
	public void initTableGrade() {

		semester.setCellValueFactory(new PropertyValueFactory<Grade, String>("semester"));
		subjectCode.setCellValueFactory(new PropertyValueFactory<Grade, String>("subjectCode"));
		subjectName.setCellValueFactory(new PropertyValueFactory<Grade, String>("subjectName"));
		credit.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("credit"));
		middleGrade.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("middleGrade"));
		finalGrade.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("finalGrade"));
		letterGrade.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("letterGrade"));

		tableGrade.setItems(studentGradeList);
	}

	/**
	 * Initialize the value for the summation table
	 * 
	 * 
	 *  Khởi tạo giá trị cho bảng tổng kết
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void initTableCPA() throws ClassNotFoundException, SQLException {

		semesterTotal.setCellValueFactory(new PropertyValueFactory<GradeTotal, String>("semester"));
		GPA.setCellValueFactory(new PropertyValueFactory<GradeTotal, Double>("GPA"));
		CPA.setCellValueFactory(new PropertyValueFactory<GradeTotal, Double>("CPA"));
		creditPass.setCellValueFactory(new PropertyValueFactory<GradeTotal, Integer>("creditPass"));
		creditTotal.setCellValueFactory(new PropertyValueFactory<GradeTotal, Integer>("creditTotal"));
		creditDebt.setCellValueFactory(new PropertyValueFactory<GradeTotal, Integer>("creditDebt"));
		creditRegister.setCellValueFactory(new PropertyValueFactory<GradeTotal, Integer>("creditRegister"));
		warning.setCellValueFactory(new PropertyValueFactory<GradeTotal, String>("warning"));

		setTableContent();

		tableCPA.setItems(studentTotalList);
	}

	/**
	 * Set values for the summation table
	 * Đặt giá trị cho bảng tổng kết
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void setTableContent() throws ClassNotFoundException, SQLException {
		ObservableList<String> semesterList = studentGrade.getSemester(userInfo.getUsername());

		semesterList.forEach(semester -> {

			tableGrade.getItems().forEach(grade -> {
				if (grade.getSemester().equals(semester)) {
					// Total number of registered credits
					creditRegisterGrade += grade.getCredit();
					creditRegisterSemster += grade.getCredit();
					// nếu môn đó trượt thì tính vào số tín chỉ trượt
					// If the subject is slipped, then count on the slipping credits
					if (grade.getLetterGrade().equals("F")) {
						creditDebtGrade += grade.getCredit();
					}
					// nếu qua thì tính vào số tín chỉ qua
					// If passed, then count on the passing credits
					else {
						creditPassGrade += grade.getCredit();
						// tổng số tín chỉ qua
						// total number of passes
						creditTotalGrade += grade.getCredit();
					}
					GPATotal += convertLetterGrade(grade.getLetterGrade()) * grade.getCredit();
					GPASemster += convertLetterGrade(grade.getLetterGrade()) * grade.getCredit();
				}
			});

			GPASemster = GPASemster / creditRegisterSemster;
			CPATotal = GPATotal / creditRegisterGrade;

			GradeTotal e = new GradeTotal(semester, Math.floor(GPASemster * 100) / 100,
					Math.floor(CPATotal * 100) / 100, creditPassGrade, creditTotalGrade, creditDebtGrade,
					creditRegisterGrade, "Mức 0");
			GPASemster = 0.0;
			creditPassGrade = 0;
			creditRegisterSemster = 0;
			studentTotalList.add(e);
		});

	}

	/**
	 * Converts the point value to 4
	 * Chuyển đổi giá trị điểm thành 4
	 * 
	 * @param letterGrade
	 * @return module results on a scale of 4 - kết quả mô-đun trên thang điểm 4
	 */
	public Double convertLetterGrade(String letterGrade) {
		switch (letterGrade) {
			case "A+":
				return 4.0;
			case "A":
				return 4.0;
			case "B+":
				return 3.5;
			case "B":
				return 3.0;
			case "C+":
				return 2.5;
			case "C":
				return 2.0;
			case "D+":
				return 1.5;
			case "D":
				return 1.0;
			case "F":
				return 0.0;
		}
		return null;
	}
}
