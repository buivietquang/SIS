package StudentInformationSystem.properties;

import StudentInformationSystem.util.Constants;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CustomAlert {
	
	/**
	 * Sử dụng Factory pattern vào Alert	
	 * @param typeAlert
	 * @param content
	 * @return 
	 */
	public Alert createAlert(String typeAlert, String content) {
		
		switch(typeAlert) {
			case Constants.INFORMATION : {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(typeAlert);
				alert.setTitle(typeAlert);
				alert.setContentText(content);
				alert.showAndWait();				
				return alert;
			}
			case Constants.WARNING : {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText(typeAlert);
				alert.setTitle(typeAlert);
				alert.setContentText(content);
				alert.showAndWait();	
				return alert;
			}
			case Constants.ERROR: {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(typeAlert);
				alert.setTitle(typeAlert);
				alert.setContentText(content);
				alert.showAndWait();	
				return alert;
			}
		}
		return null;
	}
}
