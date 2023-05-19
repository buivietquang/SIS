
package StudentInformationSystem.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import StudentInformationSystem.entity.Notice;
import StudentInformationSystem.logic.NoticeLogic;
import StudentInformationSystem.logic.impl.NoticeLogicImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * 
 * POPUP message board interface display
 * Hiển thị giao diện bảng tin POPUP
 */
public class StudentNoticePopUpController implements Initializable {

	NoticeLogic noticeLogic = new NoticeLogicImpl();

	@FXML
	private Label lbtitle, lbdatetime;
	@FXML
	private TextArea tacontent;

	static Notice notice = new Notice();

	/**
	 * POPUP message board interface display
	 * Hiển thị giao diện bảng tin POPUP
	 * 
	 * @param idNotice
	 *            notification ID
	 */
	public void setWindows(int idNotice) {
		try {
			notice = getDetailNotice(idNotice);

			Stage Stage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getResource("/StudentInformationSystem/view/StudentNoticePopUpView.fxml"));
			Scene scene = new Scene(root);
			Stage.setScene(scene);
			Stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Get all notifications
	 * Nhận tất cả thông báo
	 *
	 * @return List of notifications
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Notice> getAllNotice() throws ClassNotFoundException, SQLException {
		return noticeLogic.getNotice();
	}

	/**
	 * Get information from a specific message
	 * Nhận thông tin từ một tin nhắn cụ thể
	 *
	 * @param idNotice
	 *            Message ID
	 * @return returns a Notice object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Notice getDetailNotice(int idNotice) throws ClassNotFoundException, SQLException {
		return noticeLogic.getDetailNotice(idNotice);
	}

	/**
	 * Initialize popup notification data
	 * Khởi tạo dữ liệu thông báo popup
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lbtitle.setText("  " + notice.getTitle());
		tacontent.setText("" + notice.getContent());
		lbdatetime.setText("  " + notice.getDatetime());
	}

}
