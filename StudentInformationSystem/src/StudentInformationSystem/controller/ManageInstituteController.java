
package StudentInformationSystem.controller;

import StudentInformationSystem.entity.Institute;
import StudentInformationSystem.entity.Subject;
import StudentInformationSystem.util.Constants;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Manage Institute - Quản lý Khoa
 */

public class ManageInstituteController implements Initializable {

	InstituteDaoImpl instituteDaoImpl = new InstituteDaoImpl();
	InstituteLogicImpl instituteLogicImpl = new InstituteLogicImpl();
	ManageSubjectController updateSubjectController = new ManageSubjectController();

	private ObservableList<Subject> listSubject = FXCollections.observableArrayList();

	private ObservableList<Institute> listIntitute = FXCollections.observableArrayList();

	@FXML
	private TableView<Subject> tb_showSubjectOfInstitute;
	@FXML
	private TableColumn<Subject, String> col_subjectCode;
	@FXML
	private TableColumn<Subject, String> col_subjectName;
	@FXML
	private TableColumn<Subject, String> col_creditSubject;
	@FXML
	private TableColumn<Subject, String> col_creditTution;
	@FXML
	private TableColumn<Subject, String> col_weight;
	@FXML
	private TableView<Institute> tb_showInstitute;
	@FXML
	private TableColumn<Institute, String> col_tenKhoa;
	@FXML
	private TableColumn<Institute, String> col_soDienThoai;
	@FXML
	private TableColumn<Institute, String> col_vanPhong;
	@FXML
	private ComboBox<String> cb_instituteKV;
	@FXML
	TextField tf_searchByInstitute, tf_tenKhoa, tf_phone, tf_vanPhong;
	@FXML
	Label txt_nameInstitute, txt_adrInstitute, txt_phoneInstitute, txt_themKhoa, txt_xoaKhoa, txt_quayLaiQL;
	@FXML
	ImageView img_search, img_add, img_delete, img_quayLaiQL, img_them, img_sua;
	@FXML
	AnchorPane pane_themKhoa, pane_xoaKhoa, paneSlide_capNhatKhoa;
	@FXML
	Button btn_capNhapKhoa;
	@FXML
	public ObservableList<Institute> inforInstitute = FXCollections.observableArrayList();

