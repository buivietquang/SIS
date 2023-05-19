package StudentInformationSystem;

import StudentInformationSystem.controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class SIS extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			LoginController loginController = new LoginController();
	        loginController.setWindows(); 
	        
	       } catch(Exception e) {
	           e.printStackTrace();
	       }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
