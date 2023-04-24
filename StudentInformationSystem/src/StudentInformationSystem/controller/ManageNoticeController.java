
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.Notice;
import StudentInformationSystem.logic.NoticeLogic;
import StudentInformationSystem.logic.impl.NoticeLogicImpl;
import StudentInformationSystem.properties.CustomAlert;
import StudentInformationSystem.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * update notifications - cập nhật thông báo đến học sinh
 *
 */
public class ManageNoticeController implements Initializable {

	NoticeLogic noticeLogic = new NoticeLogicImpl();
	NoticeLogicImpl noticeLogicImpl = new NoticeLogicImpl();
	CustomAlert customAlert = new CustomAlert();

	@FXML
	private Label lbidnotice;
	@FXML
	private TextField tftitle;
	@FXML
	private TextArea tacontent;
	@FXML
	private Button btnadd, btnedit, btndelete;
	@FXML
	private TableView<Notice> tblnotice;
	@FXML
	private TableColumn<Notice, Integer> idNotice;
	@FXML
	private TableColumn<Notice, String> title;
	@FXML
	private TableColumn<Notice, String> content;
	@FXML
	private TableColumn<Notice, String> datetime;

	private ObservableList<Notice> noticeList;

	protected void setWindow(AnchorPane stackPane) throws IOException {
		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/ManageNoticeView.fxml"));
		stackPane.getChildren().setAll(pane);
	}

	/**
	 * Khởi tạo dữ liệu cho bảng message
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		noticeList = FXCollections.observableArrayList();

		try {
			noticeList = noticeLogic.getNotice();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}

		idNotice.setCellValueFactory(new PropertyValueFactory<Notice, Integer>("idNotice"));
		title.setCellValueFactory(new PropertyValueFactory<Notice, String>("title"));
		content.setCellValueFactory(new PropertyValueFactory<Notice, String>("content"));
		datetime.setCellValueFactory(new PropertyValueFactory<Notice, String>("datetime"));

		tblnotice.setItems(noticeList);
	}

	/**
	 * 
	 * Chức năng này nhận một thông báo bổ sung, gọi chức năng thông báo bổ sung trong NotceLogic
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void insertNotice(ActionEvent event) throws ClassNotFoundException, SQLException {

		String title = tftitle.getText().toString().trim();
		String content = tacontent.getText().toString().trim();
		String datetime = java.time.LocalDate.now().toString();
		Notice notice = new Notice(10, title, content, datetime);

		noticeLogic.insertNotice(notice);
		reloadTable();
	}

	/**
	 * 
	 * Chức năng này thực hiện sửa tin nhắn, gọi chức năng sửa trong NoticeLogic
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@FXML
	public void updateNotice(ActionEvent event) throws ClassNotFoundException, SQLException {
		Notice notice = getNotice();
		if (notice != null) {
			noticeLogicImpl.updateNotice(notice);
			reloadTable();
		} else {
			customAlert.createAlert(Constants.ERROR, "Đã có lỗi xảy ra");
		}
	}

	/**
	 * Chức năng này thực hiện xóa tin nhắn, gọi chức năng xóa trong NoticeLogic
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteNotice(ActionEvent event) throws ClassNotFoundException, SQLException {
		showConfirmation();
	}

	/**
	 * 
	 * Thư mục hiện lấy đối tượng thông báo trên giao diện khi người dùng muốn thêm, sửa, xóa
	 * 
	 * @return thông báo phản đối Notice
	 */
	public Notice getNotice() {
		int idnotice;
		try {
			idnotice = Integer.parseInt(lbidnotice.getText().trim());
		} catch (Exception e) {
			return null;
		}

		String title = tftitle.getText().toString().trim();
		String content = tacontent.getText().toString().trim();
		String datetime = java.time.LocalDate.now().toString();

		Notice notice = new Notice(idnotice, title, content, datetime);
		return notice;
	}

	/**
	 * 
	 * Xóa dữ liệu dựa trên textfield và textarea
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void refreshAll(ActionEvent event) throws ClassNotFoundException, SQLException {
		lbidnotice.setText("");
		tftitle.setText("");
		tacontent.setText("");
	}

	/**
	 * Làm mới dữ liệu trên bảng quản lý tin nhắn
	 */
	public void reloadTable() {

		noticeList = FXCollections.observableArrayList();

		try {
			noticeList = noticeLogic.getNotice();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		idNotice.setCellValueFactory(new PropertyValueFactory<Notice, Integer>("idNotice"));
		title.setCellValueFactory(new PropertyValueFactory<Notice, String>("title"));
		content.setCellValueFactory(new PropertyValueFactory<Notice, String>("content"));
		datetime.setCellValueFactory(new PropertyValueFactory<Notice, String>("datetime"));

		tblnotice.setItems(noticeList);
	}

	/**
	 * Thoát khi click vào một hàng của bảng quản lý tin nhắn
	 */
	@FXML
	public void tableClickedRow() {
		if (tblnotice.getSelectionModel().getSelectedItem() != null) {
			Notice notice = tblnotice.getSelectionModel().getSelectedItem();
			lbidnotice.setText("" + notice.getIdnotice());
			tftitle.setText("" + notice.getTitle());
			tacontent.setText("" + notice.getContent());
		}
	}

	/**
	 * Hiển thị thông báo xác nhận xóa yêu cầu
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void showConfirmation() throws ClassNotFoundException, SQLException {

		Alert alert = customAlert.createAlert(Constants.INFORMATION, Constants.CONFIRM_DEL);

		if (alert.getResult() == ButtonType.OK) {
			Notice notice = tblnotice.getSelectionModel().getSelectedItem();
			if (notice != null) {
				noticeLogicImpl.deleteNotice(notice);
				reloadTable();
			} else {
				customAlert.createAlert(Constants.ERROR, Constants.HAVE_ERROR);
			}
		}
	}
}
