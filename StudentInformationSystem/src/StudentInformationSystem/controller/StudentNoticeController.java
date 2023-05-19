
package StudentInformationSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.Notice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * View notice 
 * xem thông báo
 * 
 */
public class StudentNoticeController implements Initializable {

	static Notice notice = new Notice();

	@FXML
	private TableView<Notice> tblnotice;
	@FXML
	private TableColumn<Notice, Integer> idnotice;
	@FXML
	private TableColumn<Notice, String> title;
	@FXML
	private TableColumn<Notice, String> content;
	@FXML
	private TableColumn<Notice, String> datetime;

	private ObservableList<Notice> noticeList;
	StudentNoticePopUpController popUp = new StudentNoticePopUpController();

	/**
	 * Show notification interface
	 * 
	 * Hiện giao diện thông báo
	 * 
	 * @param noticePane
	 * @throws IOException
	 */
	protected void setWindow(AnchorPane noticePane) throws IOException {
		AnchorPane pane = FXMLLoader
				.load(getClass().getResource("/StudentInformationSystem/view/StudentNoticeView.fxml"));
		noticePane.getChildren().setAll(pane);
	}

	/**
	 * Exit event when user clicks on a message
	 * Thoát khi nhấp vào một tin nhắn
	 */
	@FXML
	protected void noticeClickedRow() {

		if (tblnotice.getSelectionModel().getSelectedItem() != null) {
			notice = tblnotice.getSelectionModel().getSelectedItem();

			popUp.setWindows(notice.getIdnotice());
		}
	}

	/**
	 * Initialize the value of the message board
	 * Khởi tạo giá trị của bảng tin
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		noticeList = FXCollections.observableArrayList();
		try {
			noticeList = popUp.getAllNotice();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		idnotice.setCellValueFactory(new PropertyValueFactory<Notice, Integer>("idnotice"));
		title.setCellValueFactory(new PropertyValueFactory<Notice, String>("title"));
		content.setCellValueFactory(new PropertyValueFactory<Notice, String>("content"));
		datetime.setCellValueFactory(new PropertyValueFactory<Notice, String>("datetime"));

		tblnotice.setItems(noticeList);
	}

}