	/**
	 * Giao diện quản lý Khoa
	 * 
	 * @param stackPane
	 *            Original AnchorPane để thay đổi giao diện quản lý
	 * 
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane stackPane) throws IOException {
		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/ManageInstituteView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 * Khởi tạo giá trị 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			listIntitute = instituteLogicImpl.getListInstitute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ObservableList<String> instituteNameList = FXCollections.observableArrayList();
		instituteNameList.add(Constants.EMPTY_STRING);
		for (int i = 0; i < listIntitute.size(); i++) {
			instituteNameList.add(listIntitute.get(i).getInstituteName());
		}

		paneSlide_capNhatKhoa.setLayoutX(1300);
		cb_instituteKV.getItems().addAll(instituteNameList);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>> show danh sach cac khoa vien len bang
		col_tenKhoa.setCellValueFactory(new PropertyValueFactory<Institute, String>("instituteName"));
		col_soDienThoai.setCellValueFactory(new PropertyValueFactory<Institute, String>("phoneSupport"));
		col_vanPhong.setCellValueFactory(new PropertyValueFactory<Institute, String>("address"));
		tb_showInstitute.setItems(listIntitute);

		///////// >>>>>>>>>>>>>>>>>>>>>>> Show thong tin mon hoc cua 1 khoa len
		///////// tb_showSubjectOfInstitute>>>>>>>>>>>>>
		col_subjectCode.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectCode"));
		col_subjectName.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectName"));
		col_creditSubject.setCellValueFactory(new PropertyValueFactory<Subject, String>("creditSubject"));
		col_creditTution.setCellValueFactory(new PropertyValueFactory<Subject, String>("creditTuition"));
		col_weight.setCellValueFactory(new PropertyValueFactory<Subject, String>("weight"));
		tb_showSubjectOfInstitute.setItems(listSubject);
	}

	/**
	 * Search Khoa
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void searchInstituteByCb() throws ClassNotFoundException, SQLException {
		// Tim theo Khoa cua Panel QL khoa vien
		ObservableList<Subject> listSubject = FXCollections.observableArrayList();
		if (cb_instituteKV.getSelectionModel().getSelectedItem() != null) {
			String institute = cb_instituteKV.getSelectionModel().getSelectedItem();
			listSubject = updateSubjectController.getListSubject("", "", institute);
			inforInstitute = viewInforInstitute(institute);
			txt_phoneInstitute.setText(inforInstitute.get(0).getPhoneSupport());
			txt_adrInstitute.setText(inforInstitute.get(0).getAddress());

			txt_nameInstitute.setText(institute);

		} else {
			listSubject = updateSubjectController.getListSubject("", "", "");
			txt_nameInstitute.setText("");
			txt_phoneInstitute.setText("");
			txt_adrInstitute.setText("");
		}
		tb_showSubjectOfInstitute.setItems(listSubject);
	}

	/**
	 * Select Khoa
	 * 
	 * @param click
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void selectTbInstitute(javafx.scene.input.MouseEvent click) throws ClassNotFoundException, SQLException {
		Institute institute = tb_showInstitute.getSelectionModel().getSelectedItem();
		tf_tenKhoa.setText(institute.getInstituteName());
		tf_phone.setText(institute.getPhoneSupport());
		tf_vanPhong.setText(institute.getAddress());
	}

	/**
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void clickButtonInstitute(MouseEvent event) throws ClassNotFoundException, SQLException {
		ObservableList<Subject> listSubject = FXCollections.observableArrayList();
		if (event.getTarget() == img_search) {
			if (!tf_searchByInstitute.getText().toString().equals("")) {
				String institute = tf_searchByInstitute.getText().toString();
				listSubject = updateSubjectController.getListSubject("", "", institute);
				inforInstitute = viewInforInstitute(institute);
				txt_nameInstitute.setText(inforInstitute.get(0).getInstituteName());
				txt_phoneInstitute.setText(inforInstitute.get(0).getPhoneSupport());
				txt_adrInstitute.setText(inforInstitute.get(0).getAddress());

			} else {
				txt_nameInstitute.setText("");
				listSubject = updateSubjectController.getListSubject("", "", "");
				txt_phoneInstitute.setText("");
				txt_adrInstitute.setText("");
			}
			tb_showSubjectOfInstitute.setItems(listSubject);
		} else if (event.getTarget() == pane_themKhoa || event.getTarget() == img_add
				|| event.getTarget() == txt_themKhoa) {
			TranslateTransition openUpdateInstitute = new TranslateTransition(new Duration(350), paneSlide_capNhatKhoa);
			openUpdateInstitute.setToX(-1300);
			openUpdateInstitute.play();
		} else if (event.getTarget() == img_quayLaiQL) {
			TranslateTransition closeUpdateInstitute = new TranslateTransition(new Duration(350),
					paneSlide_capNhatKhoa);
			closeUpdateInstitute.setToX(0);
			closeUpdateInstitute.play();
		} else if (event.getTarget() == img_them) {
			if (InsertInstitute(tf_tenKhoa, tf_phone, tf_vanPhong)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Thêm Khoa Thành Công");
				alert.setContentText("Tên Khoa: " + tf_tenKhoa.getText() + System.lineSeparator() + "   +Phone: "
						+ tf_phone.getText() + System.lineSeparator() + "  +Văn Phòng: " + tf_vanPhong.getText());
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Cảnh báo");
				alert.setHeaderText("Tên Khoa không hợp lệ");
				alert.setContentText("Có thể tên Khoa bị trùng hoặc không chứa kí tự");

				alert.showAndWait();
			}
			listIntitute = instituteLogicImpl.getListInstitute();
			tb_showInstitute.setItems(listIntitute);
			clearTextFieldKV();
		}
	}

	/**
	 * Xử lý click vào Button
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void effectButtonInstitute(MouseEvent event) throws ClassNotFoundException, SQLException {
		if (event.getTarget() == img_search) {
			img_search.setScaleX(1.3);
			img_search.setScaleY(1.3);
		} else if (event.getTarget() == pane_themKhoa || event.getTarget() == img_add) {
			pane_themKhoa.setScaleX(1.1);
			pane_xoaKhoa.setScaleX(1);
		} else if (event.getTarget() == pane_xoaKhoa || event.getTarget() == img_delete
				|| event.getTarget() == txt_xoaKhoa) {
			pane_xoaKhoa.setScaleX(1.1);
			pane_themKhoa.setScaleX(1);
		} else if (event.getTarget() == img_quayLaiQL) {
			img_quayLaiQL.setScaleX(1.2);
			img_quayLaiQL.setScaleY(1.2);
			txt_quayLaiQL.setVisible(true);
		} else if (event.getTarget() == img_them) {
			img_them.setScaleX(1.1);
			img_them.setScaleY(1.1);
			listIntitute = instituteLogicImpl.getListInstitute();
			tb_showInstitute.setItems(listIntitute);
		} else if (event.getTarget() == img_sua) {
			img_sua.setScaleX(1.1);
			img_sua.setScaleY(1.1);
		} else {
			img_search.setScaleX(1);
			img_search.setScaleY(1);
			pane_themKhoa.setScaleX(1);
			pane_xoaKhoa.setScaleX(1);
			img_quayLaiQL.setScaleX(1);
			img_quayLaiQL.setScaleY(1);
			img_sua.setScaleX(1);
			img_sua.setScaleY(1);
			img_them.setScaleX(1);
			img_them.setScaleY(1);
			txt_quayLaiQL.setVisible(false);

		}
	}

	/**
	 * Clear TextField
	 */
	public void clearTextFieldKV() {
		tf_tenKhoa.setText("");
		tf_phone.setText("");
		tf_vanPhong.setText("");
	}

	/**
	 * View thông tin của khoa
	 * 
	 * @param institute
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected ObservableList<Institute> viewInforInstitute(String institute)
			throws ClassNotFoundException, SQLException {

		return instituteDaoImpl.getInforInstitute(institute);
	}

	/**
	 * Insert tên khoa nếu khong có
	 * 
	 * @param nameInstitute
	 *            name of Institute
	 * @param phoneInstitute
	 * @param addressInstitute
	 *            address of Institute
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected boolean InsertInstitute(TextField nameInstitute, TextField phoneInstitute, TextField addressInstitute)
			throws ClassNotFoundException, SQLException {

		if (instituteLogicImpl.checkValidInstitute(nameInstitute.getText()) && nameInstitute != null) {
			System.out.print(">>>>" + nameInstitute.getText() + phoneInstitute.getText() + addressInstitute.getText());
			instituteDaoImpl.InsertInstitute(nameInstitute.getText(), phoneInstitute.getText(),
					addressInstitute.getText());
			return true;
		} else
			return false;

	}

}
