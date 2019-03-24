package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

			BorderPane root = FXMLLoader.load(getClass().getResource("/Forms/FRMPrincipal.fxml")); 
			Scene scene = new Scene(root);
			primaryStage.setTitle("Mi Empresa");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest((WindowEvent event1) -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("I have a great message for you!");

				alert.showAndWait();			
		    });
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
